package uz.qodirov.services.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.qodirov.dto.group.GroupCreateDTO;
import uz.qodirov.dto.group.GroupDTO;
import uz.qodirov.dto.group.GroupUpdateDTO;
import uz.qodirov.dto.response.AppErrorDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.dto.subject.SubjectDTO;
import uz.qodirov.entity.Faculty;
import uz.qodirov.entity.Group;
import uz.qodirov.entity.Student;
import uz.qodirov.entity.Subject;
import uz.qodirov.mapper.GroupMapper;
import uz.qodirov.mapper.SubjectMapper;
import uz.qodirov.repository.FacultyRepository;
import uz.qodirov.repository.GroupRepository;
import uz.qodirov.repository.SubjectRepository;
import uz.qodirov.services.AbstractService;

import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 8:05 AM
 */
@Service
public class GroupServiceImpl extends AbstractService<GroupRepository, GroupMapper> implements GroupService {

    private final FacultyRepository facultyRepository;

    private final SubjectRepository subjectRepository;

    @Autowired
    SubjectMapper subjectMapper;

    protected GroupServiceImpl(GroupRepository repository, GroupMapper mapper, FacultyRepository facultyRepository, SubjectRepository subjectRepository) {
        super(repository, mapper);
        this.facultyRepository = facultyRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public ResponseEntity<DataDTO<GroupDTO>> create(GroupCreateDTO createDto) {
        Optional<Faculty> optional = facultyRepository.findByIdAndDeletedFalse(createDto.getFacultyId());
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("faculty not found").build()), HttpStatus.OK);
        boolean exists = repository.existsByNameAndFaculty(createDto.getName(), optional.get());
        if (exists)
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("group already exist").build()), HttpStatus.OK);
        if (createDto.getYear().before(optional.get().getOpenYear()))
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("error date").build()), HttpStatus.OK);
        Group group = mapper.fromCreateDto(createDto);
        group.setFaculty(optional.get());
        GroupDTO groupDTO = mapper.toDto(repository.save(group));
        groupDTO.setFacultyName(optional.get().getName());
        return new ResponseEntity<>(new DataDTO<>(groupDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<String>> addSubject(Long groupId, Long subjectId) {
        Optional<Group> optionalGroup = repository.findByIdAndDeletedFalse(groupId);
        Optional<Subject> optionalSubject = subjectRepository.findByIdAndDeletedFalse(subjectId);
        if (optionalGroup.isEmpty() || optionalSubject.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("group or subject not found").build()), HttpStatus.BAD_REQUEST);
        Group group = optionalGroup.get();
        List<Subject> subject = group.getSubjects();
        subject.add(optionalSubject.get());
        group.setSubjects(subject);
        repository.save(group);
        return new ResponseEntity<>(new DataDTO<>("Successfully added subject in group"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<Boolean>> removeSubject(Long groupId, Long subjectId) {
        Optional<Group> optionalGroup = repository.findByIdAndDeletedFalse(groupId);
        Optional<Subject> optionalSubject = subjectRepository.findByIdAndDeletedFalse(subjectId);
        if (optionalGroup.isEmpty() || optionalSubject.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("group or subject not found").build()), HttpStatus.BAD_REQUEST);
        Group group = optionalGroup.get();
        List<Subject> subject = group.getSubjects();
        subject.remove(optionalSubject.get());
        group.setSubjects(subject);
        repository.save(group);
        return new ResponseEntity<>(new DataDTO<>(true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<Void>> delete(Long id) {
        Optional<Group> optional = repository.findByIdAndDeletedFalse(id);
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("group not found").build()), HttpStatus.BAD_REQUEST);
        Group group = optional.get();
        group.setDeleted(true);
        for (Student student : group.getStudents()) {
            student.setDeleted(true);
        }
        repository.save(group);
        return new ResponseEntity<>(new DataDTO<>(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<GroupDTO>> update(GroupUpdateDTO updateDto) {
        Optional<Group> optional = repository.findByIdAndDeletedFalse(updateDto.getId());
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("faculty not found").build()), HttpStatus.BAD_REQUEST);
        Group group = optional.get();
        if (updateDto.getName() != null && repository.existsByNameAndFaculty(updateDto.getName(), optional.get().getFaculty()))
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("this group already exist").build()), HttpStatus.BAD_REQUEST);
        else if (updateDto.getName() != null) group.setName(updateDto.getName());
        GroupDTO groupDTO = mapper.toDto(repository.save(group));
        groupDTO.setFacultyName(group.getFaculty().getName());
        return new ResponseEntity<>(new DataDTO<>(groupDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<List<GroupDTO>>> getAll() {
        List<Group> all = repository.findAllByDeletedFalseOrderById();
        return toDTO(all);
    }

    @Override
    public ResponseEntity<DataDTO<List<SubjectDTO>>> listSubjectInGroup(Long groupId) {
        Optional<Group> optionalGroup = repository.findByIdAndDeletedFalse(groupId);
        if (optionalGroup.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("group not found").build()), HttpStatus.BAD_REQUEST);
        List<Subject> subjects = optionalGroup.get().getSubjects();
        List<SubjectDTO> subjectDTOList = subjectMapper.toDto(subjects);
        return new ResponseEntity<>(new DataDTO<>(subjectDTOList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<List<GroupDTO>>> getAllByFacultyId(Long id) {
        List<Group> all = repository.findAllByFacultyIdAndDeletedFalse(id);
        return toDTO(all);
    }

    private ResponseEntity<DataDTO<List<GroupDTO>>> toDTO(List<Group> all) {
        List<GroupDTO> groupDTOS = mapper.toDto(all);
        for (int i = 0; i < all.size(); i++) {
            groupDTOS.get(i).setStudentCount(all.get(i).getStudents().size());
            groupDTOS.get(i).setFacultyName(all.get(i).getFaculty().getName());
        }
        return new ResponseEntity<>(new DataDTO<>(groupDTOS), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<GroupDTO>> get(Long id) {
        Optional<Group> optional = repository.findByIdAndDeletedFalse(id);
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("faculty not found").build()), HttpStatus.BAD_REQUEST);
        GroupDTO groupDTO = mapper.toDto(optional.get());
        groupDTO.setStudentCount(optional.get().getStudents().size());
        groupDTO.setFacultyName(optional.get().getFaculty().getName());
        return new ResponseEntity<>(new DataDTO<>(groupDTO), HttpStatus.OK);
    }
}

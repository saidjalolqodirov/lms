package uz.qodirov.services.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.qodirov.dto.response.AppErrorDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.dto.student.StudentCreateDTO;
import uz.qodirov.dto.student.StudentDTO;
import uz.qodirov.dto.student.StudentUpdateDTO;
import uz.qodirov.dto.subject.SubjectDTO;
import uz.qodirov.entity.Group;
import uz.qodirov.entity.Mark;
import uz.qodirov.entity.Student;
import uz.qodirov.mapper.StudentMapper;
import uz.qodirov.mapper.SubjectMapper;
import uz.qodirov.repository.GroupRepository;
import uz.qodirov.repository.MarkRepository;
import uz.qodirov.repository.StudentRepository;
import uz.qodirov.services.AbstractService;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 5:59 PM
 */
@Service
public class StudentServiceImpl extends AbstractService<StudentRepository, StudentMapper> implements StudentService {

    private final GroupRepository groupRepository;
    private final SubjectMapper subjectMapper;

    private final MarkRepository markRepository;

    protected StudentServiceImpl(StudentRepository repository, StudentMapper mapper, GroupRepository groupRepository, SubjectMapper subjectMapper, MarkRepository markRepository) {
        super(repository, mapper);
        this.groupRepository = groupRepository;
        this.subjectMapper = subjectMapper;
        this.markRepository = markRepository;
    }

    @Override
    public ResponseEntity<DataDTO<StudentDTO>> create(StudentCreateDTO createDto) {
        Optional<Group> optionalGroup = groupRepository.findByIdAndDeletedFalse(createDto.getGroupId());
        if (optionalGroup.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("group not found").build()), HttpStatus.BAD_REQUEST);
        Student student = mapper.fromCreateDto(createDto);
        student.setGroup(optionalGroup.get());
        StudentDTO studentDTO = mapper.toDto(repository.save(student));
        studentDTO.setGroupName(optionalGroup.get().getName());
        return new ResponseEntity<>(new DataDTO<>(studentDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<Void>> delete(Long id) {
        Optional<Student> optionalStudent = repository.findByIdAndDeletedFalse(id);
        if (optionalStudent.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("student not found").build()), HttpStatus.BAD_REQUEST);
        Student student = optionalStudent.get();
        student.setDeleted(true);
        repository.save(student);
        return new ResponseEntity<>(new DataDTO<>(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<StudentDTO>> update(StudentUpdateDTO updateDto) {
        Optional<Student> optionalStudent = repository.findByIdAndDeletedFalse(updateDto.getId());
        if (optionalStudent.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("student not found").build()), HttpStatus.BAD_REQUEST);
        Student student = optionalStudent.get();
        Optional<Group> groupOptional = groupRepository.findByIdAndDeletedFalse(updateDto.getGroupId());
        if (groupOptional.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("group not found").build()), HttpStatus.BAD_REQUEST);
        if (updateDto.getGroupId() != null && !Objects.equals(updateDto.getGroupId(), student.getGroup().getId()))
            student.setGroup(groupOptional.get());
        if (updateDto.getAddress() != null) student.setAddress(updateDto.getAddress());
        if (updateDto.getFirstName() != null) student.setEmail(updateDto.getEmail());
        if (updateDto.getLastName() != null) student.setLastName(updateDto.getLastName());
        if (updateDto.getPhone() != null) student.setPhone(updateDto.getPhone());
        StudentDTO studentDTO = mapper.toDto(repository.save(student));
        studentDTO.setGroupName(groupOptional.get().getName());
        return new ResponseEntity<>(new DataDTO<>(studentDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<List<StudentDTO>>> getAll() {
        List<Student> students = repository.findAllByDeletedFalse();
        List<StudentDTO> studentDTOS = mapper.toDto(students);
        for (int i = 0; i < students.size(); i++) {
            studentDTOS.get(i).setGroupName(students.get(i).getGroup().getName());
        }
        return new ResponseEntity<>(new DataDTO<>(studentDTOS), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<StudentDTO>> get(Long id) {
        Optional<Student> optionalStudent = repository.findByIdAndDeletedFalse(id);
        return getDataDTOResponseEntity(optionalStudent);
    }

    @Override
    public ResponseEntity<DataDTO<StudentDTO>> getByUsername(String uname) {
        Optional<Student> optionalStudent = repository.findByUsername(uname);
        return getDataDTOResponseEntity(optionalStudent);
    }

    @Override
    public ResponseEntity<DataDTO<List<SubjectDTO>>> getListSubjects(Long studentId) {
        Optional<Student> optionalStudent = repository.findByIdAndDeletedFalse(studentId);
        if (optionalStudent.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("student not found").build()), HttpStatus.BAD_REQUEST);
        List<SubjectDTO> subjectDTOS = subjectMapper.toDto(optionalStudent.get().getGroup().getSubjects());
        return new ResponseEntity<>(new DataDTO<>(subjectDTOS), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> averageMark(Long groupId) {
        Optional<Group> optionalGroup = groupRepository.findByIdAndDeletedFalse(groupId);
        if (optionalGroup.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("group not found").build()), HttpStatus.BAD_REQUEST);
        List<Student> students = optionalGroup.get().getStudents();
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (Student student : students) {
            int sum = 0;
            for (Mark mark : student.getMarks()) {
                sum += mark.getMark();
            }
            hashMap.put(student.getFirstName(), sum);
        }
        return new ResponseEntity<>(new DataDTO<>(hashMap), HttpStatus.OK);
    }

    private ResponseEntity<DataDTO<StudentDTO>> getDataDTOResponseEntity(Optional<Student> optionalStudent) {
        if (optionalStudent.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("student not found").build()), HttpStatus.BAD_REQUEST);
        StudentDTO studentDTO = mapper.toDto(optionalStudent.get());
        studentDTO.setGroupName(optionalStudent.get().getGroup().getName());
        studentDTO.setFacultyName(optionalStudent.get().getGroup().getFaculty().getName());
        return new ResponseEntity<>(new DataDTO<>(studentDTO), HttpStatus.OK);
    }
}

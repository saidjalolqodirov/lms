package uz.qodirov.services.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.qodirov.dto.faculty.FacultyCreateDTO;
import uz.qodirov.dto.faculty.FacultyDTO;
import uz.qodirov.dto.faculty.FacultyUpdateDTO;
import uz.qodirov.dto.response.AppErrorDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.entity.Faculty;
import uz.qodirov.entity.Group;
import uz.qodirov.entity.University;
import uz.qodirov.mapper.FacultyMapper;
import uz.qodirov.repository.FacultyRepository;
import uz.qodirov.repository.UniversityRepository;
import uz.qodirov.services.AbstractService;
import uz.qodirov.services.group.GroupService;

import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 7:02 PM
 */
@Service
public class FacultyServiceImpl extends AbstractService<FacultyRepository, FacultyMapper> implements FacultyService {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    GroupService groupService;

    protected FacultyServiceImpl(FacultyRepository repository, FacultyMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ResponseEntity<DataDTO<FacultyDTO>> create(FacultyCreateDTO createDto) {
        Optional<University> optional = universityRepository.findByIdAndDeletedFalse(createDto.getUniversityId());
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("university not found").build()), HttpStatus.BAD_REQUEST);
        boolean exists = repository.existsByNameAndUniversityId(createDto.getName(), createDto.getUniversityId());
        if (exists)
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("this faculty already exist").build()), HttpStatus.BAD_REQUEST);
        if (createDto.getOpenYear().before(optional.get().getOpenYear()))
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("error date").build()), HttpStatus.OK);
        Faculty faculty = mapper.fromCreateDto(createDto);
        faculty.setUniversity(optional.get());
        FacultyDTO facultyDTO = mapper.toDto(repository.save(faculty));
        facultyDTO.setUniversityName(faculty.getUniversity().getName());
        return new ResponseEntity<>(new DataDTO<>(facultyDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<Void>> delete(Long id) {
        Optional<Faculty> optional = repository.findByIdAndDeletedFalse(id);
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("faculty not found").build()), HttpStatus.BAD_REQUEST);
        Faculty faculty = optional.get();
        faculty.setDeleted(true);
        for (Group group : faculty.getGroups()) {
            groupService.delete(group.getId());
        }
        repository.save(faculty);
        return new ResponseEntity<>(new DataDTO<>(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<FacultyDTO>> update(FacultyUpdateDTO updateDto) {
        Optional<Faculty> optionalFaculty = repository.findByIdAndDeletedFalse(updateDto.getId());
        if (optionalFaculty.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("faculty not found").build()), HttpStatus.BAD_REQUEST);
        Faculty faculty = optionalFaculty.get();
        if (updateDto.getName() != null && repository.existsByNameAndUniversity(updateDto.getName(), faculty.getUniversity()))
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("this faculty already exist").build()), HttpStatus.BAD_REQUEST);
        else if (updateDto.getName() != null) faculty.setName(updateDto.getName());
        FacultyDTO facultyDTO = mapper.toDto(repository.save(faculty));
        facultyDTO.setUniversityName(faculty.getUniversity().getName());
        return new ResponseEntity<>(new DataDTO<>(facultyDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<List<FacultyDTO>>> getAll() {
        List<Faculty> all = repository.findAllByDeletedFalse();
        return new ResponseEntity<>(new DataDTO<>(mapper.toDto(all)), HttpStatus.OK);
    }

    public ResponseEntity<DataDTO<List<FacultyDTO>>> getAllByUniversityId(Long id) {
        List<Faculty> all = repository.findAllByDeletedFalseAndUniversityId(id);
        return new ResponseEntity<>(new DataDTO<>(mapper.toDto(all)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<FacultyDTO>> get(Long id) {
        Optional<Faculty> optional = repository.findByIdAndDeletedFalse(id);
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("faculty not found").build()), HttpStatus.BAD_REQUEST);
        FacultyDTO facultyDTO = mapper.toDto(optional.get());
        facultyDTO.setUniversityName(optional.get().getUniversity().getName());
        return new ResponseEntity<>(new DataDTO<>(facultyDTO), HttpStatus.OK);
    }
}

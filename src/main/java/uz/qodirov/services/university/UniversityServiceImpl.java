package uz.qodirov.services.university;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.qodirov.dto.response.AppErrorDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.dto.univercity.UniversityCreateDTO;
import uz.qodirov.dto.univercity.UniversityDTO;
import uz.qodirov.dto.univercity.UniversityUpdateDTO;
import uz.qodirov.entity.Faculty;
import uz.qodirov.entity.University;
import uz.qodirov.mapper.UniversityMapper;
import uz.qodirov.repository.UniversityRepository;
import uz.qodirov.services.AbstractService;
import uz.qodirov.services.faculty.FacultyService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 12:52 AM
 */
@Service
public class UniversityServiceImpl extends AbstractService<UniversityRepository, UniversityMapper> implements UniversityService {

    private final FacultyService facultyService;

    protected UniversityServiceImpl(UniversityRepository repository, @Qualifier("universityMapperImpl") UniversityMapper mapper, FacultyService facultyService) {
        super(repository, mapper);
        this.facultyService = facultyService;
    }

    @Override
    public ResponseEntity<DataDTO<UniversityDTO>> create(UniversityCreateDTO createDto) {
        if (createDto.getOpenYear().after(new Date(2022, Calendar.JULY,5)))
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("invalid year").build()), HttpStatus.BAD_REQUEST);
        University university = mapper.fromCreateDto(createDto);
        return new ResponseEntity<>(new DataDTO<>(mapper.toDto(repository.save(university))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<Void>> delete(Long id) {
        Optional<University> optional = repository.findByIdAndDeletedFalse(id);
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("university not found").build()), HttpStatus.BAD_REQUEST);
        University university = optional.get();
        university.setDeleted(true);
        for (Faculty faculty : university.getFaculty()) {
            facultyService.delete(faculty.getId());
        }
        repository.save(university);
        return new ResponseEntity<>(new DataDTO<>(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<UniversityDTO>> update(UniversityUpdateDTO updateDto) {
        Optional<University> optional = repository.findByIdAndDeletedFalse(updateDto.getId());
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("university not found").build()), HttpStatus.BAD_REQUEST);
        University university = optional.get();
//        if (updateDto.getOpenYear() > 0)
//            university.setOpenYear(updateDto.getOpenYear());
        if (updateDto.getAddress() != null)
            university.setAddress(updateDto.getAddress());
        if (updateDto.getName() != null)
            university.setName(updateDto.getName());
        return new ResponseEntity<>(new DataDTO<>(mapper.toDto(repository.save(university))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<List<UniversityDTO>>> getAll() {
        List<University> all = repository.findAllByDeletedFalse();
        return new ResponseEntity<>(new DataDTO<>(mapper.toDto(all)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<UniversityDTO>> get(Long id) {
        Optional<University> optional = repository.findByIdAndDeletedFalse(id);
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("university not found").build()), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new DataDTO<>(mapper.toDto(optional.get())), HttpStatus.OK);
    }
}

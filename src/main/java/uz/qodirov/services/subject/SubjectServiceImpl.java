package uz.qodirov.services.subject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.qodirov.dto.response.AppErrorDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.dto.subject.SubjectCreateDTO;
import uz.qodirov.dto.subject.SubjectDTO;
import uz.qodirov.dto.subject.SubjectUpdateDTO;
import uz.qodirov.entity.Subject;
import uz.qodirov.mapper.SubjectMapper;
import uz.qodirov.repository.SubjectRepository;
import uz.qodirov.services.AbstractService;

import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 1:49 PM
 */
@Service
public class SubjectServiceImpl extends AbstractService<SubjectRepository, SubjectMapper> implements SubjectService {

    protected SubjectServiceImpl(SubjectRepository repository, SubjectMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ResponseEntity<DataDTO<SubjectDTO>> create(SubjectCreateDTO createDto) {
        boolean exists = repository.existsByNameAndDeletedFalse(createDto.getName());
        if (exists)
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("this subject already exist").build()), HttpStatus.BAD_REQUEST);
        Subject subject = mapper.fromCreateDto(createDto);
        repository.save(subject);
        return new ResponseEntity<>(new DataDTO<>(mapper.toDto(subject)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<Void>> delete(Long id) {
        Optional<Subject> optionalSubject = repository.findByIdAndDeletedFalse(id);
        if (optionalSubject.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("subject not found").build()), HttpStatus.BAD_REQUEST);
        Subject subject = optionalSubject.get();
        subject.setDeleted(true);
        return new ResponseEntity<>(new DataDTO<>(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<SubjectDTO>> update(SubjectUpdateDTO updateDto) {
        Optional<Subject> optionalSubject = repository.findByIdAndDeletedFalse(updateDto.getId());
        if (optionalSubject.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("subject not found").build()), HttpStatus.BAD_REQUEST);
        Subject subject = optionalSubject.get();
        if (updateDto.getName() != null || !updateDto.getName().isEmpty()) subject.setName(updateDto.getName());
        if (updateDto.getDescription() != null || !updateDto.getDescription().isEmpty())
            subject.setDescription(updateDto.getDescription());
        return new ResponseEntity<>(new DataDTO<>(mapper.toDto(subject)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<List<SubjectDTO>>> getAll() {
        List<Subject> subjects = repository.findAllByDeletedFalse();
        return new ResponseEntity<>(new DataDTO<>(mapper.toDto(subjects)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<SubjectDTO>> get(Long id) {
        Optional<Subject> optionalSubject = repository.findByIdAndDeletedFalse(id);
        if (optionalSubject.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("subject not found").build()), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new DataDTO<>(mapper.toDto(optionalSubject.get())), HttpStatus.OK);
    }
}

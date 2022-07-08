package uz.qodirov.services.assigment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.qodirov.dto.assigment.AssigmentCreateDTO;
import uz.qodirov.dto.assigment.AssigmentDTO;
import uz.qodirov.dto.assigment.AssigmentUpdateDTO;
import uz.qodirov.dto.response.AppErrorDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.entity.Assignment;
import uz.qodirov.entity.Subject;
import uz.qodirov.mapper.AssigmentMapper;
import uz.qodirov.repository.AssigmentRepository;
import uz.qodirov.repository.SubjectRepository;
import uz.qodirov.services.AbstractService;

import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/8/2022 12:31 AM
 */
@Service
public class AssigmentServiceImpl extends AbstractService<AssigmentRepository, AssigmentMapper> implements AssigmentService {

    @Autowired
    SubjectRepository subjectRepository;

    protected AssigmentServiceImpl(AssigmentRepository repository, AssigmentMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ResponseEntity<DataDTO<AssigmentDTO>> create(AssigmentCreateDTO createDto) {
        Assignment assignment = mapper.fromCreateDto(createDto);
        Optional<Subject> optional = subjectRepository.findByIdAndDeletedFalse(createDto.getSubjectId());
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("subject not found").build()), HttpStatus.BAD_REQUEST);
        assignment.setSubject(optional.get());
        AssigmentDTO assigmentDTO = mapper.toDto(repository.save(assignment));
        assigmentDTO.setSubjectName(optional.get().getName());
        return new ResponseEntity<>(new DataDTO<>(assigmentDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<Void>> delete(Long id) {
        Optional<Assignment> optional = repository.findByIdAndDeletedFalse(id);
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("assigment not found").build()), HttpStatus.BAD_REQUEST);
        Assignment assignment = optional.get();
        assignment.setDeleted(true);
        repository.save(assignment);
        return new ResponseEntity<>(new DataDTO<>(null), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<DataDTO<List<AssigmentDTO>>> getAll() {
        List<Assignment> assignmentList = repository.findAllByDeletedFalse();
        List<AssigmentDTO> assigmentDTOS = mapper.toDto(assignmentList);
        return new ResponseEntity<>(new DataDTO<>(assigmentDTOS), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<AssigmentDTO>> get(Long id) {
        Optional<Assignment> optional = repository.findByIdAndDeletedFalse(id);
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("assigment not found").build()), HttpStatus.BAD_REQUEST);
        AssigmentDTO assigmentDTO = mapper.toDto(optional.get());
        assigmentDTO.setSubjectName(optional.get().getSubject().getName());
        return new ResponseEntity<>(new DataDTO<>(assigmentDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<AssigmentDTO>> update(AssigmentUpdateDTO updateDto) {
        Optional<Assignment> optionalAssignment = repository.findByIdAndDeletedFalse(updateDto.getId());
        if (optionalAssignment.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("assigment not found").build()), HttpStatus.BAD_REQUEST);
        Assignment assignment = optionalAssignment.get();
        if (updateDto.getName() != null)
            assignment.setName(updateDto.getName());
        if (updateDto.getDescription() != null)
            assignment.setDescription(updateDto.getDescription());
        return new ResponseEntity<>(new DataDTO<>(mapper.toDto(repository.save(assignment))), HttpStatus.OK);
    }
}
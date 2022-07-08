package uz.qodirov.services.mark;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.qodirov.dto.mark.MarkCreateDTO;
import uz.qodirov.dto.mark.MarkDTO;
import uz.qodirov.dto.mark.MarkUpdateDTO;
import uz.qodirov.dto.response.AppErrorDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.entity.*;
import uz.qodirov.mapper.MarkMapper;
import uz.qodirov.repository.*;
import uz.qodirov.services.AbstractService;

import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 11:32 PM
 */
@Service
public class MarkServiceImpl extends AbstractService<MarkRepository, MarkMapper> implements MarkService {

    private final StudentRepository studentRepository;
    private final JournalRepository journalRepository;
    private final AssigmentRepository assigmentRepository;

    protected MarkServiceImpl(MarkRepository repository, MarkMapper mapper, StudentRepository studentRepository, JournalRepository journalRepository, AssigmentRepository assigmentRepository) {
        super(repository, mapper);
        this.studentRepository = studentRepository;
        this.journalRepository = journalRepository;
        this.assigmentRepository = assigmentRepository;
    }

    @Override
    public ResponseEntity<DataDTO<MarkDTO>> create(MarkCreateDTO createDto) {
        Optional<Student> optionalStudent = studentRepository.findByIdAndDeletedFalse(createDto.getStudentId());
        if (optionalStudent.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("student not found").build()), HttpStatus.OK);
        Optional<Journal> optionalJournal = journalRepository.findByIdAndDeletedFalse(optionalStudent.get().getGroup().getJournal().getId());
        if (optionalJournal.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("Journal not found").build()), HttpStatus.OK);
        Optional<Assignment> optionalAssignment = assigmentRepository.findByIdAndDeletedFalse(createDto.getAssigmentId());
        if (optionalAssignment.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("assigment not found").build()), HttpStatus.OK);
        Mark mark = mapper.fromCreateDto(createDto);
        mark.setJournal(optionalJournal.get());
        mark.setAssignment(optionalAssignment.get());
        mark.setStudent(optionalStudent.get());
        MarkDTO markDTO = mapper.toDto(repository.save(mark));
        markDTO.setGroupName(optionalStudent.get().getGroup().getName());
        markDTO.setAssigmentName(optionalAssignment.get().getName());
        markDTO.setStudentName(optionalStudent.get().getFirstName());
        return new ResponseEntity<>(new DataDTO<>(markDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<Void>> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<DataDTO<MarkDTO>> update(MarkUpdateDTO updateDto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDTO<List<MarkDTO>>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<DataDTO<MarkDTO>> get(Long id) {
        return null;
    }
}

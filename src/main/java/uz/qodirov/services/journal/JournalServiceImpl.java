package uz.qodirov.services.journal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.qodirov.dto.journal.JournalCreateDTO;
import uz.qodirov.dto.journal.JournalDTO;
import uz.qodirov.dto.journal.JournalUpdateDTO;
import uz.qodirov.dto.response.AppErrorDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.entity.Group;
import uz.qodirov.entity.Journal;
import uz.qodirov.mapper.JournalMapper;
import uz.qodirov.mapper.SubjectMapper;
import uz.qodirov.repository.GroupRepository;
import uz.qodirov.repository.JournalRepository;
import uz.qodirov.services.AbstractService;

import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 10:19 PM
 */
@Service
public class JournalServiceImpl extends AbstractService<JournalRepository, JournalMapper> implements JournalService {

    protected final GroupRepository groupRepository;
    private final SubjectMapper subjectMapper;

    protected JournalServiceImpl(JournalRepository repository, JournalMapper mapper, GroupRepository groupRepository, SubjectMapper subjectMapper) {
        super(repository, mapper);
        this.groupRepository = groupRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public ResponseEntity<DataDTO<JournalDTO>> create(JournalCreateDTO createDto) {
        Optional<Group> optionalGroup = groupRepository.findByIdAndDeletedFalse(createDto.getGroupId());
        if (optionalGroup.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("group not found").build()), HttpStatus.OK);
        if (optionalGroup.get().getJournal() != null && !optionalGroup.get().getJournal().isDeleted()) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("this group already exist journal").build()), HttpStatus.OK);
        }
        Journal journal = mapper.fromCreateDto(createDto);
        journal.setGroup(optionalGroup.get());
        JournalDTO journalDTO = mapper.toDto(repository.save(journal));
        journalDTO.setGroupName(optionalGroup.get().getName());
        journalDTO.setSubjectDTOS(subjectMapper.toDto(journal.getGroup().getSubjects()));
        return new ResponseEntity<>(new DataDTO<>(journalDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<Void>> delete(Long id) {
        Optional<Journal> optionalJournal = repository.findByIdAndDeletedFalse(id);
        if (optionalJournal.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("journal not found").build()), HttpStatus.OK);
        Journal journal = optionalJournal.get();
        journal.setDeleted(true);
        repository.save(journal);
        return new ResponseEntity<>(new DataDTO<>(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<JournalDTO>> update(JournalUpdateDTO updateDto) {
        Optional<Journal> optionalJournal = repository.findByIdAndDeletedFalse(updateDto.getId());
        if (optionalJournal.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("journal not found").build()), HttpStatus.OK);
        Journal journal = optionalJournal.get();
        if (updateDto.getName() != null)
            journal.setName(updateDto.getName());
        JournalDTO journalDTO = mapper.toDto(repository.save(journal));
        return new ResponseEntity<>(new DataDTO<>(journalDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<List<JournalDTO>>> getAll() {
        List<Journal> journals = repository.findAllByDeletedFalse();
        List<JournalDTO> journalDTOS = mapper.toDto(journals);
        return new ResponseEntity<>(new DataDTO<>(journalDTOS), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDTO<JournalDTO>> get(Long id) {
        Optional<Journal> optionalJournal = repository.findByIdAndDeletedFalse(id);
        if (optionalJournal.isEmpty())
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder().message("journal not found").build()), HttpStatus.OK);
        JournalDTO journalDTO = mapper.toDto(optionalJournal.get());
        journalDTO.setGroupName(optionalJournal.get().getGroup().getName());
        journalDTO.setSubjectDTOS(subjectMapper.toDto(optionalJournal.get().getGroup().getSubjects()));
        return new ResponseEntity<>(new DataDTO<>(journalDTO), HttpStatus.OK);
    }
}

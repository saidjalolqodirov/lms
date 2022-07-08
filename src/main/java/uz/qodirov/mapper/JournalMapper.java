package uz.qodirov.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.qodirov.dto.journal.JournalCreateDTO;
import uz.qodirov.dto.journal.JournalDTO;
import uz.qodirov.dto.journal.JournalUpdateDTO;
import uz.qodirov.entity.Journal;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 10:21 PM
 */
@Component
@Mapper(componentModel = "spring")
public interface JournalMapper extends GenericMapper<Journal, JournalDTO, JournalCreateDTO, JournalUpdateDTO, Long> {
}

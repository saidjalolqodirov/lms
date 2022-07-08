package uz.qodirov.services.journal;

import uz.qodirov.dto.journal.JournalCreateDTO;
import uz.qodirov.dto.journal.JournalDTO;
import uz.qodirov.dto.journal.JournalUpdateDTO;
import uz.qodirov.entity.Journal;
import uz.qodirov.services.GenericCrudService;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 10:15 PM
 */
public interface JournalService extends GenericCrudService<Journal, JournalDTO, JournalCreateDTO, JournalUpdateDTO,Long> {
}

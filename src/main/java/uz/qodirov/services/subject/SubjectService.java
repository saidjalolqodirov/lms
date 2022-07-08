package uz.qodirov.services.subject;

import uz.qodirov.dto.subject.SubjectCreateDTO;
import uz.qodirov.dto.subject.SubjectDTO;
import uz.qodirov.dto.subject.SubjectUpdateDTO;
import uz.qodirov.entity.Subject;
import uz.qodirov.services.GenericCrudService;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 1:48 PM
 */
public interface SubjectService extends GenericCrudService<Subject, SubjectDTO, SubjectCreateDTO, SubjectUpdateDTO, Long> {

}

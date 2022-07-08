package uz.qodirov.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.qodirov.dto.subject.SubjectCreateDTO;
import uz.qodirov.dto.subject.SubjectDTO;
import uz.qodirov.dto.subject.SubjectUpdateDTO;
import uz.qodirov.entity.Subject;

import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 1:55 PM
 */
@Component
@Mapper(componentModel = "spring")
public interface SubjectMapper extends GenericMapper<Subject, SubjectDTO, SubjectCreateDTO, SubjectUpdateDTO, Long> {

    @Override
    SubjectDTO toDto(Subject subject);

    @Override
    List<SubjectDTO> toDto(List<Subject> e);

    @Override
    Subject fromCreateDto(SubjectCreateDTO subjectCreateDTO);

    @Override
    Subject fromUpdateDto(SubjectUpdateDTO d);
}

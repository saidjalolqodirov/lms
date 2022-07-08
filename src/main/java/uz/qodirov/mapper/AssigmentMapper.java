package uz.qodirov.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.qodirov.dto.assigment.AssigmentCreateDTO;
import uz.qodirov.dto.assigment.AssigmentDTO;
import uz.qodirov.dto.assigment.AssigmentUpdateDTO;
import uz.qodirov.entity.Assignment;

import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/8/2022 12:32 AM
 */
@Component
@Mapper(componentModel = "spring")
public interface AssigmentMapper extends GenericMapper<Assignment, AssigmentDTO, AssigmentCreateDTO, AssigmentUpdateDTO, Long> {
    @Override
    AssigmentDTO toDto(Assignment assignment);

    @Override
    List<AssigmentDTO> toDto(List<Assignment> e);

    @Override
    Assignment fromCreateDto(AssigmentCreateDTO assigmentCreateDTO);

    @Override
    Assignment fromUpdateDto(AssigmentUpdateDTO d);
}

package uz.qodirov.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.qodirov.dto.mark.MarkCreateDTO;
import uz.qodirov.dto.mark.MarkDTO;
import uz.qodirov.dto.mark.MarkUpdateDTO;
import uz.qodirov.entity.Mark;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 11:34 PM
 */
@Component
@Mapper(componentModel = "spring")
public interface MarkMapper extends GenericMapper<Mark, MarkDTO, MarkCreateDTO, MarkUpdateDTO, Long> {
}

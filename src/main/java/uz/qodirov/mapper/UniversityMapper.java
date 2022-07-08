package uz.qodirov.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.qodirov.dto.univercity.UniversityCreateDTO;
import uz.qodirov.dto.univercity.UniversityDTO;
import uz.qodirov.dto.univercity.UniversityUpdateDTO;
import uz.qodirov.entity.University;

import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 12:46 AM
 */
@Component
@Mapper(componentModel = "spring")
public interface UniversityMapper extends GenericMapper<University, UniversityDTO, UniversityCreateDTO, UniversityUpdateDTO, Number> {

    @Override
    UniversityDTO toDto(University university);

    @Override
    List<UniversityDTO> toDto(List<University> e);

    @Override
    University fromCreateDto(UniversityCreateDTO universityCreateDTO);

    @Override
    University fromUpdateDto(UniversityUpdateDTO d);
}

package uz.qodirov.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.qodirov.dto.faculty.FacultyCreateDTO;
import uz.qodirov.dto.faculty.FacultyDTO;
import uz.qodirov.dto.faculty.FacultyUpdateDTO;
import uz.qodirov.entity.Faculty;

import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 7:04 PM
 */
@Component
@Mapper(componentModel = "spring")
public interface FacultyMapper extends GenericMapper<Faculty, FacultyDTO, FacultyCreateDTO, FacultyUpdateDTO, Number> {
    @Override
    FacultyDTO toDto(Faculty faculty);

    @Override
    List<FacultyDTO> toDto(List<Faculty> e);

    @Override
    Faculty fromCreateDto(FacultyCreateDTO facultyCreateDTO);

    @Override
    Faculty fromUpdateDto(FacultyUpdateDTO d);
}

package uz.qodirov.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.qodirov.dto.student.StudentCreateDTO;
import uz.qodirov.dto.student.StudentDTO;
import uz.qodirov.dto.student.StudentUpdateDTO;
import uz.qodirov.entity.Student;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 6:01 PM
 */
@Component
@Mapper(componentModel = "spring")
public interface StudentMapper extends GenericMapper<Student, StudentDTO, StudentCreateDTO, StudentUpdateDTO, Long> {
}

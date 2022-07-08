package uz.qodirov.services.student;

import org.springframework.http.ResponseEntity;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.dto.student.StudentCreateDTO;
import uz.qodirov.dto.student.StudentDTO;
import uz.qodirov.dto.student.StudentUpdateDTO;
import uz.qodirov.dto.subject.SubjectDTO;
import uz.qodirov.entity.Student;
import uz.qodirov.services.GenericCrudService;

import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 5:58 PM
 */
public interface StudentService extends GenericCrudService<Student, StudentDTO, StudentCreateDTO, StudentUpdateDTO, Long> {
    ResponseEntity<DataDTO<StudentDTO>> getByUsername(String id);

    ResponseEntity<DataDTO<List<SubjectDTO>>> getListSubjects(Long studentId);

    ResponseEntity<?> averageMark(Long groupId);
}

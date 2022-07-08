package uz.qodirov.services.faculty;

import org.springframework.http.ResponseEntity;
import uz.qodirov.dto.faculty.FacultyCreateDTO;
import uz.qodirov.dto.faculty.FacultyDTO;
import uz.qodirov.dto.faculty.FacultyUpdateDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.entity.Faculty;
import uz.qodirov.services.GenericCrudService;

import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 6:57 PM
 */
public interface FacultyService extends GenericCrudService<Faculty, FacultyDTO, FacultyCreateDTO, FacultyUpdateDTO, Long> {
    @Override
    ResponseEntity<DataDTO<FacultyDTO>> create(FacultyCreateDTO createDto);

    @Override
    ResponseEntity<DataDTO<Void>> delete(Long id);

    @Override
    ResponseEntity<DataDTO<FacultyDTO>> update(FacultyUpdateDTO updateDto);

    @Override
    ResponseEntity<DataDTO<List<FacultyDTO>>> getAll();

    @Override
    ResponseEntity<DataDTO<FacultyDTO>> get(Long id);

    ResponseEntity<DataDTO<List<FacultyDTO>>> getAllByUniversityId(Long id);
}

package uz.qodirov.services.university;

import org.springframework.http.ResponseEntity;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.dto.univercity.UniversityCreateDTO;
import uz.qodirov.dto.univercity.UniversityDTO;
import uz.qodirov.dto.univercity.UniversityUpdateDTO;
import uz.qodirov.entity.University;
import uz.qodirov.services.GenericCrudService;

import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 12:56 AM
 */
public interface UniversityService extends GenericCrudService<University, UniversityDTO, UniversityCreateDTO, UniversityUpdateDTO, Long> {

    @Override
    ResponseEntity<DataDTO<UniversityDTO>> create(UniversityCreateDTO createDto);

    @Override
    ResponseEntity<DataDTO<Void>> delete(Long id);

    @Override
    ResponseEntity<DataDTO<UniversityDTO>> update(UniversityUpdateDTO updateDto);

    @Override
    ResponseEntity<DataDTO<List<UniversityDTO>>> getAll();

    @Override
    ResponseEntity<DataDTO<UniversityDTO>> get(Long id);
}

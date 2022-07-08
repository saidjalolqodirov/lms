package uz.qodirov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.qodirov.entity.Faculty;
import uz.qodirov.entity.Group;
import uz.qodirov.entity.University;

import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 8:02 AM
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long>, BaseRepository {
    Optional<Group> findByIdAndDeletedFalse(Long id);

    List<Group> findAllByDeletedFalseOrderById();

    boolean existsByNameAndFaculty(String name, Faculty faculty);

    List<Group> findAllByFacultyIdAndDeletedFalse(Long id);

    boolean existsByIdAndDeletedFalse(Long id);
}

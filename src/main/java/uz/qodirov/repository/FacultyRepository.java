package uz.qodirov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.qodirov.entity.Faculty;
import uz.qodirov.entity.University;

import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 7:03 PM
 */
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long>, BaseRepository {

    Optional<Faculty> findByIdAndDeletedFalse(Long id);

    List<Faculty> findAllByDeletedFalse();

    List<Faculty> findAllByDeletedFalseAndUniversityId(Long id);

    boolean existsByNameAndUniversityId(String s, Long id);

    boolean existsByName(String s);

    boolean existsByNameAndUniversity(String name, University university);
}

package uz.qodirov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.qodirov.entity.University;

import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 12:44 AM
 */

public interface UniversityRepository extends JpaRepository<University, Long>, BaseRepository {

    Optional<University> findByIdAndDeletedFalse(Long id);

    List<University> findAllByDeletedFalse();

    boolean existsByName(String name);
}

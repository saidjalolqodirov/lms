package uz.qodirov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.qodirov.entity.Subject;

import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 1:54 PM
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>, BaseRepository {

    boolean existsByNameAndDeletedFalse(String name);

    Optional<Subject> findByIdAndDeletedFalse(Long id);

    List<Subject> findAllByDeletedFalse();
}

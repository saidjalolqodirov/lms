package uz.qodirov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.qodirov.entity.Assignment;

import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/8/2022 12:09 AM
 */
public interface AssigmentRepository extends JpaRepository<Assignment, Long>, BaseRepository {
    Optional<Assignment> findByIdAndDeletedFalse(Long assigmentId);

    List<Assignment> findAllByDeletedFalse();
}

package uz.qodirov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.qodirov.entity.Journal;

import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 10:20 PM
 */
public interface JournalRepository extends JpaRepository<Journal, Long>, BaseRepository {
    Optional<Journal> findByIdAndDeletedFalse(Long id);

    List<Journal> findAllByDeletedFalse();

}

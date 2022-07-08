package uz.qodirov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.qodirov.entity.Student;

import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 6:00 PM
 */
public interface StudentRepository extends JpaRepository<Student, Long>, BaseRepository {

    boolean existsByEmail(String s);

    boolean existsByPhone(String s);

    Optional<Student> findByIdAndDeletedFalse(Long id);

    Optional<Student> findByUsername(String uname);

    List<Student> findAllByDeletedFalse();

    boolean existsByUsername(String s);
}

package uz.qodirov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.qodirov.dto.student.Average;
import uz.qodirov.entity.Mark;

import java.util.List;
import java.util.Optional;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 11:34 PM
 */
public interface MarkRepository extends JpaRepository<Mark, Long>, BaseRepository {
    Optional<Mark> findByIdAndDeletedFalse(Long id);

    @Query(value = "select m.id, avg(m.mark) as mark,\n" +
            "       s.first_name from lms.mark m inner join lms.student s on s.id = m.student_id\n" +
            "                                         where s.group_id = 2\n" +
            "                                         group by m.student_id, s.first_name,m.id order by avg(m.mark) desc", nativeQuery = true)
    List<Mark> findByGroupId(Long id);
}

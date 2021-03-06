package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GradeRepo extends JpaRepository<Grade,Long> {
    List<Grade> findGradeByTimestampBetween(LocalDateTime from, LocalDateTime to);
}

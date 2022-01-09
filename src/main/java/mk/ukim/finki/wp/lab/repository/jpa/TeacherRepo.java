package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.TeacherFullName;
import mk.ukim.finki.wp.lab.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Long> {
@Modifying
@Query(value = "update Teacher t set t.teacherFullName=?2,t.dateOfEmployment=?3 where t.id=?1")
void updateTeacher(Long teacherId, TeacherFullName teacherFullName, LocalDate date);
}

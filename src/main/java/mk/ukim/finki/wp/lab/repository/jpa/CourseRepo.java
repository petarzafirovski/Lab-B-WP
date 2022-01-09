package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {
    List<Student> findAllStudentsByCourseId(Long courseId);
    @Modifying
    @Query(value = "update Course c set c.name = ?2,c.description=?3,c.teacher.id=?4,c.type=?5 where c.courseId=?1")
    void updateCourse(Long courseId, String name, String description, Long teacherId, Type type);
}

package codingdojo.cl.coursemaster.enrollment.data;

import codingdojo.cl.coursemaster.course.domain.Course;
import codingdojo.cl.coursemaster.security.user.domain.CourseUser;
import codingdojo.cl.coursemaster.enrollment.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByCourseAndUser(Course course, CourseUser user);
}

package codingdojo.cl.coursemaster.course.data;

import codingdojo.cl.coursemaster.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

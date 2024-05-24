package codingdojo.cl.coursemaster.enrollment.domain;

import codingdojo.cl.coursemaster.course.domain.Course;
import codingdojo.cl.coursemaster.security.user.domain.CourseUser;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CourseUser user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDate signUpDate;
}

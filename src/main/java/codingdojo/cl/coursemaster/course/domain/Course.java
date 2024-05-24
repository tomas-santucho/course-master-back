package codingdojo.cl.coursemaster.course.domain;

import codingdojo.cl.coursemaster.security.user.domain.CourseUser;
import codingdojo.cl.coursemaster.enrollment.domain.Enrollment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.util.Set;

@Entity
@Data
@ToString(exclude = "enrollments")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String instructor;
    @Size(min = 1)
    private int capacity;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enrollment> enrollments;

    @ManyToMany
    @JoinTable(
            name = "course_users",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<CourseUser> users;
}
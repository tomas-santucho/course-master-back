package codingdojo.cl.coursemaster.security.user.data;

import codingdojo.cl.coursemaster.security.user.domain.CourseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseUserRepository extends JpaRepository<CourseUser, Long> {
    Optional<CourseUser> findByEmail(String email);
}

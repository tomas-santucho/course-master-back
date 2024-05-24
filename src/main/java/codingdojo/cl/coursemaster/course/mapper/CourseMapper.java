package codingdojo.cl.coursemaster.course.mapper;

import codingdojo.cl.coursemaster.course.domain.Course;
import codingdojo.cl.coursemaster.course.dto.CourseDTO;
import codingdojo.cl.coursemaster.course.dto.CourseDTOWithUserData;
import codingdojo.cl.coursemaster.course.dto.UserDTO;
import codingdojo.cl.coursemaster.security.user.domain.CourseUser;
import codingdojo.cl.coursemaster.enrollment.domain.Enrollment;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseDTO toDTO(final Course course) {
        Set<Enrollment> enrollments = (course.getEnrollments() == null) ? Collections.emptySet() : course.getEnrollments();
        var userDTOs = enrollments.stream()
                .map(this::toUserDTO)
                .collect(Collectors.toSet());
        return new CourseDTO(course.getId(), course.getName(), course.getInstructor(), course.getCapacity(), userDTOs.size(), userDTOs);
    }

    public CourseDTOWithUserData toDTO(final Course course, final CourseUser user) {
        Set<Enrollment> enrollments = (course.getEnrollments() == null) ? Collections.emptySet() : course.getEnrollments();
        var userDTOs = enrollments.stream()
                .map(this::toUserDTO)
                .collect(Collectors.toSet());
        return new CourseDTOWithUserData(course.getId(), course.getName(), course.getInstructor(), course.getCapacity(), userDTOs.size(), userDTOs, user.getCourses().stream().anyMatch(c -> c.getId().equals(course.getId())));
    }


    private UserDTO toUserDTO(Enrollment enrollment) {
        var user = enrollment.getUser();
        return new UserDTO(user.getId(),
                user.getName(),
                user.getEmail(),
                enrollment.getSignUpDate(),
                user.getCourses().stream().map(Course::getId).collect(Collectors.toSet()));
    }
}
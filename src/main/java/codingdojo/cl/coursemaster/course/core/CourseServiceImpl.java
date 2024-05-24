package codingdojo.cl.coursemaster.course.core;

import codingdojo.cl.coursemaster.course.domain.Course;
import codingdojo.cl.coursemaster.course.data.CourseRepository;
import codingdojo.cl.coursemaster.course.dto.CourseDTO;
import codingdojo.cl.coursemaster.course.dto.CourseDTOWithUserData;
import codingdojo.cl.coursemaster.course.dto.CreateCourseDTO;
import codingdojo.cl.coursemaster.course.dto.UpdateCourseDTO;
import codingdojo.cl.coursemaster.course.mapper.CourseMapper;
import codingdojo.cl.coursemaster.security.user.domain.CourseUser;
import codingdojo.cl.coursemaster.security.user.data.CourseUserRepository;
import codingdojo.cl.coursemaster.enrollment.domain.Enrollment;
import codingdojo.cl.coursemaster.enrollment.data.EnrollmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseUserRepository courseUserRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseMapper courseMapper;

    @Override
    public List<CourseDTOWithUserData> getAllCourses(CourseUser user) {
        return courseRepository.findAll().stream()
                .map(u->courseMapper.toDTO(u, user))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTOWithUserData getCourseById(Long id, CourseUser user) {
        var course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        return courseMapper.toDTO(course, user);
    }

    @Override
    public CourseDTO createCourse(CreateCourseDTO createCourseDTO) {
        if (createCourseDTO.capacity()<1) {
            throw new RuntimeException("Capacity must be greater than 0");
        }
        var course = new Course();
        course.setName(createCourseDTO.name());
        course.setInstructor(createCourseDTO.instructor());
        course.setCapacity(createCourseDTO.capacity());
        var createdCourse = courseRepository.save(course);
        return courseMapper.toDTO(createdCourse);
    }

    @Override
    public CourseDTO updateCourse(Long id, UpdateCourseDTO updateCourseDTO) {
        var existingCourse = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        existingCourse.setName(updateCourseDTO.name());
        existingCourse.setInstructor(updateCourseDTO.instructor());
        existingCourse.setCapacity(updateCourseDTO.capacity());
        var updatedCourse = courseRepository.save(existingCourse);
        return courseMapper.toDTO(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        var course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        course.setUsers(null);
        courseRepository.save(course);
        courseRepository.deleteById(id);
    }

    @Override
    public CourseDTO addUserToCourse(Long courseId, Long userId) {
        var course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        if (course.getCapacity() == course.getUsers().size()) {
            throw new RuntimeException("Course is full");
        }

        var user = courseUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (course.getUsers().contains(user)) {
            throw new RuntimeException("User already enrolled in course");
        }

        var enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setUser(user);
        enrollment.setSignUpDate(LocalDate.now());
        enrollmentRepository.save(enrollment);

        course.getUsers().add(user);
        var updatedCourse = courseRepository.save(course);
        return courseMapper.toDTO(updatedCourse);
    }

    @Override
    public void removeUserFromCourse(Long courseId, Long userId) {
        var course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        var user = courseUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        var enrollment = enrollmentRepository.findByCourseAndUser(course, user).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollmentRepository.delete(enrollment);
        course.getUsers().remove(user);
        courseRepository.save(course);
    }
}

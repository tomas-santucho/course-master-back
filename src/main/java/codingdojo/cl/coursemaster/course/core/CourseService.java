package codingdojo.cl.coursemaster.course.core;

import codingdojo.cl.coursemaster.course.dto.CourseDTO;
import codingdojo.cl.coursemaster.course.dto.CourseDTOWithUserData;
import codingdojo.cl.coursemaster.course.dto.CreateCourseDTO;
import codingdojo.cl.coursemaster.course.dto.UpdateCourseDTO;
import codingdojo.cl.coursemaster.security.user.domain.CourseUser;

import java.util.List;

public interface CourseService {
    List<CourseDTOWithUserData> getAllCourses(CourseUser user);
    CourseDTOWithUserData getCourseById(Long id, CourseUser user);
    CourseDTO createCourse(CreateCourseDTO createCourseDTO);
    CourseDTO updateCourse(Long id, UpdateCourseDTO updateCourseDTO);
    void deleteCourse(Long id);
    CourseDTO addUserToCourse(Long courseId, Long userId);
    void removeUserFromCourse(Long courseId, Long userId);
}

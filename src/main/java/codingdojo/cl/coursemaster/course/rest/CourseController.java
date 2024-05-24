package codingdojo.cl.coursemaster.course.rest;

import codingdojo.cl.coursemaster.course.core.CourseService;
import codingdojo.cl.coursemaster.course.dto.*;
import codingdojo.cl.coursemaster.security.user.domain.CourseUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public List<CourseDTOWithUserData> getAllCourses(Authentication authentication) {
        return courseService.getAllCourses( (CourseUser) authentication.getPrincipal());
    }

    @GetMapping("/{id}")
    public CourseDTOWithUserData getCourseById(@PathVariable Long id, Authentication authentication) {
        return courseService.getCourseById(id, (CourseUser) authentication.getPrincipal());
    }

    @PostMapping
    public CourseDTO createCourse(@RequestBody CreateCourseDTO createCourseDTO) {
        return courseService.createCourse(createCourseDTO);
    }

    @PutMapping("/{id}")
    public CourseDTO updateCourse(@PathVariable Long id, @RequestBody UpdateCourseDTO updateCourseDTO) {
        return courseService.updateCourse(id, updateCourseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    @PostMapping("/{courseId}/users")
    public CourseDTO addUserToCourse(@PathVariable Long courseId, @RequestBody AddUserToCourseDTO addUserToCourseDTO) {
        return courseService.addUserToCourse(courseId, addUserToCourseDTO.userId());
    }

    @DeleteMapping("/{courseId}/users/{userId}")
    public void removeUserFromCourse(@PathVariable Long courseId, @PathVariable Long userId) {
        courseService.removeUserFromCourse(courseId, userId);
    }
}
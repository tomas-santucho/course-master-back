package codingdojo.cl.coursemaster.course.dto;

public record UpdateCourseDTO(
        String name,
        String instructor,
        int capacity
) {}

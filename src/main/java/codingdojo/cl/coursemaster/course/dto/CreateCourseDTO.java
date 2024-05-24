package codingdojo.cl.coursemaster.course.dto;

public record CreateCourseDTO(
        String name,
        String instructor,
        int capacity
) {}
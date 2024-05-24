package codingdojo.cl.coursemaster.course.dto;

import java.util.Set;

public record CourseDTOWithUserData(
        Long id,
        String name,
        String instructor,
        int capacity,

        int signups,

        Set<UserDTO> users,

        boolean isUserInCourse
) {
}
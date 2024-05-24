package codingdojo.cl.coursemaster.course.dto;

import java.time.LocalDate;
import java.util.Set;

public record UserDTO(
        Long id,
        String name,
        String email,
        LocalDate signUpDate,

        Set<Long> courses
) {}
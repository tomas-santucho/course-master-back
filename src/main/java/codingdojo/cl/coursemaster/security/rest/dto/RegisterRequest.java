package codingdojo.cl.coursemaster.security.rest.dto;

import org.springframework.lang.NonNull;

public record RegisterRequest(@NonNull String name,
                              @NonNull String user,
                              @NonNull String pass) {
}

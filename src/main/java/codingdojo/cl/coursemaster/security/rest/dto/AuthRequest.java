package codingdojo.cl.coursemaster.security.rest.dto;

import org.springframework.lang.NonNull;

public record AuthRequest(@NonNull String user,
                          @NonNull String pass) {
}

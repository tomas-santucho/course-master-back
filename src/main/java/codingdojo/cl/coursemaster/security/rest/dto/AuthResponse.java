package codingdojo.cl.coursemaster.security.rest.dto;

public record AuthResponse(String name, String token, Long userId) {
}

package spring.security.jwtdemo.security;

public class InvalidJwtException extends RuntimeException {

    public InvalidJwtException(String message) {
        super(message);
    }
}

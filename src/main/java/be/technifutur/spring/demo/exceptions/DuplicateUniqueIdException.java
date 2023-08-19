package be.technifutur.spring.demo.exceptions;

public class DuplicateUniqueIdException extends RuntimeException {
    public DuplicateUniqueIdException(String message) {
        super(message);
    }
}

package be.technifutur.spring.demo.exceptions;

import lombok.Getter;
import java.util.Collection;

@Getter
public class UniqueViolationException extends RuntimeException {

    private final Collection<String> fieldNames; // Les noms des champs ayant violé la contrainte d'unicité

    // Constructeur de la classe
    public UniqueViolationException(Collection<String> fieldNames) {
        this(null, fieldNames);
    }

    // Constructeur de la classe avec une cause d'exception
    public UniqueViolationException(Throwable cause, Collection<String> fieldNames) {
        super("{%s} should be unique".formatted(fieldNames), cause); // Message d'erreur avec les noms des champs
        this.fieldNames = fieldNames;
    }
}

package be.technifutur.spring.demo.exceptions;

import lombok.Getter;

@Getter
public class ResourceAlreadyLinkedException extends RuntimeException {

    // Les informations relatives à la ressource contenant
    private final Class<?> containingClazz; // La classe de la ressource contenant
    private final Object containingId; // L'ID de la ressource contenant

    // Les informations relatives à la ressource contenue
    private final Class<?> containedClazz; // La classe de la ressource contenue
    private final Object containedId; // L'ID de la ressource contenue

    // Constructeur de la classe
    public ResourceAlreadyLinkedException(Class<?> containingClazz, Object containingId, Class<?> containedClazz, Object containedId) {
        super("{%s} with id {%s} already contains a {%s} with id {%s}".formatted(
                containingClazz.getSimpleName(),
                containingId.toString(),
                containedClazz.getSimpleName(),
                containedId.toString()
        ));
        this.containingClazz = containingClazz;
        this.containingId = containingId;
        this.containedClazz = containedClazz;
        this.containedId = containedId;
    }
}

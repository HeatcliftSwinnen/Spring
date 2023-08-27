package be.technifutur.spring.demo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ResourceNotFound2Exception extends ResponseStatusException {

    private final Object id;
    private final Class<?> resourceClass;

    public ResourceNotFound2Exception(Object id, Class<?> resourceClass) {
        super(HttpStatus.NOT_FOUND, "Cannot find resource of type {%s} with id {%s}".formatted(resourceClass.getSimpleName(), id.toString()));
        this.id = id;
        this.resourceClass = resourceClass;
    }
}

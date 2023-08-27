package be.technifutur.spring.demo.controllers;

import be.technifutur.spring.demo.exceptions.*;
import be.technifutur.spring.demo.models.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

// Cette classe permet de gérer les exceptions globalement pour tous les contrôleurs
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    // Gère l'exception ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handle(ResourceNotFoundException ex, HttpServletRequest req) {
        // Obtient les détails de la requête
        String uri = req.getRequestURI();
        String method = req.getMethod();

        // Crée un objet d'erreur avec les détails appropriés
        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());
        error.put("id", ex.getId());
        error.put("resourceType", ex.getResourceClass().getSimpleName());

        ErrorDTO body = ErrorDTO.builder()
                .uri(uri)
                .method(method)
                .errors(Set.of(error))
                .build();

        // Retourne une réponse avec le statut HTTP NOT_FOUND et l'objet d'erreur
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(body);
    }

    // Gère l'exception ResourceAlreadyLinkedException
    @ExceptionHandler(ResourceAlreadyLinkedException.class)
    public ResponseEntity<ErrorDTO> handle(ResourceAlreadyLinkedException ex, HttpServletRequest req) {
        // Obtient les détails de la requête
        String uri = req.getRequestURI();
        String method = req.getMethod();

        // Crée un objet d'erreur avec les détails appropriés
        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());
        error.put("containerType", ex.getContainingClazz().getSimpleName());
        error.put("containerId", ex.getContainingId());
        error.put("containedType", ex.getContainedClazz().getSimpleName());
        error.put("containedId", ex.getContainedId());

        ErrorDTO body = ErrorDTO.builder()
                .uri(uri)
                .method(method)
                .errors(Set.of(error))
                .build();

        // Retourne une réponse avec le statut HTTP CONFLICT et l'objet d'erreur
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(body);
    }

    // Gère l'exception UniqueViolationException
    @ExceptionHandler(UniqueViolationException.class)
    public ResponseEntity<ErrorDTO> handle(UniqueViolationException ex, HttpServletRequest req) {
        // Obtient les détails de la requête
        String uri = req.getRequestURI();
        String method = req.getMethod();

        // Crée un ensemble d'erreurs pour chaque champ en violation
        Set<Map<String, Object>> errors = new LinkedHashSet<>();
        ex.getFieldNames().forEach(
                field -> {
                    Map<String, Object> errorData = new HashMap<>();
                    errorData.put(field, "should be unique");
                    errors.add(errorData);
                }
        );

        // Retourne une réponse avec le statut HTTP CONFLICT et l'objet d'erreur
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(
                        ErrorDTO.builder()
                                .uri(uri)
                                .method(method)
                                .errors(errors)
                                .build()
                );
    }

    // Gère l'exception MethodArgumentNotValidException

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // Obtient les détails de la requête
        HttpServletRequest req = ((ServletWebRequest) request).getRequest();
        String uri = req.getRequestURI();
        String method = req.getMethod();

        // Crée un ensemble d'erreurs pour chaque erreur de validation
        Set<Map<String, Object>> errors = new LinkedHashSet<>();

        ex.getGlobalErrors().forEach(
                error -> {
                    Map<String, Object> errorData = new HashMap<>();
                    errorData.put("message", error.getDefaultMessage());
                    errorData.put("type", "global");
                    errors.add(errorData);
                }
        );

        ex.getFieldErrors().forEach(
                error -> {
                    Map<String, Object> errorData = new HashMap<>();
                    errorData.put("message", error.getDefaultMessage());
                    errorData.put("type", "field");
                    errorData.put("fieldName", error.getField());
                    errors.add(errorData);
                }
        );

        // Retourne une réponse avec le statut HTTP BAD_REQUEST et l'objet d'erreur
        return ResponseEntity.badRequest()
                .body(
                        ErrorDTO.builder()
                                .uri(uri)
                                .method(method)
                                .errors(errors)
                                .build()
                );
    }
}

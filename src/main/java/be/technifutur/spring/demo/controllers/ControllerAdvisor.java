package be.technifutur.spring.demo.controllers;

import be.technifutur.spring.demo.exceptions.ResourceNotFoundException;
import be.technifutur.spring.demo.models.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


// Permet une gestion d'erreur sur tous les controllers
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handle(ResourceNotFoundException ex, HttpServletRequest req){
        String uri = req.getRequestURI();
        String method = req.getMethod();

        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());
        error.put("id", ex.getId());
        error.put("resourceType", ex.getResourceClass().getSimpleName());

        ErrorDTO body = ErrorDTO.builder()
                .uri( uri )
                .method( method )
                .errors( Set.of(error) )
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(body);
    }

}
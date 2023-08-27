package be.technifutur.spring.demo.controllers.advice.test;

import be.technifutur.spring.demo.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Ce conseiller de contrôleur gère les exceptions pour les contrôleurs du package 'be.technifutur.spring.demo.controllers.advice.test'
@ControllerAdvice("be.technifutur.spring.demo.controllers.advice.test")
//@ControllerAdvice(basePackages = {"be.technifutur.spring.demo.controllers.advice.test"})
public class AdviceTestHandlers {

    // Gère les exceptions de type ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handle(){
        return ResponseEntity.badRequest()
                .body("ce @ControllerAdvice ne fonctionne que pour les @Controller ou @RestController" +
                        " du package 'be.technifutur.spring.demo.controllers.advice.test'");
    }
}

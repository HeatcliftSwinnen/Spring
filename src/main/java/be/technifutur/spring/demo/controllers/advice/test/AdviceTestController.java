package be.technifutur.spring.demo.controllers.advice.test;

import be.technifutur.spring.demo.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Ce contrôleur est destiné à tester la gestion des exceptions
@RestController
@RequestMapping("/advice/test")
public class AdviceTestController {

    // Lorsque cet endpoint est appelé, il lève une exception ResourceNotFoundException
    @GetMapping("/break")
    public void breakThis(){
        throw new ResourceNotFoundException(0L, Object.class);
    }
}

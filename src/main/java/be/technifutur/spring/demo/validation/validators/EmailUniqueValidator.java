package be.technifutur.spring.demo.validation.validators;

import be.technifutur.spring.demo.service.GamerService;
import be.technifutur.spring.demo.validation.contraints.EmailUnique;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {

    private final GamerService gamerService;

    public EmailUniqueValidator(GamerService gamerService) {
        this.gamerService = gamerService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !gamerService.isEmailTaken(email);
    }
}

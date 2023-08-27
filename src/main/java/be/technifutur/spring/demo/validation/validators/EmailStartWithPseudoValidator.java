package be.technifutur.spring.demo.validation.validators;

import be.technifutur.spring.demo.models.form.GamerForm;
import be.technifutur.spring.demo.validation.contraints.EmailStartWithPseudo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailStartWithPseudoValidator implements ConstraintValidator<EmailStartWithPseudo, GamerForm> {

    @Override
    public boolean isValid(GamerForm value, ConstraintValidatorContext context) {
        return value.getEmail().startsWith(value.getPseudo());
    }
}
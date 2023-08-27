package be.technifutur.spring.demo.validation.validators;

import be.technifutur.spring.demo.validation.contraints.IsOk;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class IsOkValidator implements ConstraintValidator<IsOk, CharSequence> {
    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        return value == null || value.equals("ok");
    }

}

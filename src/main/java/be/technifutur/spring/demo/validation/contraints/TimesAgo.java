package be.technifutur.spring.demo.validation.contraints;

import be.technifutur.spring.demo.validation.validators.TimesAgoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.core.annotation.AliasFor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimesAgoValidator.class)
public @interface TimesAgo {
    String message() default "no enough in the past";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @AliasFor("years")
    int value() default 12;

    @AliasFor("value")
    int years() default 12;
}

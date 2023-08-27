package be.technifutur.spring.demo.validation.contraints;

import be.technifutur.spring.demo.validation.validators.ExcludeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExcludeValidator.class)
public @interface Exclude {
    String message() default "using forbidden values";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


    Class<? extends Enum<?>> enumClazz();
    String[] values();

}

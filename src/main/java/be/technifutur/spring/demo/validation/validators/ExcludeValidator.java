package be.technifutur.spring.demo.validation.validators;

import be.technifutur.spring.demo.validation.contraints.Exclude;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ExcludeValidator implements ConstraintValidator<Exclude, Collection<? extends Enum<?>>> {

    private Class<? extends Enum<?>> enumClass;
    private String[] excludedValues;

    @Override
    public void initialize(Exclude constraintAnnotation) {
        enumClass = constraintAnnotation.enumClazz();
        excludedValues = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(Collection<? extends Enum<?>> value, ConstraintValidatorContext context) {
        try {
            Method method = enumClass.getMethod("valueOf", Class.class, String.class);

            List<? extends Enum<?>> enumExcludedValues = Arrays.stream(excludedValues)
                    .map( v -> {
                        try {
                            // exemple: Platform.valueOf(Platform.class, "PC");
                            return (Enum<?>)method.invoke(null, enumClass, v);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();
            return value.stream().noneMatch(enumExcludedValues::contains);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}

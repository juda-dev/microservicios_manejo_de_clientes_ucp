package agendia.manejo_clientes.validation;

import agendia.manejo_clientes.validation.impl.ExistsEmailValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsEmailValidation.class)
public @interface ExistsEmail {
    String message() default "There is already a customer with this email";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

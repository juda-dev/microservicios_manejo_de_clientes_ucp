package agendia.manejo_clientes.validation;

import agendia.manejo_clientes.validation.impl.ExistsPhoneValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsPhoneValidation.class)
public @interface ExistsPhone {
    String message() default "There is already a customer with this phone";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

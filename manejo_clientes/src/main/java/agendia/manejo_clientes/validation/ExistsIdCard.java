package agendia.manejo_clientes.validation;

import agendia.manejo_clientes.validation.impl.ExistsIdCardValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsIdCardValidation.class)
public @interface ExistsIdCard {

    String message() default "There is already a customer with this identity card";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

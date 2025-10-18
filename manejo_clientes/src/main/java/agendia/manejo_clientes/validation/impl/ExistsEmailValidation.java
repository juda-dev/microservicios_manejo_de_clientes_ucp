package agendia.manejo_clientes.validation.impl;

import agendia.manejo_clientes.service.ClientService;
import agendia.manejo_clientes.validation.ExistsEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ExistsEmailValidation implements ConstraintValidator<ExistsEmail, String> {

    private final ClientService clientService;

    public ExistsEmailValidation(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !clientService.existsByEmail(email);
    }
}

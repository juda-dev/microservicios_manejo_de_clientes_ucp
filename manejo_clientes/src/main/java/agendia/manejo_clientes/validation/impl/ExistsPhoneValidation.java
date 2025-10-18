package agendia.manejo_clientes.validation.impl;

import agendia.manejo_clientes.service.ClientService;
import agendia.manejo_clientes.validation.ExistsPhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ExistsPhoneValidation implements ConstraintValidator<ExistsPhone, Long> {

    private final ClientService clientService;

    public ExistsPhoneValidation(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean isValid(Long phone, ConstraintValidatorContext context) {
        return !clientService.existsByPhone(phone);
    }
}

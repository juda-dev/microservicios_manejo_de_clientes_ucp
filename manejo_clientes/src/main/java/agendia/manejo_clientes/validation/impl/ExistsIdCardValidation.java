package agendia.manejo_clientes.validation.impl;

import agendia.manejo_clientes.service.ClientService;
import agendia.manejo_clientes.validation.ExistsIdCard;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ExistsIdCardValidation implements ConstraintValidator<ExistsIdCard, Long> {

    private final ClientService clientService;

    public ExistsIdCardValidation(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean isValid(Long idCard, ConstraintValidatorContext context) {
        return !clientService.existsByIdCard(idCard);
    }
}

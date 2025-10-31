package agendia.manejo_clientes.service;

import agendia.manejo_clientes.model.dto.CustomerRequest;
import agendia.manejo_clientes.model.dto.EmailResponse;

public interface SendEmailService {

    EmailResponse sendVerificationEmail(CustomerRequest request);
}

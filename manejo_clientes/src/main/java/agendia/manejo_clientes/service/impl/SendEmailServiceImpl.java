package agendia.manejo_clientes.service.impl;

import agendia.manejo_clientes.model.dto.CustomerRequest;
import agendia.manejo_clientes.model.dto.EmailResponse;
import agendia.manejo_clientes.service.CustomerService;
import agendia.manejo_clientes.service.EmailService;
import agendia.manejo_clientes.service.SendEmailService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SendEmailServiceImpl implements SendEmailService {

    private final CustomerService customerService;
    private final EmailService emailService;

    public SendEmailServiceImpl(CustomerService customerService, EmailService emailService) {
        this.customerService = customerService;
        this.emailService = emailService;
    }


    @Override
    public EmailResponse sendVerificationEmail(CustomerRequest request) {
        customerService.upsert(request);
        emailService.sendVerificationCode(request);
        return new EmailResponse(request.email(), LocalDateTime.now());
    }
}

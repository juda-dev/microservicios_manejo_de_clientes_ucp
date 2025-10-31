package agendia.manejo_clientes.service;

import agendia.manejo_clientes.model.dto.ConfirmationRequest;
import agendia.manejo_clientes.model.dto.ConfirmationResponse;

public interface VerificationCodeService {

    String generateVerificationCode(String customerEmail);
    ConfirmationResponse codeValidation(ConfirmationRequest request);
}

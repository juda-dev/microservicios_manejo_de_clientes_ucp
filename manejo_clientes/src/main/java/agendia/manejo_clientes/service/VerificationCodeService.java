package agendia.manejo_clientes.service;

public interface VerificationCodeService {

    String generateVerificationCode(String customerEmail);
}

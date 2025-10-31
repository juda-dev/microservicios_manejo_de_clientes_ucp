package agendia.manejo_clientes.service;

import agendia.manejo_clientes.model.dto.CustomerRequest;

import java.util.concurrent.CompletableFuture;

public interface EmailService {

    CompletableFuture<Void> sendVerificationCode(CustomerRequest request);
}

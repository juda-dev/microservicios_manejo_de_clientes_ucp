package agendia.manejo_clientes.service;

import agendia.manejo_clientes.model.dto.CustomerRequest;

import java.util.concurrent.CompletableFuture;

public interface EmailService {

    CompletableFuture<Boolean> sendVerificationCode(CustomerRequest request);
}

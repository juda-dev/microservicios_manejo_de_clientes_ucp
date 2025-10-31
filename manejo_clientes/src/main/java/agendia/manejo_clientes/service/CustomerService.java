package agendia.manejo_clientes.service;

import agendia.manejo_clientes.model.dto.CustomerRequest;
import agendia.manejo_clientes.model.dto.CustomerResponse;
import agendia.manejo_clientes.model.entity.CustomerEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CustomerService {

    CompletableFuture <Void> upsert(CustomerRequest customerRequest);

    List<CustomerResponse> findAll();

    void deleteByEmail(String email);

    CustomerResponse findByEmail(String email);
}

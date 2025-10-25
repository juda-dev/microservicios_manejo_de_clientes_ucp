package agendia.manejo_clientes.service;

import agendia.manejo_clientes.model.dto.ClientRequest;
import agendia.manejo_clientes.model.dto.ClientResponse;
import agendia.manejo_clientes.model.entity.ClientEntity;

import java.util.List;

public interface ClientService {
    boolean existsByIdCard(Long idCard);
    boolean existsByEmail(String email);
    boolean existsByPhone(Long phone);

    ClientEntity save(ClientRequest clientRequest);

    List<ClientResponse> findAll();

    ClientResponse update(ClientRequest clientRequest);

    ClientResponse findByIdCard(Long idCard);

    void deleteByIdCard(Long idCard);
}

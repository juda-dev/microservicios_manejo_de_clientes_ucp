package agendia.manejo_clientes.service;

import agendia.manejo_clientes.model.dto.ClientRequest;
import agendia.manejo_clientes.model.entity.ClientEntity;

import java.util.List;

public interface ClientService {
    boolean existsByIdCard(Long idCard);
    boolean existsByEmail(String email);
    boolean existsByPhone(Long phone);
    ClientEntity save(ClientRequest clientRequest);

    List<ClientEntity> findAll();

    void deleteById(Long id);

    ClientEntity update(ClientEntity clientEntity);

    ClientEntity findById(Long id);
}

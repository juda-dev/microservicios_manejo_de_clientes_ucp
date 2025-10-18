package agendia.manejo_clientes.service;

import agendia.manejo_clientes.model.dto.ClientRequest;
import agendia.manejo_clientes.model.entity.ClientEntity;

public interface ClientService {

    boolean existsByIdCard(Long idCard);
    boolean existsByEmail(String email);
    boolean existsByPhone(Long phone);
    ClientEntity save(ClientRequest clientRequest);

}

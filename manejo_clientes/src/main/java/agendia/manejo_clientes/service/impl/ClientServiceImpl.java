package agendia.manejo_clientes.service.impl;

import agendia.manejo_clientes.mapper.ClientMapper;
import agendia.manejo_clientes.model.dto.ClientRequest;
import agendia.manejo_clientes.model.entity.ClientEntity;
import agendia.manejo_clientes.repository.ClientRepository;
import agendia.manejo_clientes.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByIdCard(Long idCard) {
        return clientRepository.existsByIdCard(idCard);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByPhone(Long phone) {
        return clientRepository.existsByPhone(phone);
    }

    @Transactional
    @Override
    public ClientEntity save(ClientRequest clientRequest) {
        ClientEntity clientEntity = ClientMapper.requestToEntity(clientRequest);
        return clientRepository.save(clientEntity);
    }
}

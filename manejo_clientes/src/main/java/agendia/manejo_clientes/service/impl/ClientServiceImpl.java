package agendia.manejo_clientes.service.impl;

import agendia.manejo_clientes.mapper.ClientMapper;
import agendia.manejo_clientes.model.dto.ClientRequest;
import agendia.manejo_clientes.model.dto.ClientResponse;
import agendia.manejo_clientes.model.entity.ClientEntity;
import agendia.manejo_clientes.repository.ClientRepository;
import agendia.manejo_clientes.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    @Override
    public List<ClientResponse> findAll() {
        return clientRepository.findAll().stream().map(ClientMapper::EntityToResponse).toList();
    }


    @Transactional(readOnly = true)
    @Override
    public ClientResponse findByIdCard(Long idCard) {
        return ClientMapper.EntityToResponse(clientRepository.findByIdCard(idCard).orElseThrow());
    }


    @Transactional
    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ClientResponse update(ClientRequest clientRequest) {
        ClientEntity clientEntity = clientRepository.findByIdCard(clientRequest.idCard()).orElseThrow();

        clientEntity.setFullname(clientRequest.fullname());
        clientEntity.setEmail(clientRequest.email());
        clientEntity.setPhone(clientRequest.phone());

        return ClientMapper.EntityToResponse(clientRepository.save(clientEntity));
    }

    @Transactional
    @Override
    public void deleteByIdCard(Long idCard) {
        clientRepository.deleteByIdCard(idCard);
    }
}

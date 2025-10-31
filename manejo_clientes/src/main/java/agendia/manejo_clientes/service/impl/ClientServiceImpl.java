package agendia.manejo_clientes.service.impl;

import agendia.manejo_clientes.exceptions.ClientNotFoundException;
import agendia.manejo_clientes.mapper.ClientMapper;
import agendia.manejo_clientes.model.dto.ClientRequest;
import agendia.manejo_clientes.model.dto.ClientResponse;
import agendia.manejo_clientes.model.entity.ClientEntity;
import agendia.manejo_clientes.repository.ClientRepository;
import agendia.manejo_clientes.service.ClientService;
import agendia.manejo_clientes.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    @Autowired
    private EmailService emailService;

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

        if(clientEntity.getEmail() != null && !clientEntity.getEmail().isEmpty()){
            String subject = "Successful date";
            String content = "Hello "+ clientEntity.getFullname()+" Your appointment was successfully scheduled";
            emailService.sendEmail(clientEntity.getEmail(),subject,content);
        }

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
        return ClientMapper.EntityToResponse(clientRepository.findByIdCard(idCard)
                .orElseThrow(ClientNotFoundException::new));
    }

    @Transactional
    @Override
    public ClientResponse update(ClientRequest clientRequest) {
        ClientEntity clientEntity = clientRepository.findByIdCard(clientRequest.idCard())
                .orElseThrow(ClientNotFoundException::new);

        clientEntity.setFullname(clientRequest.fullname());
        clientEntity.setEmail(clientRequest.email());
        clientEntity.setPhone(clientRequest.phone());

        return ClientMapper.EntityToResponse(clientRepository.save(clientEntity));
    }

    @Transactional
    @Override
    public void deleteByIdCard(Long idCard) {
        if (!clientRepository.existsByIdCard(idCard)){
            throw new ClientNotFoundException();
        }
        clientRepository.deleteByIdCard(idCard);
    }
}

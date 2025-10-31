package agendia.manejo_clientes.service.impl;

import agendia.manejo_clientes.exceptions.ClientNotFoundException;
import agendia.manejo_clientes.mapper.CustomerMapper;
import agendia.manejo_clientes.model.dto.CustomerRequest;
import agendia.manejo_clientes.model.dto.CustomerResponse;
import agendia.manejo_clientes.model.entity.CustomerEntity;
import agendia.manejo_clientes.repository.CustomerRepository;
import agendia.manejo_clientes.service.CustomerService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Async("taskExecutor")
    @Transactional
    @Override
    public CompletableFuture<Void> upsert(CustomerRequest customerRequest) {
        if (customerRepository.findByEmail(customerRequest.email()).isPresent()){
            CustomerEntity customerEntity = customerRepository
                    .findByEmail(customerRequest.email()).get();

            CustomerEntity updateCustomerEntity = CustomerMapper.requestToEntity(customerRequest);
                updateCustomerEntity.setId(customerEntity.getId());

            CustomerResponse customerResponse = CustomerMapper.EntityToResponse(customerRepository.save(updateCustomerEntity));

            return CompletableFuture.completedFuture(null);
        }
        CustomerEntity customerEntity = CustomerMapper.requestToEntity(customerRequest);
        CustomerResponse customerResponse = CustomerMapper.EntityToResponse(customerRepository.save(customerEntity));

        return CompletableFuture.completedFuture(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerResponse> findAll() {
        return customerRepository.findAll().stream().map(CustomerMapper::EntityToResponse).toList();
    }

    @Transactional
    @Override
    public void deleteByEmail(String email) {
        if (!customerRepository.existsByEmail(email)){
            throw new ClientNotFoundException();
        }
        customerRepository.deleteByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponse findByEmail(String email) {
        return CustomerMapper.EntityToResponse(customerRepository
                .findByEmail(email).orElseThrow(() ->  new ClientNotFoundException()));
    }
}

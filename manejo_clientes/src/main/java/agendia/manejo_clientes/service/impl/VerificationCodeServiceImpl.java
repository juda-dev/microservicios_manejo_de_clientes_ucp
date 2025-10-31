package agendia.manejo_clientes.service.impl;

import agendia.manejo_clientes.exceptions.IncorrectVerificationCodeException;
import agendia.manejo_clientes.exceptions.VerificationCodeExpiredException;
import agendia.manejo_clientes.exceptions.VerificationCodeNotFoundException;
import agendia.manejo_clientes.model.dto.ConfirmationRequest;
import agendia.manejo_clientes.model.dto.ConfirmationResponse;
import agendia.manejo_clientes.model.dto.CustomerUserId;
import agendia.manejo_clientes.model.entity.CustomerEntity;
import agendia.manejo_clientes.model.entity.CustomerUserEntity;
import agendia.manejo_clientes.model.entity.VerificationCodeEntity;
import agendia.manejo_clientes.repository.CustomerUserRepository;
import agendia.manejo_clientes.repository.VerificationCodeRepository;
import agendia.manejo_clientes.service.CustomerService;
import agendia.manejo_clientes.service.VerificationCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;
    private final CustomerService customerService;
    private final CustomerUserRepository customerUserRepository;

    public VerificationCodeServiceImpl(VerificationCodeRepository verificationCodeRepository, CustomerService customerService, CustomerUserRepository customerUserRepository) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.customerService = customerService;
        this.customerUserRepository = customerUserRepository;
    }

    @Transactional
    @Override
    public String generateVerificationCode(String customerEmail) {
        if (verificationCodeRepository.findById(customerEmail).isPresent()){
            VerificationCodeEntity entity = verificationCodeRepository.findById(customerEmail).get();
            if (!LocalDateTime.now().isAfter(entity.getExpirationDate())){
                return entity.getCode();
            }
        }
        Random random = new Random();
        int num = random.nextInt(1000000);
        String code = String.format("%06d", num);

        VerificationCodeEntity verificationCode = new VerificationCodeEntity(customerEmail
                , code
                , LocalDateTime.now().plusMinutes(20));

        verificationCode = verificationCodeRepository.save(verificationCode);

        return verificationCode.getCode();
    }

    @Transactional
    @Override
    public ConfirmationResponse codeValidation(ConfirmationRequest request) {
        CustomerEntity customer = customerService.findEntityByEmail(request.email());
        VerificationCodeEntity verificationCode = verificationCodeRepository
                .findById(request.email()).orElseThrow(VerificationCodeNotFoundException::new);
        if (!verificationCode.getCode().equals(request.code())){
            throw new IncorrectVerificationCodeException();
        }
        if (LocalDateTime.now().isAfter(verificationCode.getExpirationDate())){
            throw new VerificationCodeExpiredException();
        }
        CustomerUserId id = new CustomerUserId(customer.getId(), request.userId());
        CustomerUserEntity customerUserEntity = new CustomerUserEntity(id);

        customerUserRepository.save(customerUserEntity);

        return new ConfirmationResponse(customer.getFullName()
                , request.userId()
                , LocalDateTime.now());
    }

}

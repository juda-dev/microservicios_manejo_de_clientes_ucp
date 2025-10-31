package agendia.manejo_clientes.service.impl;

import agendia.manejo_clientes.model.entity.VerificationCodeEntity;
import agendia.manejo_clientes.repository.VerificationCodeRepository;
import agendia.manejo_clientes.service.VerificationCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;

    public VerificationCodeServiceImpl(VerificationCodeRepository verificationCodeRepository) {
        this.verificationCodeRepository = verificationCodeRepository;
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
}

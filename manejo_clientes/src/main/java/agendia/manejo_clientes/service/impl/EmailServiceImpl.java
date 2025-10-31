package agendia.manejo_clientes.service.impl;

import agendia.manejo_clientes.model.dto.CustomerRequest;
import agendia.manejo_clientes.model.entity.VerificationCodeEntity;
import agendia.manejo_clientes.repository.VerificationCodeRepository;
import agendia.manejo_clientes.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final VerificationCodeRepository verificationCodeRepository;

    public EmailServiceImpl(JavaMailSender mailSender, SpringTemplateEngine templateEngine, VerificationCodeRepository verificationCodeRepository) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.verificationCodeRepository = verificationCodeRepository;
    }

    @Async("taskExecutor")
    @Transactional
    @Override
    public CompletableFuture<Boolean> sendVerificationCode(CustomerRequest request) {
        try{
            VerificationCodeEntity verificationCode = generateCodeFormatted(request.email());

            Context context = new Context();
                context.setVariable("fullname", request.fullname());
                context.setVariable("code", verificationCode.getCode());

            String htmlContent = templateEngine.process("verification_email", context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                messageHelper.setTo(request.email());
                messageHelper.setSubject("Código de Verificación - Agendia");
                messageHelper.setText(htmlContent, true);

            mailSender.send(mimeMessage);

            return CompletableFuture.completedFuture(true);

        } catch (Exception e) {
            return CompletableFuture.completedFuture(false);
        }
    }

    public VerificationCodeEntity generateCodeFormatted(String email) {
        Random random = new Random();
        int num = random.nextInt(1000000);
        String code = String.format("%06d", num);

        VerificationCodeEntity verificationCode = new VerificationCodeEntity(email
                , code
                , LocalDateTime.now().plusMinutes(20));

        return verificationCodeRepository.save(verificationCode);
    }
}

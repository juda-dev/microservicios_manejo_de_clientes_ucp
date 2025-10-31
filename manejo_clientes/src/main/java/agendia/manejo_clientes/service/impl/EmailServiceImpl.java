package agendia.manejo_clientes.service.impl;

import agendia.manejo_clientes.model.dto.CustomerRequest;
import agendia.manejo_clientes.service.EmailService;
import agendia.manejo_clientes.service.VerificationCodeService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.concurrent.CompletableFuture;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final VerificationCodeService verificationCodeService;

    public EmailServiceImpl(JavaMailSender mailSender, SpringTemplateEngine templateEngine, VerificationCodeService verificationCodeService) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.verificationCodeService = verificationCodeService;
    }

    @Async("taskExecutor")
    @Transactional
    @Override
    public CompletableFuture<Void> sendVerificationCode(CustomerRequest request) {
        try{
            String verificationCode = verificationCodeService.generateVerificationCode(request.email());

            Context context = new Context();
                context.setVariable("fullname", request.fullname());
                context.setVariable("code", verificationCode);

            String htmlContent = templateEngine.process("verification_email", context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                messageHelper.setTo(request.email());
                messageHelper.setSubject("Código de Verificación - Agendia");
                messageHelper.setText(htmlContent, true);

            mailSender.send(mimeMessage);

            return CompletableFuture.completedFuture(null);

        } catch (Exception e) {
            return CompletableFuture.completedFuture(null);
        }
    }

}

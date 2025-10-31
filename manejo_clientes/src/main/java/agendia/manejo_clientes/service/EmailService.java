package agendia.manejo_clientes.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private final JavaMailSender emailSender;


    public EmailService (final JavaMailSender emailSender){
        this.emailSender = emailSender;
    }
    
    public void sendEmail(String to , String subject , String content){
        try {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(message,true,"UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);

        emailSender.send(message);
        }catch (MessagingException e){
            throw new RuntimeException("Error : "+e);
        }
    }
}

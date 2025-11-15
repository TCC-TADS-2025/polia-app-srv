package br.com.tads.polia.poliaappsrv.infrastructure.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {

    @Autowired(required = false)
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.from:noreply@polia.com}")
    private String fromEmail;

    public void sendPasswordRecoveryEmail(String recipientEmail, String newPassword) throws Exception {
        log.info("Enviando email de recuperação de senha para: {}", recipientEmail);
        
        String emailBody = buildPasswordRecoveryEmailBody(newPassword);
        
        try {
            if (javaMailSender != null) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(fromEmail);
                message.setTo(recipientEmail);
                message.setSubject("Recuperação de Senha - Polia");
                message.setText(emailBody);
                
                javaMailSender.send(message);
                log.info("Email de recuperação de senha enviado com sucesso para: {}", recipientEmail);
            } else {
                log.warn("JavaMailSender não configurado. Email será apenas registrado no log.");
                log.info("Email destinado a: {}\nCorpo do email:\n{}", recipientEmail, emailBody);
            }
        } catch (Exception e) {
            log.error("Erro ao enviar email de recuperação de senha para: {}", recipientEmail, e);
            throw new Exception("Falha ao enviar email de recuperação de senha", e);
        }
    }

    private String buildPasswordRecoveryEmailBody(String newPassword) {
        return "Olá,\n\n" +
               "Sua senha foi redefinida com sucesso.\n\n" +
               "Sua nova senha é: " + newPassword + "\n\n" +
               "Por favor, use essa senha para fazer login e altere-a por uma senha segura de sua escolha.\n\n" +
               "Atenciosamente,\n" +
               "Equipe Polia";
    }
}


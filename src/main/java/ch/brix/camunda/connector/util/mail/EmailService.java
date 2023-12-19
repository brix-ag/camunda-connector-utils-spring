package ch.brix.camunda.connector.util.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

/**
 * Server configuration
 * ====================
 * spring:
 *   thymeleaf:
 *     cache: false
 *   mail:
 *     default-encoding: UTF-8
 *     host: my.host.com
 *     username: username
 *     password: password
 *     port: 587
 *     properties:
 *       mail:
 *         smtp:
 *           auth: true
 *           starttls:
 *             enable: true
 *     protocol: smtp
 *     test-connection: false
 */
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    public void send(@NonNull Email email) throws MessagingException {
        Context context = new Context();
        context.setVariables(email.getVariables());
        if (email.getLocale() != null)
            context.setLocale(email.getLocale());
        String html = springTemplateEngine.process(email.getTemplate(), context);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
        helper.setSubject(email.getSubject());
        helper.setFrom(email.getFrom());
        helper.setTo(email.getTo().toArray(new String[0]));
        if (email.getCc() != null)
            helper.setCc(email.getCc().toArray(new String[0]));
        if (email.getBcc() != null)
            helper.setBcc(email.getBcc().toArray(new String[0]));
        helper.setText(html, true);
        javaMailSender.send(message);
    }

}

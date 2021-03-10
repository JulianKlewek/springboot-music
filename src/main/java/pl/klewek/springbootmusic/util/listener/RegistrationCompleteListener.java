package pl.klewek.springbootmusic.util.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.klewek.springbootmusic.model.User;
import pl.klewek.springbootmusic.service.UserService;
import pl.klewek.springbootmusic.service.VerificationTokenService;
import pl.klewek.springbootmusic.util.RegistrationCompleteEvent;

import java.util.UUID;

@Component
public class RegistrationCompleteListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final static Logger LOGGER = LogManager.getLogger(RegistrationCompleteListener.class);

    private JavaMailSender javaMailSender;
    private VerificationTokenService verificationTokenService;

    @Autowired
    public RegistrationCompleteListener(JavaMailSender javaMailSender, VerificationTokenService verificationTokenService) {
        this.javaMailSender = javaMailSender;
        this.verificationTokenService = verificationTokenService;
    }

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent registrationCompleteEvent) {
        this.sentConfirmationMail(registrationCompleteEvent);
    }

    private void sentConfirmationMail(RegistrationCompleteEvent completeEvent){
        User user = completeEvent.getUser();
        String token = UUID.randomUUID().toString();
        verificationTokenService.createVerificationToken(user,token);

        String emailAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = completeEvent.getAppUrl() + "/registrationConfirm?token=" + token;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("klewekspring@gmail.com");
        email.setTo(emailAddress);
        email.setSubject(subject);
        email.setText(String.format("http://localhost:8080%s",confirmationUrl));

        javaMailSender.send(email);
        LOGGER.info("Verification mail was sent from {} to {}", email.getFrom(), emailAddress);

    }

}

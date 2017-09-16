package com.softserve.edu.lv251.listeners;

import com.softserve.edu.lv251.constants.Constants;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.events.OnRegistrationCompleteEvent;
import com.softserve.edu.lv251.service.UserService;
import com.softserve.edu.lv251.service.VerificationTokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * Added by Pavlo Kuchereshko.
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Logger logger;

    @Override
    @Transactional
    public void onApplicationEvent(
            OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        //A UUID (Universally Unique Identifier) represents a 128-bit value.
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = messageSource.getMessage("messages.regConfirmTitle", null, LocaleContextHolder.getLocale());
        String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        String greetings = messageSource.getMessage("messages.regGreetings", null, LocaleContextHolder.getLocale())
                + " " + user.getFirstname() + " " + user.getLastname() + "!" + System.lineSeparator();
        String message = messageSource.getMessage("messages.regSuccessful", null, LocaleContextHolder.getLocale());

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(greetings + message + System.lineSeparator() + Constants.HOME + confirmationUrl);

        javaMailSender.send(simpleMailMessage);
        logger.info(simpleMailMessage);
    }
}

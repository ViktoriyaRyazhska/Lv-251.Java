package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.constants.Constants;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

/**
 * Added by Pavlo Kuchereshko.
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Logger logger;

    @Override
    public void sendEmail(User user, String messageText) {

        MimeMessagePreparator mimeMessagePreparator = getMessagePreparator(user, messageText);

        try {
            javaMailSender.send(mimeMessagePreparator);
            logger.info("Message to " + user.getEmail() + " sent!");
        } catch (MailException e) {
            logger.warn(e);
        }
    }

    /**
     * MimeMessagePreparator interface: It is the callback interface for the preparation of JavaMail MIME messages.
     *
     * TO	People required to take action.
     * CC	Kept informed of the content, but no actions required from them.
     * BCC	Receive the message without any of the other recipients knowing. Also used for larger mailings (over 50).
     *
     * @param user          to mail for
     * @param messageText   message for user
     * @return MimeMessagePreparator.
     */
    private MimeMessagePreparator getMessagePreparator(User user, String messageText) {

        return mimeMessage -> {
            mimeMessage.setFrom(Constants.Mail.MAIL);
            mimeMessage.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(user.getEmail()));
            String name = user.getFirstname() + " " + user.getLastname();
            mimeMessage.setText("Dear " + (name.trim().isEmpty() ? "patient" : name) + ", " + messageText);
            mimeMessage.setSubject("Clinic Lv251");
        };
    }
}

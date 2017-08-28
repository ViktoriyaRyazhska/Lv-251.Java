package com.softserve.edu.lv251.config;

import com.softserve.edu.lv251.constants.Constants;
import com.softserve.edu.lv251.dto.pojos.ContactDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 *
 */

@Component
public class MailComponent {
    final private String mes = "";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Logger logger;

    public boolean sendMail (ContactDTO contactDTO) {

        String mes = "Name: " + contactDTO.getName() + " Phone: "
                + contactDTO.getPhone() + " Email: " + contactDTO.getEmail() + " Message: " + contactDTO.getMessage();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(Constants.Mail.MAIL);
            messageHelper.setSubject(contactDTO.getSubject());
            messageHelper.setText(mes, true);
            javaMailSender.send(mimeMessage);
            return true;
        } catch (MessagingException | MailException e) {
            logger.error(e);
            return false;
        }
    }
}

package com.softserve.edu.lv251.service;

import com.softserve.edu.lv251.entity.User;

/**
 * Added by Pavlo Kuchereshko.
 *
 */
public interface MailService {
    void sendEmail(final User user, String messageText);
}

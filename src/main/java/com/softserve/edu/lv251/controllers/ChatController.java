package com.softserve.edu.lv251.controllers;
import com.softserve.edu.lv251.dto.pojos.MessagesDTO;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.service.MessageService;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yana Martynyak on 23.08.2017.
 */
@Controller
public class ChatController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;


@MessageMapping("/chat")
@SendTo("/topic/messages")
public MessagesDTO send(MessagesDTO message, Principal principal) throws Exception {
    User user=userService.findByEmail(principal.getName());
    String time = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
    message.setEmail(user.getEmail());
    message.setName(user.getFirstname());
    message.setLastname(user.getLastname());
    messageService.addMessage(message);
    message.setDate(time);
    return message;
}
}

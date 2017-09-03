package com.softserve.edu.lv251.controllers;
import com.softserve.edu.lv251.dto.pojos.Message;
import com.softserve.edu.lv251.dto.pojos.MessageDTO;
import com.softserve.edu.lv251.service.MessageService;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public MessageDTO send(Message message, Principal principal) throws Exception {
        message.setFrom(principal.getName());
        messageService.add(message);
        String time = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm").format(new Date());
        return new MessageDTO(message.getFrom(), message.getText(), time);
    }

}

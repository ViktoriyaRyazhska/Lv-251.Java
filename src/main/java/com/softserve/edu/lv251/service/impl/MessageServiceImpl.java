package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.MessageDAO;
import com.softserve.edu.lv251.dto.pojos.MessagesDTO;
import com.softserve.edu.lv251.entity.Message;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.service.MessageService;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Yana Martynyak on 24.08.2017.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDAO messageDAO;
    @Autowired
    private UserService userService;

    @Override
    public List<Message> getAll() {
        List<Message> messages = messageDAO.getAllEntities();

        messages.sort((p1, p2)-> (int)(p2.getDate().getTime() - p1.getDate().getTime()));
        return messages;
    }



    @Override
    public void addMessage(MessagesDTO messagesDTO) {
        User user= userService.findByEmail(messagesDTO.getEmail());
        Message message= new Message();
        message.setDate(new Date());
        message.setText(messagesDTO.getText());
        message.setFrom(user);
        messageDAO.addEntity(message);
    }
}

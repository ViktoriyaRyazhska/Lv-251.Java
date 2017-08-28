package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.MessageDAO;
import com.softserve.edu.lv251.entity.Message;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.service.MessageService;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return messageDAO.getAllEntities();
    }

    @Override
    public void add(com.softserve.edu.lv251.dto.pojos.Message message) {
      User user= userService.findByEmail(message.getFrom());
      Message message1= new Message();
      message1.setFrom(user);
      message1.setText(message.getText());
      message1.setDate(new Date());
      messageDAO.addEntity(message1);
    }
}

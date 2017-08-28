package com.softserve.edu.lv251.service;

import com.softserve.edu.lv251.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yana Martynyak on 24.08.2017.
 */
@Service
public interface MessageService  {
    List<Message> getAll();
    void add(com.softserve.edu.lv251.dto.pojos.Message message);
}

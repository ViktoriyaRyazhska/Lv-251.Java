package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.MessageDAO;
import com.softserve.edu.lv251.entity.Message;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Yana Martynyak on 24.08.2017.
 */
@Transactional
@Repository
public class MessageDAOImpl extends BaseDAOImpl<Message> implements MessageDAO  {

}

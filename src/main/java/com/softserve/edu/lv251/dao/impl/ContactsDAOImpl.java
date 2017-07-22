package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.ContactsDAO;
import com.softserve.edu.lv251.entity.Contacts;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

/**
 * Created by kilopo on 13.07.2017.
 */
@Transactional
@Repository
public class ContactsDAOImpl extends BaseDAOImpl<Contacts> implements ContactsDAO {
}

package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.TestDAO;
import com.softserve.edu.lv251.entity.Test;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Marian Brynetskyi on 13.07.2017.
 */
@Transactional
@Repository
public class TestDAOImpl extends BaseDAOImpl<Test> implements TestDAO {
}

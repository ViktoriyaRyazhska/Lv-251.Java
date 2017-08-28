package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.TestResultDAO;
import com.softserve.edu.lv251.entity.TestsResult;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Marian Brynetskyi on 13.07.2017.
 */
@Transactional
@Repository
public class TestResultDAOImpl extends BaseDAOImpl<TestsResult> implements TestResultDAO {
}

package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.TestsResultsDAO;
import com.softserve.edu.lv251.entity.TestsResults;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

/**
 * Created by kilopo on 13.07.2017.
 */
@Transactional
@Repository
public class TestsResultsDAOImpl extends BaseDAOImpl<TestsResults> implements TestsResultsDAO{
}

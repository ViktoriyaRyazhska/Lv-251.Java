package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.RespondDAO;
import com.softserve.edu.lv251.entity.Respond;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Marian Brynetskyi on 23.08.2017.
 */
@Transactional
@Repository
public class RespondDAOImpl extends BaseDAOImpl<Respond> implements RespondDAO {
}

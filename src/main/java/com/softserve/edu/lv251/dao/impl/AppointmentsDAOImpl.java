package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.AppointmentsDAO;
import com.softserve.edu.lv251.entity.Appointments;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

/**
 * Created by kilopo on 13.07.2017.
 */
@Transactional
@Repository
public class AppointmentsDAOImpl extends BaseDAOImpl<Appointments> implements AppointmentsDAO {
}

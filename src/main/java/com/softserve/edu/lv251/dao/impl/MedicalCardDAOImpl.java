package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.MedicalCardDAO;
import com.softserve.edu.lv251.entity.MedicalCard;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

/**
 * Created by kilopo on 13.07.2017.
 */
@Transactional
@Repository
public class MedicalCardDAOImpl extends BaseDAOImpl<MedicalCard>  implements MedicalCardDAO {
}

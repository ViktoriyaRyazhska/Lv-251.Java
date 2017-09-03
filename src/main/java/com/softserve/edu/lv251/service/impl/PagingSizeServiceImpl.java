package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.BaseDAO;
import com.softserve.edu.lv251.service.PagingSizeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public abstract class PagingSizeServiceImpl<T> implements PagingSizeService<T> {

    @Override
    public int numberOfPaging(Integer size) {
        int n = getDao().getAllEntities().size();
        return ((int) Math.ceil((double) n / size));
    }

    @Override
    public List<T> getEntities(Integer chainIndex, Integer size) {
        return getDao().pagination(chainIndex, size);
    }

    public abstract BaseDAO<T> getDao();
}

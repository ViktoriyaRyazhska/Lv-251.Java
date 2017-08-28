package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.VerificationTokenDAO;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.entity.VerificationToken;
import com.softserve.edu.lv251.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Added by Pavlo Kuchereshko.
 */
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    VerificationTokenDAO verificationTokenDAO;

    @Override
    public void addVerificationToken(VerificationToken token) {
        this.verificationTokenDAO.addEntity(token);
    }

    @Override
    public void updateVerificationToken(VerificationToken token) {
        this.verificationTokenDAO.updateEntity(token);
    }

    @Override
    public VerificationToken getVerificationTokenByID(Long tokenId) {
        return this.verificationTokenDAO.getEntityByID(tokenId);
    }

    @Override
    public List<VerificationToken> getVerificationTokensByColumnNameAndValue(String columnName, Object value) {
        return this.verificationTokenDAO.getEntitiesByColumnNameAndValue(columnName, value);
    }

    @Override
    public List<VerificationToken> getAllVerificationTokens() {
        return this.verificationTokenDAO.getAllEntities();
    }

    @Override
    public void deleteVerificationToken(VerificationToken token) {
        this.verificationTokenDAO.deleteEntity(token);
    }

    @Override
    public VerificationToken getFirst() {
        return this.verificationTokenDAO.getEntityByID(1L);
    }

    @Override
    public VerificationToken findByVerificationToken(String token) {
        List<VerificationToken> tokens = getVerificationTokensByColumnNameAndValue("token", token);
        return tokens.isEmpty() ? null : tokens.get(0);
    }

    @Override
    public VerificationToken findByUser(User user) {
        List<VerificationToken> tokens = getVerificationTokensByColumnNameAndValue("user", user);
        return tokens.isEmpty() ? null : tokens.get(0);
    }
}

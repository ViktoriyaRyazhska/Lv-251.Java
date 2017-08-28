package com.softserve.edu.lv251.service;


import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.entity.VerificationToken;

import java.util.List;

/**
 * Added by Pavlo Kuchereshko.
 *
 */
public interface VerificationTokenService {

    void addVerificationToken(VerificationToken token);

    void updateVerificationToken(VerificationToken token);

    VerificationToken getVerificationTokenByID(Long tokenId);

    List<VerificationToken> getVerificationTokensByColumnNameAndValue(String columnName, Object value);

    List<VerificationToken> getAllVerificationTokens();

    void deleteVerificationToken(VerificationToken token);

    VerificationToken getFirst();

    VerificationToken findByVerificationToken(String token);

    VerificationToken findByUser(User user);
}

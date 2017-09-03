package com.softserve.edu.lv251.service;

import com.softserve.edu.lv251.dto.pojos.TokenAuthenticationDTO;

/**
 * Created by Taras on 03.08.2017.
 */
public interface GetTokenService {
    public TokenAuthenticationDTO getToken(String username, String password) throws Exception;
}

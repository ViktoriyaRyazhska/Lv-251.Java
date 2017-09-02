package com.softserve.edu.lv251.service.impl;

/**
 * Created by Taras on 03.08.2017.
 */

import com.softserve.edu.lv251.dto.pojos.TokenAuthenticationDTO;
import com.softserve.edu.lv251.idl.WebRoles;
import com.softserve.edu.lv251.service.GetTokenService;
import com.softserve.edu.lv251.service.UserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class GetTokenServiceImpl implements GetTokenService {

    @Autowired
    private Logger logger;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
     private UserService userService;

    @Override
    public TokenAuthenticationDTO getToken(String username, String password) throws Exception {
        if (username == null || password == null)
            return null;
        User user = (User) userDetailsService.loadUserByUsername(username);
        com.softserve.edu.lv251.entity.User userInfo= userService.findByEmail(username);
        Map<String, Object> tokenData = new HashMap<>();
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            tokenData.put("clientType", "user");
            tokenData.put("username", username);
            tokenData.put("token_create_date", new Date().getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 1);
            tokenData.put("token_expiration_date", calendar.getTime().getTime());
            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);

            String key = "key123";
            String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();

            TokenAuthenticationDTO authentication = new TokenAuthenticationDTO();
            authentication.setId(userInfo.getId());
            authentication.setName(userInfo.getFirstname());
            authentication.setLastName(userInfo.getLastname());
            authentication.setAddress(userInfo.getContact().getAddress());
            authentication.setCity(userInfo.getContact().getCity());
            authentication.setDistrict(userInfo.getContact().getDistrict().getName());
            authentication.setToken(token);
            authentication.setEmail(user.getUsername());
            
            for (GrantedAuthority authority : user.getAuthorities()) {
                logger.log(Priority.INFO, authority.getAuthority());
                if (authority.getAuthority().equals(WebRoles.ROLE_USER.name())) {
                    authentication.setIsUser(true);
                }
                if (authority.getAuthority().equals(WebRoles.ROLE_DOCTOR.name())) {
                    authentication.setIsDoctor(true);
                }
            }


            return authentication;
        } else {
            throw new Exception("Authentication error");
        }
    }

}

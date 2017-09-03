package com.softserve.edu.lv251.controllers.rest;

import com.softserve.edu.lv251.dto.pojos.TokenAuthenticationDTO;
import com.softserve.edu.lv251.dto.pojos.UserDTO;
import com.softserve.edu.lv251.dto.pojos.UserLoginDTO;
import com.softserve.edu.lv251.service.GetTokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Taras on 03.08.2017.
 */
@CrossOrigin(origins = {"*"})
@RestController
public class TokenAuthenticationController {

    @Autowired
    public GetTokenService getTokenService;

    @Autowired
    private Logger logger;

    @RequestMapping("rest/authandroid")
    public TokenAuthenticationDTO authAndroid(@RequestParam(name = "email") String email,
                                              @RequestParam(name = "password") String password) {
        try {
            return getTokenService.getToken(email, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "rest/auth",method = RequestMethod.POST)
    public ResponseEntity< TokenAuthenticationDTO >authCookie(@RequestBody UserLoginDTO userDTO, HttpServletResponse response) {
        try {
            String token = getTokenService.getToken(userDTO.getEmail(), userDTO.getPassword()).getToken();
            Cookie cookie = new Cookie("authToken", token);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return new ResponseEntity<TokenAuthenticationDTO>(getTokenService.getToken(userDTO.getEmail(), userDTO.getPassword()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<TokenAuthenticationDTO>(HttpStatus.UNAUTHORIZED);
        }
    }

}

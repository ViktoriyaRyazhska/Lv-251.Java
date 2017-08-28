package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.entity.Role;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.entity.security.UpdatableUserDetails;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitaliy Kovalevskyy
 */
@Service
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UpdatableUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    public void setNewEmail(String oldEmail, String newEmail) {
        User user = userService.findByEmail(oldEmail);
        user.setEmail(newEmail);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }

        return new UpdatableUserDetails(
                user.getEmail().toLowerCase(),
                user.getPassword(),
                true, /*enabled*/
                true, /*accountNonExpired*/
                true, /*credentialsNonExpired*/
                true, /*accountNonLocked*/
                getAuthorities(user.getRoles())
        );
    }

    private static List<GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return authorities;
    }
}

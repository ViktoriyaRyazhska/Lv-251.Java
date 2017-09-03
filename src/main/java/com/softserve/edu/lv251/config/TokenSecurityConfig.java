package com.softserve.edu.lv251.config;

/**
 * Created by Taras on 03.08.2017.
 */

import com.softserve.edu.lv251.idl.WebRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@Order(1)
public class TokenSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticationManager tokenAuthenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/rest/api/**")
                .csrf()
                .disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .addFilterBefore(restTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/rest/api/doctor/**")
                .hasAuthority(WebRoles.ROLE_DOCTOR.name())

                .antMatchers("/rest/api/user/**")
                .hasAuthority(WebRoles.ROLE_USER.name())

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint())
        ;
    }

    @Bean(name = "restTokenAuthenticationFilter")
    public TokenAuthenticationFilter restTokenAuthenticationFilter() {
        TokenAuthenticationFilter restTokenAuthenticationFilter = new TokenAuthenticationFilter();
        restTokenAuthenticationFilter.setAuthenticationManager(tokenAuthenticationManager);

        return restTokenAuthenticationFilter;
    }

}

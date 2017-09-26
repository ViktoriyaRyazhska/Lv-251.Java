package com.softserve.edu.lv251.config;

import com.softserve.edu.lv251.idl.WebRoles;
import com.softserve.edu.lv251.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@Order(2)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfig() {
        super(false);
    }

    /**
     * Added by Pavlo Kuchereshko.
     * Will be used to encode the raw password. Generally, a good encoding algorithm applies a SHA-1
     * or greater hash combined with an 8-byte or greater randomly generated salt.
     *
     * @return BCryptPasswordEncoder.
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Added by Marian Brynetskyi.
     * Configuration for Spring Securiry.
     *
     * @param http;
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()

                .authorizeRequests()
                .antMatchers("/user/*")
                .hasAnyAuthority(WebRoles.ROLE_USER.name())

                .antMatchers("/doctor/*")
                .hasAuthority(WebRoles.ROLE_DOCTOR.name())

                .antMatchers("/moderator/*")
                .hasAuthority(WebRoles.ROLE_MODERATOR.name())

                .antMatchers("/admin/*")
                .hasAuthority(WebRoles.ROLE_ADMIN.name())

                .and()

                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?error")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll()
                .and()

                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()

                .rememberMe()
        ;
    }


    /**
     * Added by Marian Brynetskyi.
     * Global security configurations with admin data.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
        auth.inMemoryAuthentication()
                .withUser("root@admin.com").password("root")
                .authorities(WebRoles.ROLE_USER.name(), WebRoles.ROLE_DOCTOR.name(), WebRoles.ROLE_ADMIN.name());
        auth.userDetailsService(customUserDetailsService);
    }

    /**
     * Added by Pavlo Kuchereshko.
     * A production-quality AuthenticationProvider implementation called DaoAuthenticationProvider.
     *
     * @return DaoAuthenticationProvider;
     */
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

}

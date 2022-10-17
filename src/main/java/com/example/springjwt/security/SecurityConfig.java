package com.example.springjwt.security;

import com.example.springjwt.constant.SecurityConstants;
import com.example.springjwt.filter.CustomAuthFilter;
import com.example.springjwt.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        private
        http.authorizeRequests().antMatchers(GET, "/api/users/**").hasAnyAuthority(SecurityConstants.ROLE_ADMIN, SecurityConstants.ROLE_MOD);
        http.authorizeRequests().antMatchers(POST, "/api/users/**").hasAnyAuthority(SecurityConstants.ROLE_ADMIN, SecurityConstants.ROLE_MOD);
        http.authorizeRequests().antMatchers(POST, "/api/locations/**").hasAnyAuthority(SecurityConstants.ROLE_ADMIN, SecurityConstants.ROLE_MOD);
        http.authorizeRequests().antMatchers(DELETE, "/api/locations/**").hasAnyAuthority(SecurityConstants.ROLE_ADMIN, SecurityConstants.ROLE_MOD);
//        public
        http.authorizeRequests().anyRequest().permitAll();
//        filter
        http.addFilter(new CustomAuthFilter(authenticationManager()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}

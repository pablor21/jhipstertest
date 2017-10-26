package com.github.client.security;

import com.github.client.domain.Token;
import com.github.client.service.GithubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component("userDetailsService")
public class GithubUserDetailsService implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(GithubUserDetailsService.class);
    @Inject
    private GithubService service;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        log.debug("Authenticating {}", token);
        return service.getUser(new Token(token));
    }
}

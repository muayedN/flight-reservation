package com.flight.reservation.authentication.service;

import com.flight.reservation.authentication.domain.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final AccountService accountService;

    @Autowired
    public JwtUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserPrincipal(
                accountService.findUserByUsername(username).orElseThrow(
                        () ->new UsernameNotFoundException("Authentication Failure")
                )
        );
    }
}

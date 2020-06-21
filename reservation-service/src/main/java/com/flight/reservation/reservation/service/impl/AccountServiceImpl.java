package com.flight.reservation.reservation.service.impl;

import com.flight.reservation.reservation.service.AccountService;

import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public UUID getLoggedInUserPublicId() {
        return UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }

}

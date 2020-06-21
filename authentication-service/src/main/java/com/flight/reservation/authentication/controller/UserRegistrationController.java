package com.flight.reservation.authentication.controller;

import com.flight.reservation.authentication.dto.RegistrationRequest;
import com.flight.reservation.authentication.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserRegistrationController {

    private final AccountService accountService;

    public UserRegistrationController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest){
        accountService.createUser(registrationRequest);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}

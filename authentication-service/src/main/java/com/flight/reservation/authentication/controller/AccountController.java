package com.flight.reservation.authentication.controller;

import com.flight.reservation.authentication.dto.UpdateRolesRequest;
import com.flight.reservation.authentication.dto.UserResponse;
import com.flight.reservation.authentication.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/user/{publicId}")
    @PreAuthorize("hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_SYSTEM')")
    public ResponseEntity<UserResponse> getUserByPublicId(@PathVariable String publicId) {
        return accountService.findUserByPublicId(publicId).map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping("/user/{publicId}/roles")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateRolesForUser(@PathVariable String publicId, @RequestBody UpdateRolesRequest updateRolesRequest){
        accountService.updateAssignedRolesForUser(publicId, updateRolesRequest.getRoles());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/details")
    public ResponseEntity<UserResponse> getLoggedInUsersDetail() {
        return accountService.findUserByPublicId(accountService.getLoggedInUserPublicId()).map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}

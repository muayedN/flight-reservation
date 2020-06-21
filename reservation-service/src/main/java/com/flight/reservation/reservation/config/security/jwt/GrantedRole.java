package com.flight.reservation.reservation.config.security.jwt;

import org.springframework.security.core.GrantedAuthority;

public class GrantedRole implements GrantedAuthority {
    private final RoleCode roleCode;

    public GrantedRole(RoleCode roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public String getAuthority() {
        return roleCode.name();
    }
}

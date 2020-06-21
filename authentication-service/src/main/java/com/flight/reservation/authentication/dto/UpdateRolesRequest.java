package com.flight.reservation.authentication.dto;

import com.flight.reservation.authentication.domain.RoleCode;

import java.util.Set;

public class UpdateRolesRequest {
    Set<RoleCode> roles;

    public Set<RoleCode> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleCode> roles) {
        this.roles = roles;
    }
}

package com.flight.reservation.authentication.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, length = 50)
    @Enumerated(EnumType.STRING)
    private RoleCode code;

    public Role(){}

    public Role(RoleCode code) {
        this.code = code;
    }

    @Override
    public String getAuthority() {
        return code.name();
    }

    public int getId(){
        return this.id;
    }
}

package com.flight.reservation.authentication.repository;

import com.flight.reservation.authentication.domain.Role;
import com.flight.reservation.authentication.domain.RoleCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByCode(RoleCode code);

    @Query("select r from Role r where r.code in (:roleCodes)")
    Set<Role> findAllByCodeIn(Set<RoleCode> roleCodes);

}

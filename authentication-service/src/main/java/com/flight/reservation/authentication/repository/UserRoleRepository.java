package com.flight.reservation.authentication.repository;

import com.flight.reservation.authentication.domain.User;
import com.flight.reservation.authentication.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    void deleteAllByUser(User user);
}

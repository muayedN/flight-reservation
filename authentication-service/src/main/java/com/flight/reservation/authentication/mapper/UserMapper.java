package com.flight.reservation.authentication.mapper;

import com.flight.reservation.authentication.domain.Role;
import com.flight.reservation.authentication.domain.User;
import com.flight.reservation.authentication.domain.UserRole;
import com.flight.reservation.authentication.dto.RegistrationRequest;
import com.flight.reservation.authentication.dto.UserResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User map(RegistrationRequest registrationRequest){
        User user = new User();
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setDateOfBirth(registrationRequest.getDateOfBirth());
        user.setEmail(registrationRequest.getEmail());
        return user;
    }

    public UserResponse map(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setPublicId(user.getPublicId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setDateOfBirth(user.getDateOfBirth());
        userResponse.setEmail(user.getEmail());
        userResponse.setRoles(user.getUserRoles().stream().map(UserRole::getRole).map(Role::getAuthority).collect(Collectors.toSet()));
        return userResponse;
    }
}

package com.flight.reservation.authentication.service;

import com.flight.reservation.authentication.domain.Role;
import com.flight.reservation.authentication.domain.RoleCode;
import com.flight.reservation.authentication.domain.User;
import com.flight.reservation.authentication.domain.UserRole;
import com.flight.reservation.authentication.dto.RegistrationRequest;
import com.flight.reservation.authentication.dto.UserResponse;
import com.flight.reservation.authentication.exceptions.EmailAlreadyExistsException;
import com.flight.reservation.authentication.exceptions.RoleDoesNotExistException;
import com.flight.reservation.authentication.exceptions.UserNotFoundException;
import com.flight.reservation.authentication.mapper.UserMapper;
import com.flight.reservation.authentication.repository.RoleRepository;
import com.flight.reservation.authentication.repository.UserRepository;
import com.flight.reservation.authentication.repository.UserRoleRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public AccountService(UserRepository userRepository, RoleRepository roleRepository,
                          UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder,
                          UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    public void createUser(@Valid RegistrationRequest registrationRequest) {
        if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        User user = userMapper.map(registrationRequest);
        user.setPublicId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        // all registered users have the passenger role by default
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(roleRepository.findByCode(RoleCode.ROLE_PASSENGER).map(role -> new UserRole(user, role))
                .orElseThrow(() -> new RoleDoesNotExistException(RoleCode.ROLE_PASSENGER)));

        User savedUser = userRepository.save(user);
        userRoles.forEach(userRole -> userRole.setUser(savedUser));
        userRoleRepository.saveAll(userRoles);
    }

    public Optional<UserResponse> findUserByPublicId(String publicId) {
        return userRepository.findByPublicId(publicId).map(userMapper::map);
    }

    public void updateAssignedRolesForUser(String publicId, Set<RoleCode> roleCodes) {
        User user = userRepository.findByPublicIdEagerLoadRole(publicId).orElseThrow(UserNotFoundException::new);
        Set<Role> roles = roleRepository.findAllByCodeIn(roleCodes);
        Set<UserRole> userRoles = roles.stream().map(role -> new UserRole(user, role)).collect(Collectors.toSet());
        userRoleRepository.deleteAll(user.getUserRoles());
        user.setUserRoles(new HashSet<>());
        userRoleRepository.saveAll(userRoles);
    }

    public String getLoggedInUserPublicId(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}

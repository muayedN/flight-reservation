package com.flight.reservation.email.service;

import com.flight.reservation.email.dto.UserResponse;

import java.util.UUID;

public interface UserService {
    UserResponse getUserWithPublicId(UUID publicId);
}

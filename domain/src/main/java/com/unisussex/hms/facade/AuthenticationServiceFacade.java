package com.unisussex.hms.facade;

import com.unisussex.hms.User;

import java.util.Optional;

public interface AuthenticationServiceFacade {
    Optional<User> authenticateUser(String username, String password);
}

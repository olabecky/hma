package com.unisussex.hms.facade;

import com.unisussex.hms.User;
import com.unisussex.hms.UserService;
import com.unisussex.hms.hibernate.enums.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static com.unisussex.hms.User.aUser;

@Service
public class AuthenticationServiceFacadeImpl implements AuthenticationServiceFacade {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceFacadeImpl.class);
    private final UserService userService;

    public AuthenticationServiceFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<User> authenticateUser(String username, String password){
        Optional<User> userOp = userService.getUserByUsername(username);
        if(!userOp.isPresent()){
            logger.info(String.format("User with username [%s] was not found", username));
            throw new IllegalArgumentException("Authentication failed, Invalid username password combination");
        }
        if(!Objects.equals(userOp.get().getUserStatus(), UserStatus.ACTIVE.name())){
            logger.info("User profile is inactive");
            throw new IllegalArgumentException("User profile is inactive");
        }
        if(!Objects.equals(userOp.get().getPassword(), password)){
            logger.info("Passwords do not match");
            throw new IllegalArgumentException("Authentication failed, Invalid username password combination");
        }
        User user = userOp.get();
        userService.updateUser(aUser()
                .lastLoginDate(new Date())
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .password(user.getPassword())
                .userStatus(user.getUserStatus())
                .build());
        return userOp;
    }
}

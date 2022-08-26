package com.unisussex.hms;

import com.unisussex.hms.exception.ResourceNotFoundException;
import com.unisussex.hms.facade.AuthenticationServiceFacade;
import com.unisussex.hms.util.Util;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@RestController
@Scope("session")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationServiceFacade authenticationServiceFacade;

    public AuthenticationController(AuthenticationServiceFacade authenticationServiceFacade) {
        this.authenticationServiceFacade = authenticationServiceFacade;
    }

    @PostMapping(value = "/authenticateUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity authenticateUser(@RequestBody UserLoginDto obj, HttpSession session) {
        if (Strings.isBlank(obj.getUsername())) {
            throw new IllegalArgumentException("Username was empty/null.");
        }

        if (Strings.isBlank(obj.getPassword())) {
            throw new IllegalArgumentException("Password was empty/null.");
        }
        session.removeAttribute("userInSession");

        Optional<User> opUser = authenticationServiceFacade.authenticateUser(obj.getUsername(), Util.hash(obj.getPassword()));
        if (opUser.isPresent()) {
            session.setAttribute("userInSession", opUser.stream().findFirst().get().getUsername());
            session.setAttribute("userInSessionRoleDescription", opUser.stream().findFirst().get().getRole().getDescription());
            session.setAttribute("userInSessionRoleId", opUser.stream().findFirst().get().getRole().getId());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    void handleResourceNotFound(HttpServletResponse response, ResourceNotFoundException ex) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    void handleBadRequests(HttpServletResponse response, IllegalArgumentException ex) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}

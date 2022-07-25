package com.unisussex.hms;

import com.unisussex.hms.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController  {

	private final UserService userService;


	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/user")
	public List<UserDto> getUsers() {

		  return this.userService.getAllUsers().stream()
				.map(this::convert)
				.collect(Collectors.toList());
	}

	private UserDto convert(User user) {
		return UserDto.aUserDto()
				.id(user.getId())
				.username(user.getUsername())
				.role(user.getRole())
				.build();
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

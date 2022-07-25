package com.unisussex.hms;

import com.unisussex.hms.exception.ResourceNotFoundException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

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

	@GetMapping("/user/{username}")
	public UserDto getUserByUsername(@PathVariable("username") String username) {
		System.out.println(":::::::::username::::::: "+username);
		return this.userService.getUserByUsername(username)
				.map(this::convert)
				.orElseThrow(() -> new ResourceNotFoundException("Not user with username '" + username + "'"));
	}

	@GetMapping("/userSearch")
	public List<UserDto> getUserByParam(@RequestParam(name = "param") String param) {
		System.out.println(":::::::::param::::::: "+param);
		if (Strings.isBlank(param)) {
			return this.userService.getAllUsers().stream()
					.map(this::convert)
					.collect(Collectors.toList());
		}
		return this.userService.getUsersByUsernameOrRole("%"+param.toLowerCase()+"%").stream()
				.map(this::convert)
				.collect(Collectors.toList());
	}

	@PostMapping(value = "/user")
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {

		if (userDto.getId() != null) {
			throw new IllegalArgumentException("To update an existing user you should be performing a PUT. To create a user, remove the ID as an ID will be assigned at creation.");
		}

		if (Strings.isBlank(userDto.getUsername())) {
			throw new IllegalArgumentException("Username was empty/null.");
		}

		if (Strings.isBlank(userDto.getRole())) {
			throw new IllegalArgumentException("Role was empty/null.");
		}

		User createdUser = this.userService.saveUser(User.aUser()
				.id(userDto.getId())
				.username(userDto.getUsername())
				.role(userDto.getRole())
				.build());

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(convert(createdUser));
	}

	@PutMapping(value = "/user/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") long id, @RequestBody UserDto userDto) {

		if (Strings.isBlank(userDto.getUsername())) {
			throw new IllegalArgumentException("Username was empty/null.");
		}

		if (Strings.isBlank(userDto.getRole())) {
			throw new IllegalArgumentException("Role was empty/null.");
		}

		User createdUser = this.userService.updateUser(User.aUser()
				.id(id)
				.username(userDto.getUsername())
				.role(userDto.getRole())
				.build());

		return ResponseEntity.status(HttpStatus.OK)
				.body(convert(createdUser));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/user/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		this.userService.delete(id);
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

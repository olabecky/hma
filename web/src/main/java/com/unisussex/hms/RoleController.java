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
public class RoleController {

	private final RoleService roleService;


	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping(value = "/role")
	public List<RoleDto> getAllSuperheroes() {

		return this.roleService.getAllRoles().stream()
				.map(this::convert)
				.collect(Collectors.toList());
	}

	@GetMapping("/role/{name}")
	public RoleDto getHeroByName(@PathVariable("name") String name) {
		return this.roleService.getRoleByName(name)
				.map(this::convert)
				.orElseThrow(() -> new ResourceNotFoundException("Not superhero with name '" + name + "'"));
	}

	@GetMapping("/roleSearch")
	public List<RoleDto> getRoleByParam(@RequestParam(name = "param") String param) {
		if (Strings.isBlank(param)) {
			return this.roleService.getAllRoles().stream()
					.map(this::convert)
					.collect(Collectors.toList());
		}
		return this.roleService.getRoleByParam("%"+param.toLowerCase()+"%").stream()
				.map(this::convert)
				.collect(Collectors.toList());
	}

	@PostMapping(value = "/role")
	public ResponseEntity<RoleDto> saveSuperhero(@RequestBody RoleDto superhero) {

		if (superhero.getId() != null) {
			throw new IllegalArgumentException("To update an existing hero you should be performing a PUT. To create a hero, remove the ID as an ID will be assigned at creation.");
		}

		if (Strings.isBlank(superhero.getName())) {
			throw new IllegalArgumentException("Name was empty/null.");
		}

		Role createdHero = this.roleService.saveRole(Role.aRole()
				.id(superhero.getId())
				.name(superhero.getName())
				.description(superhero.getDescription())
				.build());

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(convert(createdHero));
	}

	@PutMapping(value = "/role/{id}")
	public ResponseEntity<RoleDto> updateSuperhero(@PathVariable("id") long id, @RequestBody RoleDto superhero) {

		if (Strings.isBlank(superhero.getName())) {
			throw new IllegalArgumentException("Name was empty/null.");
		}

		if (Strings.isBlank(superhero.getDescription())) {
			throw new IllegalArgumentException("Description was empty/null.");
		}

		Role createdHero = this.roleService.updateRole(Role.aRole()
				.id(id)
				.name(superhero.getName())
				.description(superhero.getDescription())
				.build());

		return ResponseEntity.status(HttpStatus.OK)
				.body(convert(createdHero));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/role/{id}")
	public void deleteHero(@PathVariable("id") long id) {
		this.roleService.delete(id);
	}

	private RoleDto convert(Role role) {
		return RoleDto.aRoleDto()
				.id(role.getId())
				.name(role.getName())
				.description(role.getDescription())
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

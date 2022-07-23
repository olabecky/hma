package com.unisussex.hms;

import com.unisussex.hms.exception.ResourceNotFoundException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SuperheroController {

	private final SuperheroService superheroService;


	public SuperheroController(SuperheroService superheroService) {
		this.superheroService = superheroService;
	}

	@GetMapping(value = "/hero")
	public List<SuperHeroDto> getAllSuperheroes() {

		return this.superheroService.getAllSuperHeroes().stream()
				.map(this::convert)
				.collect(Collectors.toList());
	}

	@GetMapping("/hero/{name}")
	public SuperHeroDto getHeroByName(@PathVariable("name") String name) {
		return this.superheroService.getSuperHeroByName(name)
				.map(this::convert)
				.orElseThrow(() -> new ResourceNotFoundException("Not superhero with name '" + name + "'"));
	}

	@PostMapping(value = "/hero")
	public ResponseEntity<SuperHeroDto> saveSuperhero(@RequestBody SuperHeroDto superhero) {

		if (superhero.getId() != null) {
			throw new IllegalArgumentException("To update an existing hero you should be performing a PUT. To create a hero, remove the ID as an ID will be assigned at creation.");
		}

		if (Strings.isBlank(superhero.getName())) {
			throw new IllegalArgumentException("Name was empty/null.");
		}

		Superhero createdHero = this.superheroService.saveSuperhero(Superhero.aSuperhero()
				.id(superhero.getId())
				.name(superhero.getName())
				.secretIdentity(superhero.getSecretIdentity())
				.build());

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(convert(createdHero));
	}

	@PutMapping(value = "/hero/{id}")
	public ResponseEntity<SuperHeroDto> updateSuperhero(@PathVariable("id") long id, @RequestBody SuperHeroDto superhero) {

		if (Strings.isBlank(superhero.getName())) {
			throw new IllegalArgumentException("Name was empty/null.");
		}

		if (Strings.isBlank(superhero.getSecretIdentity())) {
			throw new IllegalArgumentException("Secret Identity was empty/null.");
		}

		Superhero createdHero = this.superheroService.updateSuperhero(Superhero.aSuperhero()
				.id(id)
				.name(superhero.getName())
				.secretIdentity(superhero.getSecretIdentity())
				.build());

		return ResponseEntity.status(HttpStatus.OK)
				.body(convert(createdHero));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/hero/{id}")
	public void deleteHero(@PathVariable("id") long id) {
		this.superheroService.delete(id);
	}

	private SuperHeroDto convert(Superhero superhero) {
		return SuperHeroDto.aSuperHeroDto()
				.id(superhero.getId())
				.name(superhero.getName())
				.secretIdentity(superhero.getSecretIdentity())
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

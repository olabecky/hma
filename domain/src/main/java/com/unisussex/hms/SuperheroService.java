package com.unisussex.hms;

import java.util.List;
import java.util.Optional;

public interface SuperheroService {

	List<Superhero> getAllSuperHeroes();

	Optional<Superhero> getSuperHeroByName(String name);

	Superhero saveSuperhero(Superhero superhero);

	Superhero updateSuperhero(Superhero superheroDto);

	void saveSuperheros(List<Superhero> superhero);

	void delete(long id);

	void deleteAll();

}

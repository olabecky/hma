package com.unisussex.hms;

import com.unisussex.hms.hibernate.SuperheroEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityHeroConverter {

	public Superhero convert(SuperheroEntity entity) {
		return Superhero.aSuperhero()
				.id(entity.getId())
				.name(entity.getName())
				.secretIdentity(entity.getSecretIdentity())
				.number(entity.getId())
				.build();
	}

	public SuperheroEntity convert(Superhero superhero) {
		SuperheroEntity superheroEntity = new SuperheroEntity();
		superheroEntity.setId(superhero.getId());
		superheroEntity.setName(superhero.getName());
		superheroEntity.setSecretIdentity(superhero.getSecretIdentity());
		superheroEntity.setNumber(superhero.getNumber());

		return superheroEntity;
	}
}

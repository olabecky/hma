package com.unisussex.hms;

import com.unisussex.hms.hibernate.SuperheroDao;
import com.unisussex.hms.hibernate.SuperheroEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HibernateSuperheroService implements SuperheroService {

	private final SuperheroDao superheroDao;
	private final EntityHeroConverter entityHeroConverter;

	public HibernateSuperheroService(SuperheroDao superheroDao,
									 EntityHeroConverter entityHeroConverter) {
		this.superheroDao = superheroDao;
		this.entityHeroConverter = entityHeroConverter;
	}

	@Override
	public List<Superhero> getAllSuperHeroes() {
		return superheroDao.findAll()
				.stream()
				.map(entityHeroConverter::convert)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Superhero> getSuperHeroByName(String name) {
		return superheroDao.findByName(name)
				.map(entityHeroConverter::convert);
	}

	@Override
	public Superhero saveSuperhero(Superhero superhero) {
		if(superheroDao.findByName(superhero.getName()).isPresent()) {
			throw new IllegalArgumentException("Superhero already exists with name: " + superhero.getName());
		} else {
			SuperheroEntity savedHero = superheroDao.save(entityHeroConverter.convert(superhero));

			return entityHeroConverter.convert(savedHero);
		}
	}

	@Override
	public Superhero updateSuperhero(Superhero superhero) {
		Optional<SuperheroEntity> superheroEntity = superheroDao.findById(superhero.getId());

		if(superheroEntity.isEmpty()) {
			throw new IllegalArgumentException("No Superhero exists with id: " + superhero.getId());
		} else {
			SuperheroEntity entityInDB = superheroEntity.get();
			entityInDB.setNumber(superhero.getNumber());
			entityInDB.setName(superhero.getName());
			entityInDB.setSecretIdentity(superhero.getSecretIdentity());

			entityInDB = superheroDao.save(entityInDB);

			return entityHeroConverter.convert(entityInDB);
		}
	}

	@Override
	public void saveSuperheros(List<Superhero> superheros) {
		List<SuperheroEntity> superheroEntities = superheros.stream()
				.map(entityHeroConverter::convert)
				.collect(Collectors.toList());

		superheroDao.saveAll(superheroEntities);
	}

	@Override
	public void delete(long id) {
		superheroDao.deleteById(id);
	}

	@Override
	public void deleteAll() {
		superheroDao.deleteAll();
	}
}

package com.unisussex.hms.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuperheroDao extends JpaRepository<SuperheroEntity, Long> {

	Optional<SuperheroEntity> findByName(String name);
}

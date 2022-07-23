package com.unisussex.hms.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleDao extends JpaRepository<RoleEntity, Long> {

	Optional<RoleEntity> findByName(String name);

	@Query("SELECT e FROM RoleEntity e WHERE lower(e.name) like :param or lower(e.description) like :param")
	List<RoleEntity> findByParam(@Param("param") String param);
}

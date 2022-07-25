package com.unisussex.hms.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity, Long> {

    Optional<RoleEntity> findByUser(String name);

    @Query("SELECT e FROM UserEntity e WHERE lower(e.name) like :param or lower(e.role) like :param")
    List<UserEntity> findByParam(@Param("param") String param);
}

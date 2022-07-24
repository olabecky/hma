package com.unisussex.hms;

import com.unisussex.hms.hibernate.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityRoleConverter {

	public Role convert(RoleEntity entity) {
		return Role.aRole()
				.id(entity.getId())
				.name(entity.getName())
				.description(entity.getDescription())
				.build();
	}

	public RoleEntity convert(Role role) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(role.getId());
		roleEntity.setName(role.getName());
		roleEntity.setDescription(role.getDescription());

		return roleEntity;
	}
}

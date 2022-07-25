package com.unisussex.hms;

import com.unisussex.hms.hibernate.RoleEntity;
import com.unisussex.hms.hibernate.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityUserConverter {
    public User convert(UserEntity entity) {
        return User.aUser()
                .id(entity.getId())
                .username(entity.getUsername())
                .role(Role.aRole().id(entity.getRole().getId()).name(entity.getRole().getName()).description(entity.getRole().getDescription()).build())
                .build();
    }

    public UserEntity convert(User user) {
        UserEntity roleEntity = new UserEntity();
        roleEntity.setId(user.getId());
        roleEntity.setUsername(user.getUsername());
        roleEntity.setRole(new RoleEntity(user.getRole().getId()));

        return roleEntity;
    }
}

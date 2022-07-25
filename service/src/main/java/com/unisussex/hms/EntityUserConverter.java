package com.unisussex.hms;

import com.unisussex.hms.hibernate.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityUserConverter {
    public User convert(UserEntity entity) {
        return User.aUser()
                .id(entity.getId())
                .username(entity.getUsername())
                .role(entity.getRole())
                .build();
    }

    public UserEntity convert(User user) {
        UserEntity roleEntity = new UserEntity();
        roleEntity.setId(user.getId());
        roleEntity.setUsername(user.getUsername());
        roleEntity.setRole(user.getRole());

        return roleEntity;
    }
}

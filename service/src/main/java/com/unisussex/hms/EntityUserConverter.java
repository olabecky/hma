package com.unisussex.hms;

import com.unisussex.hms.hibernate.RoleEntity;
import com.unisussex.hms.hibernate.UserEntity;
import com.unisussex.hms.hibernate.enums.UserStatus;
import org.springframework.stereotype.Component;

@Component
public class EntityUserConverter {
    public User convert(UserEntity entity) {
        return User.aUser()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .mustChangePassword(entity.isMustChangePassword())
                .userStatus(entity.getUserStatus().name())
                .role(Role.aRole().id(entity.getRole().getId()).name(entity.getRole().getName()).description(entity.getRole().getDescription()).build())
                .build();
    }

    public UserEntity convert(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setMustChangePassword(user.isMustChangePassword());
        userEntity.setUserStatus(UserStatus.valueOf(user.getUserStatus()));
        userEntity.setRole(new RoleEntity(user.getRole().getId()));

        return userEntity;
    }
}

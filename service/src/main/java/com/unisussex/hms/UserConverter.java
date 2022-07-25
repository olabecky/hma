package com.unisussex.hms;
import com.unisussex.hms.hibernate.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User convert(UserEntity entity) {
        return User.aUser()
                .id(entity.getId())
                .username(entity.getUsername())
                .role(entity.getRole())
                .build();
    }

    public UserEntity convert(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setRole(user.getRole());

        return userEntity;
    }
}

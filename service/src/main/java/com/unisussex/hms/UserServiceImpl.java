package com.unisussex.hms;

import com.unisussex.hms.hibernate.RoleEntity;
import com.unisussex.hms.hibernate.UserDao;
import com.unisussex.hms.hibernate.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final EntityRoleConverter entityRoleConverter;

    public UserServiceImpl(UserDao userDao, EntityRoleConverter entityRoleConverter) {
        this.userDao = userDao;
        this.entityRoleConverter = entityRoleConverter;
    }

    @Override
    public User saveUser(User user) {
        if(userDao.findByUser(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User already exists with name: " + user.getUsername());
        } else {
            UserEntity savedUser = userDao.save(entityRoleConverter.convert(user));

            return entityRoleConverter.convert(savedUser);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll()
                .stream()
                .map(entityRoleConverter::convert)
                .collect(Collectors.toList());

    }


}

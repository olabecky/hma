package com.unisussex.hms;

import com.unisussex.hms.hibernate.RoleDao;
import com.unisussex.hms.hibernate.RoleEntity;
import com.unisussex.hms.hibernate.UserDao;
import com.unisussex.hms.hibernate.UserEntity;
import com.unisussex.hms.hibernate.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final EntityUserConverter entityUserConverter;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, EntityUserConverter entityUserConverter) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.entityUserConverter = entityUserConverter;
    }

    @Override
    public User saveUser(User user) {
        if(userDao.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User already exists with name: " + user.getUsername());
        } else {
            UserEntity savedUser = userDao.save(entityUserConverter.convert(user));
            return entityUserConverter.convert(savedUser);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll()
                .stream()
                .map(entityUserConverter::convert)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userDao.findByUsername(username)
                .map(entityUserConverter::convert);
    }

    @Override
    public List<User> getUsersByUsernameOrRole(String param) {
        return userDao.findByParam(param)
                .stream()
                .map(entityUserConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public User updateUser(User user) {
        Optional<UserEntity> userEntity = userDao.findById(user.getId());

        if(userEntity.isEmpty()) {
            throw new IllegalArgumentException("No User exists with id: " + user.getId());
        } else {
            UserEntity entityInDB = userEntity.get();
            entityInDB.setUsername(user.getUsername());
            entityInDB.setRole(roleDao.findById(user.getRole().getId()).get());
            entityInDB.setUserStatus(UserStatus.valueOf(user.getUserStatus()));

            entityInDB = userDao.save(entityInDB);

            return entityUserConverter.convert(entityInDB);
        }
    }

    @Override
    public void saveUsers(List<User> users) {
        List<UserEntity> userEntities = users.stream()
                .map(entityUserConverter::convert)
                .collect(Collectors.toList());

        userDao.saveAll(userEntities);
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

}

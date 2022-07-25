package com.unisussex.hms;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserByUsername(String username);

    List<User> getUsersByUsernameOrRole(String param);

    User updateUser(User user);

    void saveUsers(List<User> users);

    void delete(long id);

    void deleteAll();
}

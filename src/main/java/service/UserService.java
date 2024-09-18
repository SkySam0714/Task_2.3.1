package service;

import models.User;

import java.util.List;

public interface UserService {
    void createUser(String name, String surname, byte age);

    void createUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUserById(long id);
}

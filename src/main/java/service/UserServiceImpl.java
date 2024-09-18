package service;

import dao.UserDao;
import dao.UserDaoHibernateImpl;
import models.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    private final UserDao userDao = new UserDaoHibernateImpl();

    @Override
    public void createUser(String name, String surname, byte age) {
        userDao.createUser(name, surname, age);
    }

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }
}

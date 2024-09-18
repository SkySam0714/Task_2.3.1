package dao;


import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger("Dao");


    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUser(String name, String surname, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(new User(name,surname,age));
            session.getTransaction().commit();
            LOGGER.info("User с именем – " + name + " добавлен в базу данных");
        }
    }

    @Override
    public void createUser(User user) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            LOGGER.info("User с именем – " + user.getName() + " добавлен в базу данных");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("SELECT user FROM User user", User.class).getResultList();
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            LOGGER.info("User with id: " + user.getId() + " - was changed");
        }
    }

    @Override
    public void deleteUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE User WHERE id = :id").setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
            LOGGER.info("User with id: " + id + " - was removed from database");
        }
    }

}

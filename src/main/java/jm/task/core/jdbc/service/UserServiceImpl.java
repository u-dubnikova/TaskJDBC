package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        UserDao dao = new UserDaoJDBCImpl();
        dao.createUsersTable();
    }

    public void dropUsersTable() {
        UserDao dao = new UserDaoJDBCImpl();
        dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDao dao = new UserDaoJDBCImpl();
        dao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDao dao = new UserDaoJDBCImpl();
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDao dao = new UserDaoJDBCImpl();
        return dao.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDao dao = new UserDaoJDBCImpl();
        dao.cleanUsersTable();
    }
}

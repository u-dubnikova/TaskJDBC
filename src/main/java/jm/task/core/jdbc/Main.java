package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService us = new UserServiceImpl(new UserDaoJDBCImpl());
        us.createUsersTable();
        us.saveUser("Марина", "Куприянова", (byte) 24);
        System.out.println("User с именем Марина добавлен в базу данных");
        us.saveUser("Даниил", "Алексеев", (byte) 28);
        System.out.println("User с именем Даниил добавлен в базу данных");
        us.saveUser("Алексей", "Жуков", (byte) 22);
        System.out.println("User с именем Алексей добавлен в базу данных");
        us.saveUser("Софья", "Кучкова", (byte) 31);
        System.out.println("User с именем Софья добавлен в базу данных");

        for(User user: us.getAllUsers()){
            System.out.println(user);
        }

        us.cleanUsersTable();
        us.dropUsersTable();
    }
}

package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() throws ClassNotFoundException,SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/java_users?serverTimezone=Europe/Moscow";
        String login = "user";
        String pwd  = "user";
        return DriverManager.getConnection(url,login,pwd);
    }

    public static void closeConnection(Connection con) throws SQLException {
        con.close();
    }
}

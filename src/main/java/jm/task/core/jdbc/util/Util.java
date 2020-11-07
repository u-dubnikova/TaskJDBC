package jm.task.core.jdbc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

    public static Configuration getConfiguration() {
        Properties properties = new Properties();
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.setProperty(Environment.USER, "user");
        properties.setProperty(Environment.PASS, "user");
        properties.setProperty(Environment.URL, "jdbc:mysql://127.0.0.1:3306/java_users?serverTimezone=Europe/Moscow");
        properties.setProperty("hibernate.id.new_generator_mappings", "false");

        Configuration cfg = new Configuration();
        cfg.setProperties(properties);

        return cfg;
    }

    public static Session getSession(Configuration cfg) {
        SessionFactory factory = cfg.buildSessionFactory();
        return factory.openSession();
    }
}

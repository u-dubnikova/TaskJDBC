package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.SQLGrammarException;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSession();

        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("create table User (id INT auto_increment, name VARCHAR(45), lastName VARCHAR(45), age INT, constraint user_pk primary key (id));");
        try{
        query.executeUpdate();
        } catch (Exception e){ }
        tx.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        //Configuration cfg = Util.getConfiguration();
        //cfg.addAnnotatedClass(User.class);
        Session session = Util.getSession();

        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("drop table User ");
        try {
            query.executeUpdate();
        } catch (Exception e){ }

        tx.commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        //Configuration cfg = Util.getConfiguration();
        //cfg.addAnnotatedClass(User.class);
        Session session = Util.getSession();

        Transaction tx = session.beginTransaction();

        User user = new User(name, lastName, age);
        session.save(user);

        /*Query query = session.createSQLQuery("insert into user (name, lastName, age) values (:name, :lastName , :age) ");
        query.setParameter("name", name);
        query.setParameter("lastName", lastName);
        query.setParameter("age", age);
        query.executeUpdate();*/
        tx.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        //Configuration cfg = Util.getConfiguration();
        //cfg.addAnnotatedClass(User.class);
        Session session = Util.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(session.get(User.class,id));
        } catch (Exception e){ }
        tx.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        //Configuration cfg = Util.getConfiguration();
        //cfg.addAnnotatedClass(User.class);
        Session session = Util.getSession();

        Query query  = session.createQuery("from User");
        List list = query.list();

        session.close();

        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("delete from User");
        query.executeUpdate();

        tx.commit();
        session.close();
    }
}

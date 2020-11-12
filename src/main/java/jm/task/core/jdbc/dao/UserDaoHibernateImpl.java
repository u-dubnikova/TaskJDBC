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
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();

        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("drop table User ");
        try{
            query.executeUpdate();
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();

        Transaction tx = session.beginTransaction();

        User user = new User(name, lastName, age);

        try{
            session.save(user);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(session.get(User.class,id));
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
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
        try{
            query.executeUpdate();
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            session.close();
        }
    }
}

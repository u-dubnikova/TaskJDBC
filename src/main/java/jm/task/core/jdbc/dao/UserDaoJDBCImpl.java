package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection con = null;
        Statement st = null;
        try {
            con = Util.getConnection();
            st = con.createStatement();
            try {
                String sql = "create table user (id BIGINT(10), name VARCHAR(45), lastName VARCHAR(45), age TINYINT(1));";
                st.execute(sql);
            } catch (Exception e) {

            }
        } catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Connection con = null;
        Statement st = null;
        try {
            con = Util.getConnection();
            st = con.createStatement();

            try {
                String sql = "drop table user;";
                st.execute(sql);
            } catch (Exception e) {

            }

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection con = null;
        Statement st = null;
        try {
            con = Util.getConnection();
            st = con.createStatement();
            String sql = "insert into user (name, lastName, age) values ('" + name + "', '" + lastName + "', " + age + ");";
            st.execute(sql);
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (st != null) {
                    st.execute("commit;");
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Connection con = null;
        Statement st = null;
        try {
            con = Util.getConnection();
            st = con.createStatement();
            String sql = "delete from user where id=" + id + ";";
            st.execute(sql);
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        Connection con = null;
        Statement st = null;
        List<User> userList = new ArrayList<>();
        try {
            con = Util.getConnection();
            st = con.createStatement();
            String sql = "select * from user";
            ResultSet dblist = st.executeQuery(sql);
            String name;
            String lName;
            byte age;
            while (dblist.next()) {
                name = dblist.getString("name");
                lName = dblist.getString("lastName");
                age = dblist.getByte("age");
                userList.add(new User(name, lName,age));
            }

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        Connection con = null;
        Statement st = null;
        try {
            con = Util.getConnection();
            st = con.createStatement();
            String sql = "delete from user;";
            st.execute(sql);
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

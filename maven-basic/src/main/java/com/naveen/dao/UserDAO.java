package com.naveen.dao;

import com.naveen.beans.LoginBean;
import com.naveen.beans.UserBean;
import com.naveen.connection.GetConnection;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static final Logger LOGGER = GetConnection.getLogger(UserDAO.class);

    private GetConnection connection;
    
    public UserDAO() {
        connection = new GetConnection();
    }

    public boolean insertUser(UserBean userBean) {
        final String sql = "insert into user (uname, pass,utype, display_name, mobile) values(?,?,?,?,?)";
        try {
            connection.preparedStatement1 = GetConnection.getMySQLConnection().prepareStatement(sql);
            connection.preparedStatement1.setString(1, userBean.getUserName().toUpperCase());
            connection.preparedStatement1.setString(2, userBean.getPassWord());
            connection.preparedStatement1.setString(3, userBean.getUserType());
            connection.preparedStatement1.setString(4, userBean.getDisplayName().toUpperCase());
            connection.preparedStatement1.setString(5, userBean.getMobNo());
            return connection.preparedStatement1.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (connection.preparedStatement1 != null) {
                    connection.preparedStatement1.close();   
                }
                GetConnection.getMySQLConnection().close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return false;
    }

    // this method is used in user.jsp to display all registered users.
    public List<UserBean> getAllUsers() {
        final ArrayList<UserBean> myList = new ArrayList<UserBean>();
        final String sql = "select * from user";
        try {
            connection.preparedStatement1 = GetConnection.getMySQLConnection().prepareStatement(sql);
            connection.resultSet1 = connection.preparedStatement1.executeQuery();
            while (connection.resultSet1.next()) {
                UserBean tempUser = new UserBean();
                tempUser.setUserId(connection.resultSet1.getInt(1));
                tempUser.setUserName(connection.resultSet1.getString(2));
                tempUser.setPassWord(connection.resultSet1.getString(3));
                tempUser.setUserType(connection.resultSet1.getString(4));
                tempUser.setDisplayName(connection.resultSet1.getString(5));
                tempUser.setMobNo(connection.resultSet1.getString(6));
                myList.add(tempUser);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                connection.resultSet1.close();
                connection.preparedStatement1.clearBatch();
                connection.preparedStatement1.close();
                GetConnection.getMySQLConnection().close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return myList;
    }

    // method is used to check valid user
    public boolean validateUser(LoginBean login) {
        final String sql = "select * from user where uname =? and pass =? and utype=?";
        try {
            connection.preparedStatement1 = GetConnection.getMySQLConnection().prepareStatement(sql);
            connection.preparedStatement1.setString(1, login.getuName());
            connection.preparedStatement1.setString(2, login.getPassWord());
            connection.preparedStatement1.setString(3, login.getUserType());
            connection.resultSet1 = connection.preparedStatement1.executeQuery();
            return connection.resultSet1.next();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (connection.resultSet1 != null) {
                    connection.resultSet1.close();   
                }
                if (connection.preparedStatement1 != null) {
                    connection.preparedStatement1.close();   
                }
                GetConnection.getMySQLConnection().close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return false;
    }

    public int getUserId(String uName) {
        final String sql = "select uid from user where uname = ?";
        try {
            connection.preparedStatement1 = GetConnection.getMySQLConnection().prepareStatement(sql);
            connection.preparedStatement1.setString(1, uName);
            connection.resultSet1 = connection.preparedStatement1.executeQuery();
            if (connection.resultSet1.next()) {
                return connection.resultSet1.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (connection.resultSet1 != null) {
                    connection.resultSet1.close();   
                }
                if (connection.preparedStatement1 != null) {
                    connection.preparedStatement1.close();   
                }
                GetConnection.getMySQLConnection().close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return 0;
    }

    public String getUserType(String uName) {
        final String sql = "select utype from user where uname = ?";
        try {
            connection.preparedStatement1 = GetConnection.getMySQLConnection().prepareStatement(sql);
            connection.preparedStatement1.setString(1, uName);
            connection.resultSet1 = connection.preparedStatement1.executeQuery();
            if (connection.resultSet1.next()) {
                return connection.resultSet1.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (connection.resultSet1 != null)
                    connection.resultSet1.close();
                if (connection.preparedStatement1 != null)
                    connection.preparedStatement1.close();
                GetConnection.getMySQLConnection().close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return null;
    }

    public boolean updateUser(UserBean ub) {
        final String sql = "update user set pass=? where uname=?";
        try {
            connection.preparedStatement1 = GetConnection.getMySQLConnection().prepareStatement(sql);
            connection.preparedStatement1.setString(2, ub.getUserName());
            connection.preparedStatement1.setString(1, ub.getPassWord());
            return connection.preparedStatement1.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (connection.preparedStatement1 != null)
                    connection.preparedStatement1.close();
                GetConnection.getMySQLConnection().close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return false;
    }

}

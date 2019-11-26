package com.naveen.dao;

import com.naveen.beans.LoginBean;
import com.naveen.beans.UserBean;
import com.naveen.connection.GetConnection;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static final Logger LOGGER = GetConnection.getLogger(UserDAO.class);

    public boolean insertUser(UserBean userBean) {
        final String sql = "insert into user (uname, pass,utype, display_name, mobile) values(?,?,?,?,?)";
        try(PreparedStatement preparedStatement = GetConnection.getMySQLConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, userBean.getUserName().toUpperCase());
            preparedStatement.setString(2, userBean.getPassWord());
            preparedStatement.setString(3, userBean.getUserType());
            preparedStatement.setString(4, userBean.getDisplayName().toUpperCase());
            preparedStatement.setString(5, userBean.getMobNo());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return false;
    }
    
    // this method is used in user.jsp to display all registered users.
    public List<UserBean> getAllUsers() {
        final ArrayList<UserBean> myList = new ArrayList<>();
        final String sql = "select * from user";
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = GetConnection.getMySQLConnection().prepareStatement(sql)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserBean tempUser = new UserBean();
                tempUser.setUserId(resultSet.getInt(1));
                tempUser.setUserName(resultSet.getString(2));
                tempUser.setPassWord(resultSet.getString(3));
                tempUser.setUserType(resultSet.getString(4));
                tempUser.setDisplayName(resultSet.getString(5));
                tempUser.setMobNo(resultSet.getString(6));
                myList.add(tempUser);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            closeResultSet(resultSet);
            closeConnection();
        }
        return myList;
    }

    // method is used to check vali     d user
    public boolean validateUser(LoginBean login) {
        final String sql = "select * from user where uname = ? and pass = ? and utype = ?";
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = GetConnection.getMySQLConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, login.getuName());
            preparedStatement.setString(2, login.getPassWord());
            preparedStatement.setString(3, login.getUserType());
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            closeResultSet(resultSet);
            closeConnection();
        }
        return false;
    }

    public int getUserId(String uName) {
        final String sql = "select uid from user where uname = ?";
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = GetConnection.getMySQLConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, uName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            closeResultSet(resultSet);
            closeConnection();
        }
        return 0;
    }

    public String getUserType(String uName) {
        final String sql = "select utype from user where uname = ?";
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = GetConnection.getMySQLConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, uName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            closeResultSet(resultSet);
            closeConnection();
        }
        return null;
    }

    public boolean updateUser(UserBean ub) {
        final String sql = "update user set pass=? where uname=?";
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = GetConnection.getMySQLConnection().prepareStatement(sql)) {
            preparedStatement.setString(2, ub.getUserName());
            preparedStatement.setString(1, ub.getPassWord());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
           closeResultSet(resultSet);
           closeConnection();
        }
        return false;
    }
    
    private void closeConnection() {
        try {
            GetConnection.getMySQLConnection().close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
    
    private void closeResultSet(ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();   
            }
        } catch(SQLException e) {
             LOGGER.error(e);  
        }
    }

}

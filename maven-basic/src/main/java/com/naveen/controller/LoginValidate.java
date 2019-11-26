package com.naveen.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.beans.LoginBean;
import com.naveen.connection.GetConnection;
import com.naveen.dao.UserDAO;

public class LoginValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public void init(){
		GetConnection gc = new GetConnection();
		
		try {
			gc.ps2 = GetConnection.getMySQLConnection().prepareStatement("set global max_connections = 700");
			gc.ps2.executeUpdate();
			//System.out.println("connections set to 700>>>>>>>>>>>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginBean login = new  
				LoginBean(request.getParameter("uname"), 
						request.getParameter("password"),
						request.getParameter("utype"));

		
		if(new UserDAO().validateUser(login)){
			login.setSessionId(request.getSession(true).getId());
			
			login.setUserId(new UserDAO().getUserId(request.getParameter("uname")));
			login.setUserType(new UserDAO().getUserType(request.getParameter("uname")));
			
			request.getSession().setAttribute("LOGIN", login);
			
			request.getRequestDispatcher("index.jsp?msg=Login Successfull").forward(request, response);
			
		}else{
			request.getRequestDispatcher("login.jsp?msg=User Name or Password or User Type Not Valid").include(request, response);
			}
		
	}

}

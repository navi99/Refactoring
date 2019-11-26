package com.naveen.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.beans.UserBean;
import com.naveen.dao.UserDAO;


public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean ub=new UserBean();
		ub.setUserName(request.getParameter("uname"));
		ub.setPassWord(request.getParameter("pass"));
		
		if(new UserDAO().updateUser(ub)){
			getServletContext().getRequestDispatcher("/index.jsp?msg=Password Changed Successfully").forward(request, response);
		}else{
			getServletContext().getRequestDispatcher("/ChangePassword.jsp?msg=Sorry Password Not Changed.. Try Again/Contact Our Team").include(request, response);

		}
	}

}

package com.naveen.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.beans.UserBean;
import com.naveen.dao.UserDAO;


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean ub=new UserBean();
		ub.setUserName(request.getParameter("uname"));
		ub.setPassWord(request.getParameter("pass"));
		ub.setDisplayName(request.getParameter("dname"));
		ub.setMobNo(request.getParameter("mob"));
		ub.setUserType(request.getParameter("utype"));
		if(new UserDAO().insertUser(ub)){
			getServletContext().getRequestDispatcher("/index.jsp?msg=User Added Successfully").forward(request, response);
		}else{
			getServletContext().getRequestDispatcher("/user.jsp?msg=Sorry User Not Added.. Try Again/Contact Our Team").include(request, response);

		}
	}

}

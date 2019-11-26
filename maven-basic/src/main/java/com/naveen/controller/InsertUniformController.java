package com.naveen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.beans.LoginBean;
import com.naveen.beans.SchoolBean;
import com.naveen.dao.SchoolDAO;
import com.naveen.dao.UniformDAO;

/**
 * Servlet implementation class InsertUniformController
 */
public class InsertUniformController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.setAttribute("school", null);
		//request.setAttribute("class", null);
		//request.setAttribute("sex", null);
 		
		String [] uniformIds = request.getParameterValues("uniformid");
 		String sex = request.getParameter("sex");
 		String cls = request.getParameter("class");
 		int schId = Integer.parseInt(request.getParameter("schoolid"));
 		
 		SchoolBean school = new SchoolDAO().getSchool(schId);
 		
 		request.setAttribute("school", school);
		request.setAttribute("class", cls);
		request.setAttribute("sex", sex);
 		
 		int uid=((LoginBean)request.getSession().getAttribute("LOGIN")).getUserId();
 		
 		
 		new UniformDAO().insertUniform(schId, cls, sex, uniformIds,uid);
 		request.getRequestDispatcher("AssignUniform1.jsp?").forward(request, response);
		
	}

}

package com.naveen.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.beans.LoginBean;
import com.naveen.beans.StudentBean;
import com.naveen.dao.StudentDAO;

public class EditStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	//varish written to edit student details
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentBean studb=new StudentBean();
		//System.out.println(request.getParameter("sname"));
		studb.setStudName(request.getParameter("sname"));
		studb.setStudSex(request.getParameter("gen"));
		studb.setStudUSN(request.getParameter("usn"));
		studb.setStudClass(request.getParameter("class"));
		studb.setStudSection(request.getParameter("sec"));
		studb.setStudSchoolId(Integer.parseInt(request.getParameter("schoolid")));
		studb.setStudParent(request.getParameter("pname"));
		studb.setStudParentMob(request.getParameter("mob"));
		studb.setStudParentEmail(request.getParameter("email"));
		studb.setStudId(Integer.parseInt(request.getParameter("stid")));
		if(new StudentDAO().updateStudent(studb,((LoginBean)request.getSession().getAttribute("LOGIN")).getUserId())){
			getServletContext().getRequestDispatcher("/index.jsp?msg=Student Details Updated Successfully").forward(request, response);
		}else{
			getServletContext().getRequestDispatcher("/student.jsp?msg=Sorry Student Details Not Updated.. Try Again").include(request, response);

		}
	
	
	
	}

}

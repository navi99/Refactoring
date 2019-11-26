package com.naveen.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.dao.StudentDAO;

@WebServlet("/DeleteStudent.do")
public class DeleteStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String studentId = request.getParameter("studentId");

		if (new StudentDAO().deleteStudent(studentId)) {
			request.getRequestDispatcher("index.jsp?msg=Student Deleted Successfully").forward(request, response);
		} else {
			request.getRequestDispatcher("index.jsp?msg=Sorry Student Not Deleted...").forward(request, response);
		}

	}

}

package com.naveen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.beans.StudentBean;
import com.naveen.dao.OrderDAO;
import com.naveen.dao.StudentDAO;

/**
 * Servlet implementation class GetStudentFromSchoolForDeleteController
 */
@WebServlet("/GetStudentFromSchoolForDelete.do")
public class GetStudentFromSchoolForDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println("<table border='2'>");

		List<StudentBean> list = new StudentDAO().getStudents(Integer.parseInt(request.getParameter("schid")),
				request.getParameter("name"));

		out.println(
				"<tr style='background-color:yellow'><td>Name</td><td>USN</td><td>Gender</td><td>Class</td><td>Delete ?</td></tr>");

		for (StudentBean temp : list) {
			out.print("<tr>");
			out.print("<td>" + temp.getStudName() + "</td>");
			out.print("<td>" + temp.getStudUSN() + "</td>");
			out.print("<td>" + temp.getStudSex() + "</td>");
			out.print("<td>" + temp.getStudClass() + "</td>");
			out.print("<td><a href='DeleteStudent.do?studentId=" + temp.getStudId() + "'>Delete</a></td> </tr>");
		}

		out.println("</table>");
	}

}

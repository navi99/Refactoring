package com.naveen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.dao.OrderDAO;

public class GetOrderByStudentController extends HttpServlet {

	private static final long serialVersionUID = -3535214704951675756L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<table border='3', bordercolor='orange'>");
		out.println("<tr style='background-color:yellow'><td>Name</td><td>USN</td><td>Gender</td><td>Class</td><td>Order Number</td><td>Cancelled</td></tr>");
		for(List<String> temp : new OrderDAO().getOrderDetailsWithStudentName(request.getParameter("name"),
				Integer.parseInt(request.getParameter("schid")))){
			out.println("<tr>");
			out.println("<td>"+temp.get(0)+"</td>");
			out.println("<td>"+temp.get(1)+"</td>");
			out.println("<td>"+temp.get(2)+"</td>");
			out.println("<td>"+temp.get(3)+"</td>");
			out.println("<td><span style='color:red; font-family:arial;'><a href='PrintOrder.jsp?frmname=sname&oid="+temp.get(4)+"'>"+temp.get(4)+"</span></a></td>");
			out.println("<td>"+temp.get(5)+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	}

}

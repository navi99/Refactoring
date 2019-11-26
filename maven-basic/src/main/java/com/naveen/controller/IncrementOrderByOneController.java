package com.naveen.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.dao.OrderDAO;

/**
 * Servlet implementation class IncrementOrderByOneController
 */
@WebServlet("/IncrementOrderByOne.do")
public class IncrementOrderByOneController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = new OrderDAO().incrementOrderNumber
				(Integer.parseInt(request.getParameter("schid")));
		String msg = (flag == true) ? 
				"Orders Reset Properly, Note One Order Number will be skipped"
				: "Sorry Order Id Reset did not happen try again";
		
		request.getRequestDispatcher("index.jsp?msg=" + msg).forward(request, response);
	}
}

package com.naveen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.dao.OrderDAO;

/**
 * Servlet implementation class ApproveOrderController
 */
public class DuplicateDateController extends HttpServlet {
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
		int sno = Integer.parseInt(request.getParameter("sno"));
		int eno = Integer.parseInt(request.getParameter("eno"));
		String date1=request.getParameter("invdate");
		if(new OrderDAO().updateInvDate1(sno,eno,date1)){
			request.getRequestDispatcher("DuplicateInvoice.jsp?msg=Thank you... date saved successfully.. ").forward(request, response);
		}else{
			request.getRequestDispatcher("DuplicateInvoice.jsp?msg=Sorry date not saved, pls try again.. ").forward(request, response);
		}
	}

}

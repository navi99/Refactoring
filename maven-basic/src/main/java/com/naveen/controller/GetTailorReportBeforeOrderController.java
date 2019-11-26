package com.naveen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.dao.TailorDAO;


// New Requirement 
// By Naveen 27-Dec-2015
@SuppressWarnings("serial")
public class GetTailorReportBeforeOrderController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setAttribute("SHOWTAILORREPORTBEFOREORDER", new TailorDAO().getTailorReportBeforeOrder
				(Integer.parseInt(request.getParameter("schoolid")), 
						request.getParameter("cls"), 
				Integer.parseInt(request.getParameter("uniformid")), 
				request.getParameter("sex")));

		request.getRequestDispatcher("ShowTailorReportBeforeOrder.jsp").forward(request, response);
		
	}

}

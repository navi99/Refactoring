package com.naveen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.dao.OrderDAO;


public class SetInvoiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("invno")) ;
		
		try{
			
			if(new OrderDAO().updateInvoice(no))
			{
				request.getRequestDispatcher("SetInvoice.jsp?msg=Thank You.. Invoice Saved Successfully").forward(request, response);
			}
			else{
				request.getRequestDispatcher("SetInvoice.jsp?msg=Sorry Invoice No Not Saved").forward(request, response);
			}
	}catch(Exception e){
		e.printStackTrace();
		
	}
	}

}

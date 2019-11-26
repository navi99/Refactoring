package com.naveen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.dao.UniformDAO;

/**
 * Servlet implementation class InsertUpdatePriceMasterController
 */
public class InsertUpdatePriceMasterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int count = Integer.parseInt(request.getParameter("count"));
//		int schId = Integer.parseInt(request.getParameter("schoolid"));
//		String cls = request.getParameter("class");
//		String sex = request.getParameter("sex");
		
		UniformDAO ud = new UniformDAO();
		
		
		for(int i=1; i<=count; i++){
			System.out.println(
					ud.insertPrice(
					Double.parseDouble(request.getParameter("price"+i)), 
					Integer.parseInt(request.getParameter("auid"+i)), 
//					Double.parseDouble(request.getParameter("tax"+i)),
					Double.parseDouble(request.getParameter("cgst"+i)),
					Double.parseDouble(request.getParameter("sgst"+i)),
					Double.parseDouble(request.getParameter("igst"+i)),

					Integer.parseInt(request.getParameter("ordersorder"+i)), 
					/// added newly for courier on 22 FEb 2018 
					Double.parseDouble(request.getParameter("couriercost"+i)))
					);
		}
		
		
		request.getRequestDispatcher("index.jsp?msg=Prices Updated Successfully... ").forward(request, response);
		
		
	}
}

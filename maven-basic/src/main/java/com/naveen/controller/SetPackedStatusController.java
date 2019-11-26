package com.naveen.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.dao.OrderDAO;

@WebServlet("/SetPackedStatus.do")
public class SetPackedStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int odid = Integer.parseInt(request.getParameter("odid"));
		String checkStatus = request.getParameter("checkstatus");


		if(checkStatus.equals("true")){
			checkStatus="Y";
		}else {
			checkStatus="N";
		}
		
		
		PrintWriter out = response.getWriter(); 
		if(new OrderDAO().setPackStatus(odid, checkStatus)){
		out.println("Saved");
		}else{
			out.println("Error Try Again");
		}
		
	}

}

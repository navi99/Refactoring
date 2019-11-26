package com.naveen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.beans.LoginBean;
import com.naveen.dao.ColorDAO;

/**
 * Servlet implementation class InsertHouseColor
 */
public class InsertHouseColor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(new ColorDAO().insertColor(request.getParameter("color1").toUpperCase(), request.getParameter("color2").toUpperCase(), request.getParameter("color3").toUpperCase(), request.getParameter("color4").toUpperCase(), ((LoginBean)request.getSession().getAttribute("LOGIN")).getUserId())){
			request.getRequestDispatcher("index.jsp?msg=Colors Added Successfully").forward(request, response);
		}else{
			request.getRequestDispatcher("housecolors.jsp?msg=Sorry Colors Not Added").include(request, response);
		}
		
		
	}

}

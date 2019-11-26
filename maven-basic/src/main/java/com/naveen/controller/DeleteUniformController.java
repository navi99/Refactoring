package com.naveen.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author naveenkumar
 * @see this is part of new requirement sep 2016 26th, tell to be cautious when using  
 */
@WebServlet("/DeleteUniform.do")
public class DeleteUniformController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// When deleting the uniform 
		// first identify all the students who are having that uniform 
		// delete from studentmesurementdetails 
		//  then delete from studentmesurement
		// delete from orderdetail since auid is referred here 
		// delete from studentorder  and 
		
	}

}

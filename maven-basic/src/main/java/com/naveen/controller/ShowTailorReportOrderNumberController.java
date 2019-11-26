package com.naveen.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.dao.TailorDAO;

/**
 * Servlet implementation class ShowTailorReportController
 */
public class ShowTailorReportOrderNumberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
		ArrayList<String> classesList = new ArrayList<String>(Arrays.asList(request.getParameterValues("classes"))) ;
		//System.out.println("Classes got is " + classesList);

		ArrayList<String> sexList = new ArrayList<String>();
		
		String sex = request.getParameter("sex");
		
		if(sex.equals("BOTH")){
			sexList.add("B");
			sexList.add("G");
		}else{
			sexList.add(sex);
		}
		//System.out.println("Sex list is " + sexList);
		ArrayList<String> schoolList = new ArrayList<String>(Arrays.asList(request.getParameterValues("schoolids")));

		//System.out.println("School list is " + schoolList);
		int uniformId = Integer.parseInt(request.getParameter("uniformid"));
		// by naveen 6th Feb
		String startDate = request.getParameter("startdate");
		String endDate = request.getParameter("enddate");
		
		
		
		int startOrderNo = Integer.parseInt(request.getParameter("startordernumber"));
		int endOrderNo = Integer.parseInt(request.getParameter("endordernumber"));
		
		
		
		if(classesList.size()==0 || sexList.size() ==0 || schoolList.size()==0){
			request.getRequestDispatcher("index.jsp?msg=Please select all values for Tailor Report").forward(request, response);
		}else{
			
			ArrayList<ArrayList<String>> tailorReport= new TailorDAO().generateTailorReportOrderNumber(uniformId, classesList, sexList, schoolList, startDate, endDate, startOrderNo, endOrderNo);
				if(tailorReport == null){
				request.getRequestDispatcher("index.jsp?msg=Sorry No Data Found For Entered Values").forward(request, response);

			}else{
			request.setAttribute("TAILORRPT", tailorReport);
			request.setAttribute("HASCOLOR", new TailorDAO().checkColorForUniform(uniformId, classesList, sexList, schoolList));
			//request.setAttribute("NOOFUNIFORM", new TailorDAO().getNumberOfUniforms(uniformId, classesList, sexList, schoolList));
			request.setAttribute("NOOFUNIFORM", new TailorDAO().getNumberOfUniforms(uniformId));
			
					request.getRequestDispatcher("ShowTailorReport.jsp?startorderno=" + startOrderNo +"&endorderno=" + endOrderNo).forward(request, response);
			}
		}
		}catch(Exception e){
			request.getRequestDispatcher("index.jsp?msg=Please select all values for Tailor Report").forward(request, response);
		}
	}

}

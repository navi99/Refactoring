package com.naveen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.dao.OrderDAO;

/**
 * implementing singlethreadmodel to ensure that all operations should happen in one go and no other request is 
 * being processed
 */
@SuppressWarnings("deprecation")
public class TaxInvoiceController extends HttpServlet implements SingleThreadModel{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		int studentId = Integer.parseInt(request.getParameter("stid"));
		String oId=request.getParameter("oid");
		String invDate=request.getParameter("invdate");
		//int schoolId=Integer.parseInt(request.getParameter("schid"));	
		
		int invNo=new OrderDAO().insertInvoiceDetail(studentId,invDate, oId);
		if(invNo!=-1){
			request.getRequestDispatcher("PrintInvoiceBulk.jsp?oid="+oId+"&invDate="+invDate+"&invno="+invNo).forward(request, response);	
			}
		else{
			request.getRequestDispatcher("index.jsp?msg=Sorry Invoice not generated.. pls try again.. .").forward(request, response);
		}
	}

}

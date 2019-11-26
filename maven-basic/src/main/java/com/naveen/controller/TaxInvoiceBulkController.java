package com.naveen.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.naveen.beans.OrderBean;
import com.naveen.beans.OrderInvoiceBean;
import com.naveen.connection.GetConnection;
import com.naveen.dao.OrderDAO;

/**
 * implementing singlethreadmodel to ensure that all operations should happen in one go and no other request is 
 * being processed
 */
@SuppressWarnings("deprecation")
public class TaxInvoiceBulkController extends HttpServlet implements SingleThreadModel{
	private static final long serialVersionUID = 1L;
	static Logger logger = GetConnection.getLogger(TaxInvoiceBulkController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int schoolId = Integer.parseInt(request.getParameter("schid"));
		String studentClass = request.getParameter("class");
		String invDate=request.getParameter("invdate");
		
		// this will hold oid and studentid 
		List<OrderInvoiceBean> orderInvoiceList = new OrderDAO().insertBulkInvoiceDetail(schoolId, studentClass, invDate);

		logger.info("orderInvoiceList Size ->  " + orderInvoiceList.size());
		if(orderInvoiceList.size()>0){
			request.setAttribute("ORDERINVOICE", orderInvoiceList);
			request.getRequestDispatcher("PrintInvoiceBulk.jsp?invDate="+invDate).forward(request, response);	
			}
		else{
			request.getRequestDispatcher("index.jsp?msg=Sorry Invoice not generated.. pls try again... or generate individually for this Class").forward(request, response);
		}
	}
}

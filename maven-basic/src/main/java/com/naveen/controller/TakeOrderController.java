package com.naveen.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.lang.reflect.Executable;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.beans.LoginBean;
import com.naveen.dao.OrderDAO;

/**
 * implementing singlethreadmodel to ensure that all operations should happen in one go and no other request is 
 * being processed
 */
@SuppressWarnings("deprecation")
public class TakeOrderController extends HttpServlet implements SingleThreadModel{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
//		unid1=1&auid1=3&qty31=1&amount1=111&
//		unid2=2&auid2=5&qty32=2&amount2=600&
//		total=711&grandtotal=750.11&modeofpayment=cheque&ccvalue=112211&count=3
		String usn = request.getParameter("usn");
		
		String class1 = request.getParameter("class2");
		//System.out.println("class1="+class1);
		String schoolid=request.getParameter("schId");
		
		int count = Integer.parseInt(request.getParameter("count"));
		String modeOfPay = request.getParameter("modeofpayment");
		String ccValue = request.getParameter("ccvalue");
		double grandTotal = Double.parseDouble(request.getParameter("grandtotal"));
		double total = Double.parseDouble(request.getParameter("total"));
		int studentId = Integer.parseInt(request.getParameter("stid"));
		
		// new requirement 23 Feb 2018 
		double totalCourierCost = Double.parseDouble(request.getParameter("totalcouriercost"));

		// Added by Naveen, 06-Feb-2016
		// As New Requirement
		double bankHandlingCharges = Double.parseDouble(request.getParameter("bankhandlingcharges"));
		
		boolean flag=true;
		// not in use now because of format 2015/16/STI/01
	// 	int oId = Integer.parseInt(request.getParameter("oid"));



		// new OrderDAO().updateStudentOrder(stId, grandTotal, total,   modeOfPay,ccValue)
		// oId="no";
		String oId = new OrderDAO().insertStudentOrder( studentId,  grandTotal, total,  modeOfPay,  ccValue, 
				((LoginBean)request.getSession().getAttribute("LOGIN")).getUserId(), bankHandlingCharges, totalCourierCost);
		


		//if(oId.equalsIgnoreCase("no"))
		if(oId==null)
		{
			request.getRequestDispatcher("MakeOrder3.jsp?msg=Sorry Order Already Taken For This Student...&schid="+schoolid+"&class2="+class1+"&usn="+usn).forward(request, response);
		}
		else{
		// insert into orderdetail with values unid, qty, auid, oid, amount
		for(int i=1; i<count; i++){
			int unId = Integer.parseInt(request.getParameter("unid"+i));
			int auId = Integer.parseInt(request.getParameter("auid"+i));
			int qty =Integer.parseInt(request.getParameter("qty"+i));
			if(qty>0){
			double amount = Double.parseDouble(request.getParameter("amount"+i));
			// added by naveen 
			double tax = Double.parseDouble(request.getParameter("tax"+i));
			
			// tax1 is commented earlier this was vat now changed to GST with cgst, scst, igst 
			// double tax1 = Double.parseDouble(request.getParameter("tax1"+i));
			
			double cgstTax  = Double.parseDouble(request.getParameter("cgstpercentage"+i));
			double sgstTax = Double.parseDouble(request.getParameter("sgstpercentage"+i));
			double igstTax = Double.parseDouble(request.getParameter("igstpercentage"+i));

			double cgstTaxAmount = Double.parseDouble(request.getParameter("cgstamount"+i));
			double sgstTaxAmount = Double.parseDouble(request.getParameter("sgstamount"+i));
			double igstTaxAmount = Double.parseDouble(request.getParameter("igstamount"+i));
			
			//double taxamount= Double.parseDouble(request.getParameter("tax"+i));
			int smId = Integer.parseInt(request.getParameter("smid"+i));
			
			// courier cost added newly 23 Feb 2018 
			
			double courierCost = Double.parseDouble(request.getParameter("couriercost" + i));
			
			if(!new OrderDAO().insertOrderDetail(unId, auId, qty, amount, oId, smId,cgstTax,
					cgstTaxAmount, sgstTax, sgstTaxAmount,igstTax , igstTaxAmount, courierCost)){
						flag=false; 
						break;
				}
			}
		} 
		
		//request.getRequestDispatcher("MakeOrder3.jsp?msg=Sorry Order Already Taken For This Student...&schid="+schoolid+"&class2="+class1+"&usn="+usn).forward(request, response);
		if(flag){
			request.getRequestDispatcher("PrintOrder.jsp?frmname=takeorder&oid="+oId+"&schoolid="+schoolid+"&class2="+ class1).forward(request, response);
		}
		else{
			request.getRequestDispatcher("MakeOrder3.jsp?msg=Sorry Order Already Taken For This Student...&schid="+schoolid+"&class2="+class1+"&usn="+usn).forward(request, response);
		}
	  }	
	}
}

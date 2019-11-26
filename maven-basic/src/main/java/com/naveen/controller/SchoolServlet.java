package com.naveen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.beans.LoginBean;
import com.naveen.beans.SchoolBean;
import com.naveen.dao.SchoolDAO;


public class SchoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SchoolBean sb=new SchoolBean();
		sb.setSchName(request.getParameter("sname"));
		sb.setSchPhone(request.getParameter("phno"));
		sb.setStreet(request.getParameter("street"));
		sb.setCity(request.getParameter("city"));
		sb.setState(request.getParameter("state"));
		sb.setPin(request.getParameter("pin"));
		sb.setInPerson(request.getParameter("person"));
		sb.setInPersonEmail(request.getParameter("email"));
		sb.setInPersonMobile(request.getParameter("mob"));
		sb.setVat(request.getParameter("vat"));
		sb.setVatPercent(request.getParameter("vatpercent"));
		sb.setComments(request.getParameter("comments"));
		sb.setShortCode(request.getParameter("shortcode").toUpperCase());
		//System.out.println(((LoginBean)request.getSession().getAttribute("LOGIN")).getUserId());
		if(new SchoolDAO().insertSchool(sb,((LoginBean)request.getSession().getAttribute("LOGIN")).getUserId())){
			if(new SchoolDAO().insertStudentOrderNumber(sb)&& new SchoolDAO().insertStudentInvNumber(sb)){
				getServletContext().getRequestDispatcher("/index.jsp?msg=School Added Successfully").forward(request, response);
			}
			else{
				getServletContext().getRequestDispatcher("/index.jsp?msg=School Added But Short Code Not Added").forward(request, response);
			}
			
		}else{
			getServletContext().getRequestDispatcher("/school.jsp?msg=Sorry School Not Added.. Try Again/Contact Our Team").include(request, response);

		}
	}
}



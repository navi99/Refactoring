package com.naveen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.beans.LoginBean;
import com.naveen.dao.StudentDAO;


public class UpdateStudentParentController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usn = request.getParameter("usn");
		String un[]=usn.split("~~~");
		String usn1=un[1];
		String class1 = request.getParameter("class2");
		//System.out.println("class1="+class1);
		String schoolid=request.getParameter("schId");
		String parent=request.getParameter("parent").toUpperCase();
		String mob=request.getParameter("mob");
		String email=request.getParameter("email");
		int stid = new StudentDAO().getStudentIdFromUsn(usn1,class1,Integer.parseInt(schoolid)) ;
		int uid =((LoginBean)request.getSession().getAttribute("LOGIN")).getUserId();
		try{
			if(new StudentDAO().updateStudentParent(parent, mob, email, uid, stid))
			{
				request.getRequestDispatcher("MakeOrder3.jsp?msg=Parent Details Saved Successfully...&schid="+schoolid+"&class2="+class1+"&usn="+usn).forward(request, response);
			}
			else{
				request.getRequestDispatcher("MakeOrder3.jsp?msg=Sorry Parent Details Not Saved...&schid="+schoolid+"&class2="+class1+"&usn="+usn).forward(request, response);
			}
	}catch(Exception e){
		e.printStackTrace();
		
	}
	}

}

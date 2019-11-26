package com.naveen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naveen.beans.LoginBean;
import com.naveen.beans.MesurementOrderBean;
import com.naveen.dao.StudentDAO;
import com.naveen.dao.UniformDAO;


public class TakeMesurementController1 extends HttpServlet {
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
		String schoolid=request.getParameter("schid");
		int stid = new StudentDAO().getStudentIdFromUsn(usn1,class1,Integer.parseInt(schoolid)) ;
		//System.out.println("usn got " + usn  +", stid id is " + stid );
		
		// need to take it from session 
		int uid =((LoginBean)request.getSession().getAttribute("LOGIN")).getUserId();
		
		
		String unids[] = request.getParameterValues("unid");
		String auids[] = request.getParameterValues("auid");
		
		//System.out.println("no of auids is " + unids.length);
		try{
		for(int i=0; i<unids.length; i++){
			//System.out.println("auid - unid " +auids[i]  +"-" + unids[i]);
			
			// insert into studentmesurement with auid, stid, color 
			
				String color = request.getParameter(auids[i] +"-color");
				//System.out.println("Color is " + color);
			
				// if you get null meaning the student does not has house 
				// store null only in studentmesurement 
				
				int smid=0;
				try {
					smid = new UniformDAO().insertStudentMesurement(Integer.parseInt(auids[i]), stid, color, uid  );
				} catch (Exception e) {

					e.printStackTrace();
				}
				//System.out.println("smid is " + smid);
//				System.out.println(new UniformDAO().getMesurementOrderunId(Integer.parseInt(temp)));
			
			for(MesurementOrderBean mob : new UniformDAO().getMesurementOrderunId(Integer.parseInt(unids[i]))){
				//System.out.println(request.getParameter(auids[i]  +"-" + mob.getmId() +"-"+ mob.getName()));
				
				
				
				String req = request.getParameter(auids[i]  +"-" + mob.getmId() +"-"+ mob.getName());
				
				
				//System.out.println("req is " + req);
				// insert into studentmesurementdetails int(req) ->size, mob.getMid, smid 
				
				if(new UniformDAO().insertStudentMesurementDetails(Double.parseDouble(req), mob.getmId(), smid )){
				
					//System.out.println("updated/inserted student size... for smid " + smid );
				// System.out.println("mes name " + auids[i]  +"-" + mob.getmId() +"-"+ mob.getName());
				
				//request.getRequestDispatcher("index.jsp?msg = Mesurement already saved... ").forward(request, response);
				}
				
				// not required... 
//				else{
//					request.getRequestDispatcher("index.jsp?msg=Sorry Measurment Not Saved...  ").forward(request, response);
//
//				}
				
				
				
			}
			
			

		}
		
		

		// By Naveen - 19JAN2016
		// update MEASUREMENTDATE with sysdate, this date is used to know whether the user was absent or present 
		// while taking the measurement, next year when the student is promoted the measurementdate will be again set 
		// to null, such that the measuremnet is not taken
		
		new StudentDAO().updateMeasurementDate(stid);
		request.getRequestDispatcher("MakeOrder3.jsp?msg=Mesurements Saved Successfully...&schid="+schoolid+"&class2="+class1+"&usn="+usn).forward(request, response);

		//System.out.println("++++++++++++++++++++++++++");
	}catch(Exception e){
		e.printStackTrace();
		request.getRequestDispatcher("index.jsp?msg=Sorry Measurment Not Saved...  ").forward(request, response);
	}
	}

}

package com.naveen.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.naveen.beans.SchoolBean;
import com.naveen.connection.GetConnection;


/**
 * 
 * 
 * @author naveenkumar
 *
 */
public class SchoolDAO {
	static Logger logger = GetConnection.getLogger(SchoolDAO.class);

	public List<SchoolBean> getAllSchool(){
		String sql="select schid,sname,shortcode,street,city,state,pin,inperson,phno,mobile,email,vat,vatpercent,comments,uid from school where schid > 1";
		
		List<SchoolBean> schoolList = new ArrayList<SchoolBean>();
		
		GetConnection gc = new GetConnection();
		try {
			gc.preparedStatement1 = GetConnection.getMySQLConnection().prepareStatement(sql);
			gc.resultSet1 = gc.preparedStatement1.executeQuery();
			
			while(gc.resultSet1.next()){
				SchoolBean temp = new SchoolBean();
				temp.setSchId(gc.resultSet1.getInt(1));
				temp.setSchName(gc.resultSet1.getString(2));
				temp.setShortCode(gc.resultSet1.getString(3));
				temp.setStreet(gc.resultSet1.getString(4));
				temp.setCity(gc.resultSet1.getString(5));
				temp.setState(gc.resultSet1.getString(6));
				temp.setPin(gc.resultSet1.getString(7));
				temp.setInPerson(gc.resultSet1.getString(8));
				temp.setSchPhone(gc.resultSet1.getString(9));
				temp.setInPersonMobile(gc.resultSet1.getString(10));
				temp.setInPersonEmail(gc.resultSet1.getString(11));
				temp.setVat(gc.resultSet1.getString(12));
				temp.setVatPercent(gc.resultSet1.getString(13));
				temp.setComments(gc.resultSet1.getString(14));
				temp.setUid(gc.resultSet1.getInt(15));
				
				schoolList.add(temp);
			}
			
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		finally{
			try {
				if(gc.resultSet1!=null)gc.resultSet1.close();
				if(gc.preparedStatement1!=null) gc.preparedStatement1.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			GetConnection.closeConnection();
			
		}
		
		return schoolList;
		
		
	}

	
	/**
	 * 
	 * @param schId
	 * @return
	 * @see added lastorderconfirmdate, and also altered SchoolBean, the same is used in ShowMeasurement.jsp, as 
	 * the message to be shown to the parent to say them when is the last date for the change in measurement 
	 */
	public SchoolBean getSchool(int schId){
		String sql="select schid, sname, shortcode, lastorderconfirmdate, DATE_FORMAT(lastorderconfirmdate, '%d %M %Y') from school where schid =?";
		
		
		GetConnection gc = new GetConnection();
		SchoolBean sb = null;

		try {
			gc.preparedStatement1 = GetConnection.getMySQLConnection().prepareStatement(sql);
			gc.preparedStatement1.setInt(1, schId);
			gc.resultSet1 = gc.preparedStatement1.executeQuery();
			
			if(gc.resultSet1.next()){
				
				sb = new SchoolBean();
				sb.setSchId(gc.resultSet1.getInt(1));
				sb.setSchName(gc.resultSet1.getString(2));
				sb.setStreet(gc.resultSet1.getString(3));
				sb.setLastorderconfirmdate(gc.resultSet1.getString(4));
				sb.setLastOrderConfirmDateInDDMMYYYY(gc.resultSet1.getString(5));
				// put other
				return sb;
			}
			
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		finally{
			try {
				if(gc.resultSet1!=null)gc.resultSet1.close();
				if(gc.preparedStatement1!=null) gc.preparedStatement1.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			GetConnection.closeConnection();
			
		}
		
		return sb;
	}
	
	public static void main(String[] args) {
		System.out.println(new SchoolDAO().getSchool(2).getLastOrderConfirmDateInDDMMYYYY());
	}
	public boolean insertSchool(SchoolBean sb, int uid) {
		String sql="insert into school (sname, street,city, state, pin,inperson,phno,mobile,email,vat,vatpercent,comments,uid,shortcode) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		GetConnection gc=new GetConnection();
		try {
			gc.preparedStatement1 = GetConnection.getMySQLConnection().prepareStatement(sql);
			gc.preparedStatement1.setString(1, sb.getSchName().toUpperCase());
			gc.preparedStatement1.setString(2,sb.getStreet().toUpperCase());
			gc.preparedStatement1.setString(3, sb.getCity().toUpperCase());
			gc.preparedStatement1.setString(4, sb.getState().toUpperCase());
			gc.preparedStatement1.setString(5, sb.getPin());
			gc.preparedStatement1.setString(6, sb.getInPerson().toUpperCase());
			gc.preparedStatement1.setString(7, sb.getSchPhone());
			gc.preparedStatement1.setString(8, sb.getInPersonMobile());
			gc.preparedStatement1.setString(9, sb.getInPersonEmail());
			gc.preparedStatement1.setString(10, sb.getVat().toUpperCase());
			gc.preparedStatement1.setString(11, sb.getVatPercent());
			gc.preparedStatement1.setString(12, sb.getComments());
			gc.preparedStatement1.setInt(13, uid);
			gc.preparedStatement1.setString(14, sb.getShortCode().toUpperCase());
			
			return gc.preparedStatement1.executeUpdate()>0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		finally{
			try {
				//if(gc.rs1!=null)gc.rs1.close();
				if(gc.preparedStatement1!=null) gc.preparedStatement1.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			GetConnection.closeConnection();
			
		}
		return false;
		}

	
	// this method is not used after GST - commented on 29 DEC 2017 
	// naveen
	// used in MakeOrder2.jsp
	public double getSchoolVat(int schId){
		String sql ="select vatpercent from school where schid=?";
		
		GetConnection gc = new GetConnection();
		
		try {
			gc.preparedStatement1 = GetConnection.getMySQLConnection().prepareStatement(sql);
			gc.preparedStatement1.setInt(1, schId);
			
			gc.resultSet1 = gc.preparedStatement1.executeQuery();
			
			if(gc.resultSet1.next())
				return gc.resultSet1.getDouble(1);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(gc.resultSet1!=null)gc.resultSet1.close();
				if(gc.preparedStatement1!=null) gc.preparedStatement1.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			GetConnection.closeConnection();
			
		}
		
		return 0;
	}
	
	/* varish
	 * This method is used to get school id using shortcoder
	 */
	public int getSchoolId(String scode)
	{
		String sql="select schid from school where shortcode=?";
		GetConnection gc=new GetConnection();
		
		try {
			gc.preparedStatement1=GetConnection.getMySQLConnection().prepareStatement(sql);
			gc.preparedStatement1.setString(1, scode);
			gc.resultSet1=gc.preparedStatement1.executeQuery();
			if(gc.resultSet1.next())
			{
				return gc.resultSet1.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			try {
				if(gc.resultSet1!=null)gc.resultSet1.close();
				if(gc.preparedStatement1!=null) gc.preparedStatement1.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			GetConnection.closeConnection();
			
		}
		return 0;
	}
	
	/* varish
	 * This method is used to insert values to studentordernumber table used to generate order number
	 */
	
	
	public boolean insertStudentOrderNumber(SchoolBean sb)
	{
		int schid=getSchoolId(sb.getShortCode());
		String sql="insert into studentordernumber (schsname,yr,seqno,schid) values (?,?,?,?)";
		GetConnection gc=new GetConnection();
		
		try {
			gc.preparedStatement1=GetConnection.getMySQLConnection().prepareStatement(sql);
			gc.preparedStatement1.setString(1, sb.getShortCode());
			gc.preparedStatement1.setInt(2,Calendar.getInstance().get(Calendar.YEAR));
			gc.preparedStatement1.setInt(3, 1);
			gc.preparedStatement1.setInt(4, schid);
			return gc.preparedStatement1.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			try {
				//if(gc.rs1!=null)gc.rs1.close();
				if(gc.preparedStatement1!=null) gc.preparedStatement1.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			GetConnection.closeConnection();
			
		}
		
		return false;
	}
	
	/* varish
	 * This method is used to insert values to studentinvnumber table used to generate invoice number
	 */
	
	public boolean insertStudentInvNumber(SchoolBean sb)
	{
		int schid=getSchoolId(sb.getShortCode());
		String sql="insert into studentinvnumber (schsname,yr,seqno,schid) values (?,?,?,?)";
		GetConnection gc=new GetConnection();
		
		try {
			gc.preparedStatement1=GetConnection.getMySQLConnection().prepareStatement(sql);
			gc.preparedStatement1.setString(1, sb.getShortCode());
			gc.preparedStatement1.setInt(2,Calendar.getInstance().get(Calendar.YEAR));
			gc.preparedStatement1.setInt(3, 1);
			gc.preparedStatement1.setInt(4, schid);
			return gc.preparedStatement1.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			try {
				//if(gc.rs1!=null)gc.rs1.close();
				if(gc.preparedStatement1!=null) gc.preparedStatement1.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			GetConnection.closeConnection();
			
		}
		return false;
	}
	
	
	
	
}

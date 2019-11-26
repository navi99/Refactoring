package com.naveen.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.naveen.beans.HouseBean;
import com.naveen.connection.GetConnection;

public class HouseDAO {
	
	static Logger logger = GetConnection.getLogger(HouseDAO.class);

	public HouseBean getHouse(int houseId) {

		String sql = "select hid, color1, color2, color3, color4 from house where hid =?";
		HouseBean hb = null;
		GetConnection gc = new GetConnection();
		try {
			gc.preparedStatement1 = GetConnection.getMySQLConnection().prepareStatement(sql);
			gc.preparedStatement1.setInt(1, houseId);

			gc.resultSet1 = gc.preparedStatement1.executeQuery();

			if (gc.resultSet1.next()) {
				hb = new HouseBean();
				hb.sethId(houseId);

				hb.setColor1(gc.resultSet1.getString(2));
				hb.setColor2(gc.resultSet1.getString(3));
				hb.setColor3(gc.resultSet1.getString(4));
				hb.setColor4(gc.resultSet1.getString(5));
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
		return hb;

	}
	
	
	
	
	
	
	public HouseBean getHouseFromAssignedUniformId(int auId) {

		String sql = "SELECT hid, color1, color2, color3, color4 FROM HOUSE WHERE"
				+ " HID = (SELECT HID FROM ASSIGNUNIFORM WHERE AUID  =?)";
		HouseBean hb = null;
		GetConnection gc = new GetConnection();
		try {
			gc.preparedStatement1 = GetConnection.getMySQLConnection().prepareStatement(sql);
			gc.preparedStatement1.setInt(1, auId);

			gc.resultSet1 = gc.preparedStatement1.executeQuery();

			if (gc.resultSet1.next()) {
				hb = new HouseBean();
				hb.sethId(gc.resultSet1.getInt(1));

				hb.setColor1(gc.resultSet1.getString(2));
				hb.setColor2(gc.resultSet1.getString(3));
				hb.setColor3(gc.resultSet1.getString(4));
				hb.setColor4(gc.resultSet1.getString(5));
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
		return hb;

	}
	
	
}

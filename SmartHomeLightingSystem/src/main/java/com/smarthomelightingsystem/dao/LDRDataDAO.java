package com.smarthomelightingsystem.dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


import com.smarthomelightingsystem.data.Database;
import com.smarthomelightingsystem.model.LDRData;

public class LDRDataDAO {
	private static final String TABLENAME = "ldr_data";
	
	public void fill(ResultSet r, LDRData out) throws SQLException {
		
		String str = r.getString("timestamp");
		String label = str.substring(13, 15);
		
		out.getData().add(r.getInt("data"));
		out.getLabels().add(label);

    }

    public LDRData getDefault() { 
        LDRData out = new LDRData();
    	Database DB = new Database();
    	try {
    		String q = "SELECT * FROM ldr_data_day "
    				+ "LIMIT 8;";
    		
        	PreparedStatement prepStatement = DB.connection.prepareStatement(q);
        	ResultSet r = DB.executePreparedStatement(prepStatement);
        	
        	while (r.next()) { fill(r, out); }
        	
    	} catch (SQLException e) { e.printStackTrace(); } 
    	finally { DB.close(); }
    	
    	return out;
			
    }

    public LDRData getDay() {
    	LDRData out = new LDRData();
    	Database DB = new Database();
    	try {
    		String q = "SELECT * FROM ldr_data_day";
    		
    		PreparedStatement prepStatement = DB.connection.prepareStatement(q);
        	ResultSet r = DB.executePreparedStatement(prepStatement);
        	
        	while(r.next()) {fill(r, out);}
        	
    	} catch (SQLException e) {e.printStackTrace(); } 
    	finally{ DB.close(); }
    	
    	return out;
    }

    public LDRData getWeek() {
    	LDRData out = new LDRData();
    	Database DB = new Database();
    	try {
    		String q = "SELECT * FROM ldr_data_week";
    		
    		PreparedStatement prepStatement = DB.connection.prepareStatement(q);
    		ResultSet r = DB.executePreparedStatement(prepStatement);
        	
        	while(r.next()) {fill(r, out);}
        	
    	} catch (SQLException e) {e.printStackTrace(); } 
    	finally{ DB.close(); }
    	
    	return out;
    }

    public LDRData getMonth() { 
    	LDRData out = new LDRData();
    	Database DB = new Database();
    	try {
    		String q = "SELECT * FROM ldr_data_month";
    		
    		PreparedStatement prepStatement = DB.connection.prepareStatement(q);
    		ResultSet r = DB.executePreparedStatement(prepStatement);
    		
    		while(r.next()) {fill(r, out);}
        	
    	} catch (SQLException e) {e.printStackTrace(); } 
    	finally{ DB.close(); }
    	
    	return out;
    }

    public void setValue(float value) {
    	Database DB = new Database();
    	try {
    		String q = "INSERT INTO " + TABLENAME + "(data, timestamp) VALUES(?, ?);" ;
    		PreparedStatement prepStatement = DB.connection.prepareStatement(q);
			prepStatement.setFloat(1, value);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			prepStatement.setTimestamp(2, timestamp);
			
			DB.executePreparedStatement(prepStatement);
    	}catch (Exception e) {
    		e.printStackTrace();
    	}finally{
			DB.close();
		}

    }

}
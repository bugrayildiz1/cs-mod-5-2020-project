package com.smarthomelightingsystem.dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


import com.smarthomelightingsystem.data.Database;
import com.smarthomelightingsystem.model.LDRData;
/**
 * The class works with LDR data retrieved from LDR sensor
 * 
 * @author Jasper van Amerongen
 * @author Albina Shynkar
 * @author Lola Solovyeva
 * @author Ilya Averchenko
 * @author Bugra Yildiz
 * @author Alexandru Matcov

 * 
 * @version 1
 *
 */

public class LDRDataDAO {

	private static final String TABLENAME = "ldr_data";

	/**
	 * Fills LDRData with information from ResultSet.
	 * 
	 * @param r ResultSet object
	 * 
	 * @return LDRData object with information from ResultSet
	 * 
	 * @throws SQLException upon SQL query failure
	 */
	public void fillHours(ResultSet r, LDRData out) throws SQLException {
		
		out.getData().add(r.getFloat("data"));
		out.getLabels().add(r.getString("timestamp").substring(11, 13));

    }

	public void fill(ResultSet r, LDRData out) throws SQLException {

		out.getData().add(r.getFloat("avg"));
		out.getLabels().add(r.getString("timestamp").substring(8, 10));

	}
	/**
	 * Retrieves the information from database regarding LDRData
	 * for the last 8 hours.
	 * 
	 * @throws SQLException upon SQL query failure
	 */
    public LDRData getDefault() {

        LDRData out = new LDRData();
    	Database DB = new Database();

    	try {

    		String q = "SELECT * FROM ldr_data_day_8";
    		
        	PreparedStatement ps = DB.connection.prepareStatement(q);
        	ResultSet r = DB.executePreparedStatement(ps);
        	
        	while (r.next()) { fillHours(r, out); }
        	
    	} catch (SQLException e) { e.printStackTrace(); } 
    	finally { DB.close(); }
    	
    	return out;
			
    }
    /**
	 * Retrieves the information from database regarding LDRData
	 * for the last day.
	 * 
	 * @throws SQLException upon SQL query failure
	 */
    public LDRData getDay() {

    	LDRData out = new LDRData();
    	Database DB = new Database();

    	try {

    		String q = "SELECT * FROM ldr_data_day";
    		
    		PreparedStatement ps = DB.connection.prepareStatement(q);
        	ResultSet r = DB.executePreparedStatement(ps);
        	
        	while(r.next()) {fillHours(r, out);}
        	
    	} catch (SQLException e) {e.printStackTrace(); } 
    	finally{ DB.close(); }
    	
    	return out;

    }

    /**
	 * Retrieves the information from database regarding LDRData
	 * for the week with average value for each day.
	 * 
	 * @throws SQLException upon SQL query failure
	 */
    public LDRData getWeek() {

    	LDRData out = new LDRData();
    	Database DB = new Database();

    	try {

    		String q = "SELECT * FROM ldr_data_week";
    		
    		PreparedStatement ps = DB.connection.prepareStatement(q);
    		ResultSet r = DB.executePreparedStatement(ps);
        	
        	while(r.next()) { fill(r, out); }
        	
    	} catch (SQLException e) {e.printStackTrace(); } 
    	finally{ DB.close(); }
    	
    	return out;
    }

    /**
   	 * Retrieves the information from database regarding LDRData
   	 * for the month with average value for each day.
   	 * 
   	 * @throws SQLException upon SQL query failure
   	 */
    public LDRData getMonth() {

    	LDRData out = new LDRData();
    	Database DB = new Database();

    	try {

    		String q = "SELECT * FROM ldr_data_month";
    		
    		PreparedStatement ps = DB.connection.prepareStatement(q);
    		ResultSet r = DB.executePreparedStatement(ps);
    		
    		while(r.next()) { fill(r, out); }
        	
    	} catch (SQLException e) {e.printStackTrace(); } 
    	finally{ DB.close(); }
    	
    	return out;

    }

    /**
     * Inserts exact value to the database with current time
     * 
     * @param value value of LDR data to put in database
     * 
     * @throws SQLException upon SQL query failure
     */
    public void setValue(float value) {

    	Database DB = new Database();

    	try {

    		String q = "INSERT INTO " + TABLENAME + "(data, timestamp) VALUES (?, ?);";

    		PreparedStatement ps = DB.connection.prepareStatement(q);
			ps.setFloat(1, value);
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			
			DB.executePreparedStatement(ps);

    	} catch (SQLException e) { e.printStackTrace(); }
    	finally { DB.close(); }

    }

}
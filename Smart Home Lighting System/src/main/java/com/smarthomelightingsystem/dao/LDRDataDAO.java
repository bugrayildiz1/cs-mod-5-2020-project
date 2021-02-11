package com.smarthomelightingsystem.dao;



import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


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
		String timeStamp = new SimpleDateFormat("H:mm").format(r.getTimestamp("timestamp"));
		out.getLabels().add(timeStamp);

    }

	public void fill(ResultSet r, LDRData out) throws SQLException {

		out.getData().add(round(r.getFloat("avg"),2));
		String timeStamp = new SimpleDateFormat("dd/MM").format(r.getTimestamp("timestamp"));
		out.getLabels().add(timeStamp);

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

    		String q = "SELECT * FROM ldr_data_day_8 ORDER BY timestamp ASC";
    		
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

    		String q = "SELECT * FROM ldr_data_day ORDER BY timestamp ASC";
    		
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

    		String q = "SELECT * FROM ldr_data_week ORDER BY timestamp ASC";
    		
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

    		String q = "SELECT * FROM ldr_data_month ORDER BY timestamp ASC";
    		
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

	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
}
package com.smarthomelightingsystem.dao;



import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.smarthomelightingsystem.data.Database;
import com.smarthomelightingsystem.model.LDRData;

public class LDRDataDAO {
	private static final String TABLENAME = "ldr_data";

    public LDRData getDefault(boolean mobile) { // last 8h | mobile false: every h; mobile true: every 2h
    	Database DB = new Database();
    	try {
    		if (mobile == true) {
    			String q = "SELECT * "
    			+ "FROM ldr_data_day "
    			+ "WHERE ";
    		}else if(mobile!= true) {
    			String q= "SELECT * FROM ldr_data_day;";
    		}
    	}catch (Exception e) {
			e.printStackTrace();
		} finally{
			DB.close();
		}
        return null;
    }

    public LDRData getDay(boolean mobile) { // mobile false: every 2h; mobile true: every 4h
        return null;
    }

    public LDRData getWeek() { // every day regardless of mobile
        return null;
    }

    public LDRData getMonth(boolean mobile) { // mobile false: every 2d; mobile true: every 4d

        return null;

    }

    public void setValue(float value) {
    	Database DB = new Database();
    	try {
    		String q = "INSERT INTO " + TABLENAME + "(data, timestamp) VALUES(?, ?);" ;
    		PreparedStatement prepStatement = DB.connection.prepareStatement(q);
			prepStatement.setFloat(1, value);
			//LocalDateTime localDateTime = m.getTime().toInstant().atZone(ZoneId.of("GMT+0")).toLocalDateTime();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now(); 
			prepStatement.setObject(2, dtf.format(now));
			
			DB.executePreparedStatement(prepStatement);
    	}catch (Exception e) {
    		e.printStackTrace();
    	}finally{
			DB.close();
		}

    }

}
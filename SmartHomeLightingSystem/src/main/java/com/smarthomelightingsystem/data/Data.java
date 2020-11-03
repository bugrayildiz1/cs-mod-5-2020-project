package com.smarthomelightingsystem.data;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class Data {
    private static final String TABLENAME = "ldr_data";

    static void Generate() {
        ArrayList<Timestamp> timestamps = new ArrayList<>();
        Database DB = new Database();

        try {

            String q = "DELETE FROM " + TABLENAME + ";"
                    + " INSERT INTO " + TABLENAME + " (data, timestamp)  VALUES ";

            for (int d = 1; d < 31 * 24; d++) {
                if (d == 31 * 24 - 1) {
                    q += "(?,?);";
                } else {
                    q += "(?,?),";
                }
            }

            PreparedStatement ps = DB.connection.prepareStatement(q);

            Date date = new Date();
            for (int d = 31; d >= 1; d--) {
                for (int h = 24; h >= 0; h--) {
                    Calendar cal = GregorianCalendar.getInstance();
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.HOUR_OF_DAY, date.getHours() + h - 24);
                    cal.set(Calendar.DAY_OF_MONTH, date.getDay() + d - 30);
                    cal.set(Calendar.MONTH, date.getMonth());
                    cal.set(Calendar.YEAR, 2020);
                    timestamps.add(new Timestamp(cal.getTimeInMillis()));
                }
            }

            for (int c = 1; c < 1486; c++) {
                if (c % 2 != 0) {
                    float random = new Random().nextFloat();
                    ps.setFloat(c, round(random, 2));
                }
            }
            int count = 0;
            for (int c = 1; c < 1487; c++) {
                if (c % 2 == 0) {
                    ps.setTimestamp(c, timestamps.get(count));
                    count++;
                }
            }

            DB.executePreparedStatement(ps);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close();
        }

    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static void main(String[] args) {
        Generate();
    }
}

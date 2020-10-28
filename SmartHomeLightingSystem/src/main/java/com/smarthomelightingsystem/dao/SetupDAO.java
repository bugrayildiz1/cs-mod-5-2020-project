package com.smarthomelightingsystem.dao;

import com.smarthomelightingsystem.data.Database;
import com.smarthomelightingsystem.model.Configuration;
import com.smarthomelightingsystem.model.SetUp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The class works with animations of LED strip
 *
 * @author Jasper van Amerongen
 * @author Albina Shynkar
 * @author Lola Solovyeva
 * @author Ilya Averchenko
 * @author Bugra Yildiz
 * @author Alexandru Matcov
 * @version 1
 */
public class SetupDAO {

    private static final String TABLENAME = "setup";

    private SetUp fill(ResultSet r) throws SQLException {

        SetUp setUp = new SetUp();
        setUp.setConfiguration(r.getInt("p"), r.getInt("q"));
        setUp.setRGB(r.getInt("r"), r.getInt("g"), r.getInt("b"));
        setUp.setBrightness(r.getFloat("a"));
        setUp.setAnimation(r.getInt("a_id"));
        setUp.setPreset(r.getInt("r_id"));
        setUp.setMode(r.getBoolean("power"));
        return setUp;
    }

    public SetUp getSetUp() {
        Database DB = new Database();
        SetUp setUp = null;
        try {
            String q = "SELECT * FROM" + TABLENAME;
            PreparedStatement prepStatement = DB.connection.prepareStatement(q);
            ResultSet r = DB.executePreparedStatement(prepStatement);
            fill(r);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DB.close();
        return setUp;
    }

    public void setConfiguration(SetUp setUp) {
        Database DB = new Database();
        try {
            String q1 = "UPDATE " + TABLENAME + " "
                    + "SET p = ? "
                    + "AND SET q = ?;";
            PreparedStatement prepStatement = DB.connection.prepareStatement(q1);
            prepStatement.setInt(1, setUp.getP());
            prepStatement.setInt(2, setUp.getQ());
            DB.executePreparedStatement(prepStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DB.close();
        }
    }

    public void setColor(SetUp setUp) {
        Database DB = new Database();
        try {
            String q1 = "UPDATE " + TABLENAME + " "
                    + "SET r = ? "
                    + "AND SET g = ?"
                    + "AND SET b = ?"
                    + "AND SET a = ?;";
            PreparedStatement prepStatement = DB.connection.prepareStatement(q1);
            prepStatement.setInt(1, setUp.getR());
            prepStatement.setInt(2, setUp.getG());
            prepStatement.setInt(3, setUp.getB());
            prepStatement.setFloat(4, setUp.getA());
            DB.executePreparedStatement(prepStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DB.close();
        }
    }
	public void setAnimation(SetUp setUp) {
		Database DB = new Database();
		try {
			String q1 = "UPDATE " + TABLENAME + " "
					+ "SET a_id = ?; ";
			PreparedStatement prepStatement = DB.connection.prepareStatement(q1);
			prepStatement.setInt(1, setUp.getAnimation());
			DB.executePreparedStatement(prepStatement);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			DB.close();
		}
	}
    public void setPreset(SetUp setUp) {
        Database DB = new Database();
        try {
            String q1 = "UPDATE " + TABLENAME + " "
                    + "SET p_id = ?; ";
            PreparedStatement prepStatement = DB.connection.prepareStatement(q1);
            prepStatement.setInt(1, setUp.getPreset());
            DB.executePreparedStatement(prepStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DB.close();
        }
    }
	public void setMode(SetUp setUp) {
		Database DB = new Database();
		try {
			String q1 = "UPDATE " + TABLENAME + " "
					+ "SET power = ? ";
			PreparedStatement prepStatement = DB.connection.prepareStatement(q1);
			prepStatement.setBoolean(1,setUp.isPower());
			DB.executePreparedStatement(prepStatement);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			DB.close();
		}
	}

    public Configuration getUserNumOfLed() {
        //TO-DO
        return null;
    }

    public Configuration setUserNomOfLeds() {
        //TO-DO
        return null;
    }

}

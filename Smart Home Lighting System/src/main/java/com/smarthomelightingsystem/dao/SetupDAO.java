package com.smarthomelightingsystem.dao;

import com.smarthomelightingsystem.data.Database;
import com.smarthomelightingsystem.exceptions.IllegalSetupException;
import com.smarthomelightingsystem.model.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The class works with setup of LED strip
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
    /**
	 * Fills SetUp with information from ResultSet.
	 * 
	 * @param r ResultSet object
	 * 
	 * @return SetUp object with information from ResultSet
	 * 
	 * @throws SQLException upon SQL query failure
	 */
    private Setup fill(ResultSet r) throws SQLException {

        Setup setUp = new Setup();
        setUp.setPQ(r.getInt("p"),
                    r.getInt("q"));
        setUp.setRGBA(r.getInt("r"),
                      r.getInt("g"),
                      r.getInt("b"),
                      r.getFloat("a"));
        setUp.setAnimId(r.getInt("anim_id"));
        setUp.setPresetId(r.getInt("preset_id"));
        setUp.setDoLDR(r.getBoolean("do_ldr"));
        return setUp;

    }

	/**
	 * Retrieves the information from database regarding SetUp.
	 * 
	 * @throws SQLException upon SQL query failure
	 */
    public Setup getSetUp() {

        Database DB = new Database();
        Setup s = null;

        try {

            String q = "SELECT * FROM " + TABLENAME;
            PreparedStatement prepStatement = DB.connection.prepareStatement(q);
            ResultSet r = DB.executePreparedStatement(prepStatement);

            while (r.next()) { s = fill(r); }

        } catch (SQLException e) { e.printStackTrace(); }
        finally { DB.close(); }

        return s;

    }

	/**
	 * Inserts SetUp information in the database
	 * 
	 * @param s setup object
	 */
    public void setSetup(Setup s) throws IllegalSetupException {

        if (s.getP() != 0 && s.getQ() != 0) setPQ(s.getP(), s.getQ());
        else throw new IllegalSetupException();

        if (

            // A color (with or without animation) is selected
            (s.getR() != 0 &&
             s.getG() != 0 &&
             s.getB() != 0 &&
             s.getA() != 0 &&
             s.getAnimId() != 0 &&
             s.getPresetId() == 0 &&
             s.getDoLDR() == false) ||

            // A preset is selected
            (s.getR() == 0 &&
             s.getG() == 0 &&
             s.getB() == 0 &&
             s.getA() == 0 &&
             s.getAnimId() == 0 &&
             s.getPresetId() != 0 &&
             s.getDoLDR() == false) ||

            // The system is running of thr LDR
            (s.getR() == 0 &&
             s.getG() == 0 &&
             s.getB() == 0 &&
             s.getA() == 0 &&
             s.getAnimId() == 0 &&
             s.getPresetId() == 0 &&
             s.getDoLDR() == true) ||

            // The system is off
            (s.getR() == 0 &&
             s.getG() == 0 &&
             s.getB() == 0 &&
             s.getA() == 0 &&
             s.getAnimId() == 0 &&
             s.getPresetId() == 0 &&
             s.getDoLDR() == false)

        ) {

            setRGBA(s.getR(), s.getG(), s.getB(), s.getA());
            setAnimation(s.getAnimId());
            setPreset(s.getPresetId());
            setDoLDR(s.getDoLDR());

        } else throw new IllegalSetupException(); // In any other case, throw exception

    }

	/**
	 * Inserts parameters p and q in the database
	 * 
	 * @param p length of strip
	 * @param q width of strip
	 */
    public void setPQ(int p, int q) throws IllegalSetupException {

        if (p < 0 || q < 0) throw new IllegalSetupException();

        Database DB = new Database();

        try {

            String q1 = "UPDATE " + TABLENAME + " SET p = ?, q = ?;";
            PreparedStatement ps = DB.connection.prepareStatement(q1);
            ps.setInt(1, p);
            ps.setInt(2, q);
            DB.executePreparedStatement(ps);

        } catch (SQLException e) { e.printStackTrace(); }
        finally { DB.close(); }

    }

    /**
     * Inserts r,g,b,a to database
     * 
     * @param r red value
     * @param g green value
     * @param b blue value
     * @param a brightness value
     */
    public void setRGBA(int r, int g, int b, float a) throws IllegalSetupException {

        if (r < 0 || r > 255 ||
            g < 0 || g > 255 ||
            b < 0 || b > 255 ||
            a < 0 || a > 1) throw new IllegalSetupException();

        Database DB = new Database();

        try {

            String q = "UPDATE " + TABLENAME + " SET r = ?, g = ?, b = ?, a = ?;";
            PreparedStatement ps = DB.connection.prepareStatement(q);
            ps.setInt(1, r);
            ps.setInt(2, g);
            ps.setInt(3, b);
            ps.setFloat(4, a);
            DB.executePreparedStatement(ps);

        } catch (SQLException e) { e.printStackTrace(); }
        finally { DB.close(); }

    }

    public void setAnimation(int id) throws IllegalSetupException {

        if (id < 0) throw new IllegalSetupException();

		Database DB = new Database();

		try {

			String q = "UPDATE " + TABLENAME + " SET anim_id = ?; ";
			PreparedStatement ps = DB.connection.prepareStatement(q);
			ps.setInt(1, id);
			DB.executePreparedStatement(ps);

		} catch (SQLException e) { e.printStackTrace(); }
		finally { DB.close(); }

	}

    /**
     * Inserts preset in the database
     *
     * @param id id of preset
     */
    public void setPreset(int id) throws IllegalSetupException {

        if (id < 0) throw new IllegalSetupException();

		Database DB = new Database();

		try {

			String q = "UPDATE " + TABLENAME + " SET preset_id = ?; ";
			PreparedStatement ps = DB.connection.prepareStatement(q);
			ps.setInt(1, id);
			DB.executePreparedStatement(ps);

		} catch (SQLException e) { e.printStackTrace(); }
		finally { DB.close(); }

	}

    /**
     * Inserts information about whether to use the LDR in the database
     * 
     * @param b whether the LDR is used.
     */
    public void setDoLDR(boolean b) {

		Database DB = new Database();

		try {

			String q = "UPDATE " + TABLENAME + " SET do_ldr = ?; ";
			PreparedStatement ps = DB.connection.prepareStatement(q);
			ps.setBoolean(1, b);
			DB.executePreparedStatement(ps);

		} catch (SQLException e) { e.printStackTrace(); }
		finally { DB.close(); }

	}

}

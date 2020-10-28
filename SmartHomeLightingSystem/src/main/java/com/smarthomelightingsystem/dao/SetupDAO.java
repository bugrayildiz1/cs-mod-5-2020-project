package com.smarthomelightingsystem.dao;

import com.smarthomelightingsystem.data.Database;
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
        setUp.setPQ(r.getInt("p"),
                    r.getInt("q"));
        setUp.setRGBA(r.getInt("r"),
                      r.getInt("g"),
                      r.getInt("b"),
                      r.getFloat("a"));
        setUp.setAnimId(r.getInt("anim_id"));
        setUp.setPresetId(r.getInt("preset_id"));
        setUp.setPower(r.getBoolean("power"));
        return setUp;

    }

    public SetUp getSetUp() {

        Database DB = new Database();
        SetUp s = null;

        try {

            String q = "SELECT * FROM " + TABLENAME;
            PreparedStatement prepStatement = DB.connection.prepareStatement(q);
            ResultSet r = DB.executePreparedStatement(prepStatement);

            while (r.next()) { s = fill(r); }

        } catch (SQLException e) { e.printStackTrace(); }
        finally {

            DB.close();
            return s;

        }

    }

    public void setSetUp(SetUp s) {

        setPQ(s.getP(), s.getQ());
        setRGBA(s.getR(), s.getG(), s.getB(), s.getA());
        if (s.getAnimId() != 0 && s.getPresetId() == 0) setAnimation(s.getAnimId());
        else if (s.getPresetId() != 0 && s.getAnimId() == 0) setPreset(s.getPresetId());
        setPower(s.getPower());

    }

    public void setPQ(int p, int q) {

        System.out.println("p = " + p + " and q = " + q);

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

    public void setRGBA(int r, int g, int b, float a) {

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

    public void setAnimation(int id) {

		Database DB = new Database();

		try {
		    
			String q = "UPDATE " + TABLENAME + " SET anim_id = ?; ";
			PreparedStatement ps = DB.connection.prepareStatement(q);
			ps.setInt(1, id);
			DB.executePreparedStatement(ps);

		} catch (SQLException e) { e.printStackTrace(); }
		finally { DB.close(); }

	}

    public void setPreset(int id) {

		Database DB = new Database();

		try {

			String q = "UPDATE " + TABLENAME + " SET preset_id = ?; ";
			PreparedStatement ps = DB.connection.prepareStatement(q);
			ps.setInt(1, id);
			DB.executePreparedStatement(ps);

		} catch (SQLException e) { e.printStackTrace(); }
		finally { DB.close(); }

	}

    public void setPower(boolean p) {

		Database DB = new Database();

		try {

			String q = "UPDATE " + TABLENAME + " SET power = ?; ";
			PreparedStatement ps = DB.connection.prepareStatement(q);
			ps.setBoolean(1, p);
			DB.executePreparedStatement(ps);

		} catch (SQLException e) { e.printStackTrace(); }
		finally { DB.close(); }

	}

}

package com.smarthomelightingsystem.dao;

import com.smarthomelightingsystem.data.Database;
import com.smarthomelightingsystem.model.pallate.Preset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class works with presets for LED strip
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
public class PresetDAO {

    private static final String TABLENAME = "preset";

    private Preset fill(ResultSet r) throws SQLException {

        Preset preset = new Preset();
        preset.setId(r.getInt("id"));
        preset.setPrimary(r.getString("primary"));
        preset.setSecondary(r.getString("secondary"));
        return preset;

    }

    public Preset getPreset(int id) {

        Database DB = new Database();
        Preset p = null;

        try {

            String q = "SELECT * FROM " + TABLENAME + " "
                    + "WHERE id = ?;";

            PreparedStatement ps = DB.connection.prepareStatement(q);
            ps.setInt(1, id);
            ResultSet r = DB.executePreparedStatement(ps);

            while (r.next()) { p = fill(r); }

        } catch (SQLException ex) { ex.printStackTrace(); }
        finally {

            DB.close();
            return p;

        }

    }

    public List<Preset> getAll() {

        Database DB = new Database();
        List<Preset> out = new ArrayList<>();

        try {

            String q = "SELECT * FROM " + TABLENAME;

            PreparedStatement ps = DB.connection.prepareStatement(q);
            ResultSet r = DB.executePreparedStatement(ps);

            while (r.next()) { out.add(fill(r)); }

        } catch (SQLException ex) { ex.printStackTrace(); }
        finally {

            DB.close();
            return out;

        }

    }
    
}

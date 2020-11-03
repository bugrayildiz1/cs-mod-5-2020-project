package com.smarthomelightingsystem.dao;

import com.smarthomelightingsystem.data.Database;
import com.smarthomelightingsystem.model.palette.Animation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class works with animations of LED strip 
 * shown on the Palette page of web interface
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
public class AnimationDAO {

	private static final String TABLENAME = "animation";

	/**
	 * Fills Animation with information from ResultSet.
	 * 
	 * @param r ResultSet object
	 * 
	 * @return Animation object with information from ResultSet
	 * 
	 * @throws SQLException upon SQL query failure
	 */
	private Animation fill(ResultSet r) throws SQLException {

		Animation animation = new Animation();
		animation.setId(r.getInt("id"));
		animation.setName(r.getString("name"));
		animation.setIcon(r.getString("icon"));
		return animation;

	}

	/**
	 * Retrieves the information from database regarding Animation.
	 * 
	 * @throws SQLException upon SQL query failure
	 */
	public Animation getAnimation(int id) {

		Database DB = new Database();
		Animation a = null;

		try {

			String q = "SELECT * FROM " + TABLENAME + " WHERE id = ?;";

			PreparedStatement ps = DB.connection.prepareStatement(q);
			ps.setInt(1, id);
			ResultSet r = DB.executePreparedStatement(ps);

			while (r.next()) { a = fill(r); }

		} catch (SQLException ex) { ex.printStackTrace(); }
		finally { DB.close(); }

		return a;

	}

	/**
	 * Retrieves the information about all Animations
	 * and returns it as a list of animations.
	 */
	public List<Animation> getAll() {

		Database DB = new Database();
		List<Animation> out = new ArrayList<>();

		try {

			String q = "SELECT * FROM " + TABLENAME + " ORDER BY id;";

			PreparedStatement ps = DB.connection.prepareStatement(q);
			ResultSet r = DB.executePreparedStatement(ps);

			while (r.next()) { out.add(fill(r)); }

		} catch (SQLException ex) { ex.printStackTrace(); }
		finally { DB.close(); }

		return out;

	}

}

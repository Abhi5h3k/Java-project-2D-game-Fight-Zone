package game_abhi;
//This class create Database and Table. 

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlLite {

	// Create a Database If not exist
	protected static void createDb() {
		Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Game_abhi_score.db");
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

	// Check if table exist so that we don't override existing
	protected static boolean tableExists(String tableName) {
		Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Game_abhi_score.db");
			DatabaseMetaData md = c.getMetaData();
			ResultSet rs = md.getTables(null, null, "score", null);
			rs.next();
			c.close();
			return rs.getRow() > 0;
		} catch (SQLException ex) {
			Logger.getLogger(ex.getClass().getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Create Table
	protected static void createTable() {
		Connection c = null;
		Statement stmt = null;
		if (!tableExists("score")) {
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:Game_abhi_score.db");
				stmt = c.createStatement();

				String sql = "CREATE TABLE score " + "( Player_one  TEXT    NOT NULL, "
						+ " Player_Two            TEXT     NOT NULL, " + " Winner        TEXT     NOT NULL)";

				stmt.executeUpdate(sql);
				stmt.close();
				c.close();

			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		}
	}

	// Insert data into table
	protected static void insertData(String p1Name, String p2Name, String winner) {
		// System.out.println(" insert check");
		Connection c = null;
		Statement stmt = null;

		if (tableExists("score")) {
			try {
				// System.out.println("not creating");
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:Game_abhi_score.db");

				Class.forName("org.sqlite.JDBC");

				c.setAutoCommit(false);

				stmt = c.createStatement();
				String sql = "INSERT INTO score VALUES (\" " + p1Name + "\",\"" + p2Name + "\",\"" + winner + "\");";
				// System.out.println(sql);

				// Empty table query
				// String del = "delete from score";

				stmt.executeUpdate(sql);
				stmt.close();
				c.commit();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}

		}

		else {
			// System.out.println(" creating");
			createTable();
			insertData(p1Name, p2Name, winner);
		}

	}

	// Retrieve data.
	protected static void getData() {
		System.out.println("getting data");
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Game_abhi_score.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM score;");

			while (rs.next()) {

				String p1Name = rs.getString(1);
				String p2Name = rs.getString(2);
				String Winner = rs.getString(3);

				System.out.println(
						"\nPlayer 1 Name : " + p1Name + "| Player 2 Name : " + p2Name + "| winner Name : " + Winner);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

}

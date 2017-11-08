package businesslayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import datalayer.DatabaseConnection;

public class AppData {
	
	private static List<Fighter> fighters = new ArrayList<Fighter>();
	private static AppData appData = null;
	
	private AppData() {
		
	}
	
	public static AppData getAppData() {
		if (appData == null) {
			initializeList();
			appData = new AppData();
		}
		
		return appData;
	}
	
	public static void initializeList() {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery("select * from fighter;");
			if(!(rs.first())){
				initializeData();
				rs = statement.executeQuery("select * from fighter;");
			} else {
				rs.beforeFirst();
			}
			
			while (rs.next()) {
				String name = rs.getString(1);
				String nickname = rs.getString(2);
				int power = rs.getInt(3);
				int health = rs.getInt(4);
				int mobility = rs.getInt(5);
				int techniques = rs.getInt(6);
				int range = rs.getInt(7);
				fighters.add(
						new Fighter(name, nickname, power, health, mobility,
								techniques, range));
			} // end while
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} // end try...catch
	}
	
	public static void initializeData() {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			try {
				statement.executeUpdate(
						"Insert into fighter(name, nickname, powerRating, "
						+ "healthRating, mobilityRating, techniquesRating, "
						+ "rangeRating) values ('Ryu', 'Eternal Wanderer', '4', "
						+ "'3', '3', '2', '4');");
			} catch (MySQLIntegrityConstraintViolationException msicve) {
				// does nothing - only used to avoid 'duplicate entry' error
				// message
			}

			try {
				statement.executeUpdate(
						"Insert into fighter(name, nickname, powerRating, "
						+ "healthRating, mobilityRating, techniquesRating, "
						+ "rangeRating) values ('Chun-Li', 'Blue Jade', '2', "
						+ "'2', '4', '4', '3');");
			} catch (MySQLIntegrityConstraintViolationException msicve) {
				// does nothing - only used to avoid 'duplicate entry' error
				// message
			}

			try {
				statement.executeUpdate(
						"Insert into fighter(name, nickname, powerRating, "
						+ "healthRating, mobilityRating, techniquesRating, "
						+ "rangeRating) values ('Nash', 'Paradoxical Avenger', "
						+ "'3', '3', '4', '3', '3');");
			} catch (MySQLIntegrityConstraintViolationException msicve) {
				// does nothing - only used to avoid 'duplicate entry' error
				// message
			}

			try {
				statement.executeUpdate(
						"Insert into fighter(name, nickname, powerRating, "
						+ "healthRating, mobilityRating, techniquesRating, "
						+ "rangeRating) values ('M. Bison', 'Emperor of Evil', "
						+ "'5', '3', '4', '4', '3');");
			} catch (MySQLIntegrityConstraintViolationException msicve) {
				// does nothing - only used to avoid 'duplicate entry' error
				// message
			}

			try {
				statement.executeUpdate(
						"Insert into fighter(name, nickname, powerRating, "
						+ "healthRating, mobilityRating, techniquesRating, "
						+ "rangeRating) values ('Cammy', 'Techniques of "
						+ "Incomparable Precision', '5', '3', '4', '4', '3');");
			} catch (MySQLIntegrityConstraintViolationException msicve) {
				// does nothing - only used to avoid 'duplicate entry' error
				// message
			}

			try {
				statement.executeUpdate(
						"Insert into fighter(name, nickname, powerRating, "
						+ "healthRating, mobilityRating, techniquesRating, "
						+ "rangeRating) values ('Birdie', 'Headbutt Happy Glutton'"
						+ ", '5', '3', '4', '4', '3');");
			} catch (MySQLIntegrityConstraintViolationException msicve) {
				// does nothing - only used to avoid 'duplicate entry' error
				// message
			}

			try {
				statement.executeUpdate(
						"Insert into fighter(name, nickname, powerRating, "
						+ "healthRating, mobilityRating, techniquesRating, "
						+ "rangeRating) values ('Ken', 'The Fire-Breathing Fist', "
						+ "'5', '3', '4', '4', '3');");
			} catch (MySQLIntegrityConstraintViolationException msicve) {
				// does nothing - only used to avoid 'duplicate entry' error
				// message
			}

			try {
				statement.executeUpdate(
						"Insert into fighter(name, nickname, powerRating, "
						+ "healthRating, mobilityRating, techniquesRating, "
						+ "rangeRating) values ('Necalli', 'Soul Consuming "
						+ "Darkness', '5', '3', '4', '4', '3');");
				statement.close();
			} catch (MySQLIntegrityConstraintViolationException msicve) {
				// does nothing - only used to avoid 'duplicate entry' error
				// message
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Fighter> getAllFighters() {
		return fighters;
	}
	
	public void addFighter(Fighter f) {
		fighters.add(f);
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(
					"Insert into fighter"
							+ "(name, nickname, powerRating, healthRating, "
							+ "mobilityRating, techniquesRating, rangeRating)"
							+ "values ('" + f.getName() + "', '"
							+ f.getNickname() + "', '"
							+ f.getPower() + "', '" 
							+ f.getHealth() + "', '" 
							+ f.getMobility() + "', '"
							+ f.getTechniques() + "', '"
							+ f.getRange() + "');");
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean removeFighter(String name){
		for(Fighter f : fighters) {
			if (f.getName() == name) {
				try {
					Connection conn = DatabaseConnection.getConnection();
					Statement statement = conn.createStatement();
					statement.executeUpdate("delete from fighter where name = '"
						+ name + "';");
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} // end try...catch
				
				return fighters.remove(f);
			} //end if
		} //end for-each loop
		return false;
	} // end removeCharacter
	
		
	
}

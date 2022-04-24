package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	public static Connection conn = null;
	
	public static void Connect() {
		try {
			String url = "jdbc:sqlite:database.db";
			conn = DriverManager.getConnection(url);
		}catch(SQLException e) {
			Util.Alert(e.getMessage());
		}
	}
	
	public static void Reg() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
		}
	}
	
	public static void Disconnect() {
		try {
			conn.close();
		}catch (SQLException e) {
			Util.Alert(e.getMessage());
		}
	}
	
}

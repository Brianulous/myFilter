package tw.brian.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	public  Connection createSQLServerConnection() {
		System.out.println("建立連線!");
		Properties props = new Properties();
		FileInputStream fis = null;
		Connection conn = null;
		try {
			fis = new FileInputStream("src/db.properties");
			props.load(fis);
			conn = DriverManager.getConnection(props.getProperty("MMSQL_DB_URL"), props.getProperty("MMSQL_DB_USER"),
					props.getProperty("MMSQL_DB_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}
	
	public  void closeConnection(Connection conn) {
	    try {
	        if (conn != null) {
	            conn.close();
	            System.out.println("關閉連線!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	

}

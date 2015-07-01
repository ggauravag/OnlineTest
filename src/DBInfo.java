import java.sql.Connection;
import java.sql.DriverManager;

public class DBInfo {
	static Connection con = null;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public static Connection getConn() {
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/onlinetest", "root", "rat");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
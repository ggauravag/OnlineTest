import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FirstSMS extends SmsLane {
	static Connection con = DBInfo.getConn();

	public static String send(String uid) {

		String query = "select * from user where userid = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString("fname");
				String mobile = "91" + rs.getString("mobile");
				String msg = "Dear "
						+ name.toUpperCase()
						+ ", You have successfully registered at RAT Online Portal, please check your email for verification link.\n- RAT Online Panel";

				if (msg.length() > 160) {
					msg = "Dear "
							+ name.toUpperCase()
							+ ", You have successfully registered at RAT Online Portal, please check your email for verification link.";
				}

				String response = sendSMS(mobile, msg);

				return response;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}

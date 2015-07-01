import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegSMS extends SmsLane {
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
						+ ", your email has been successfully verified. After confirmation from ADMIN your login credentials would be sent to you \n- RAT Online Panel";

				if (msg.length() > 160) {
					msg = "Dear "
							+ name.toUpperCase()
							+ ", your email has been successfully verified. After confirmation, your login credentials would be sent to you - RAT Online Panel";
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

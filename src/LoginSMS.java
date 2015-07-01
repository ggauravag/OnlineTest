import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginSMS extends SmsLane {
	static Connection con = DBInfo.getConn();

	public static String send(String uid) {

		String query = "SELECT user.fname,login.username, login.password, user.mobile FROM user, login WHERE user.userid = login.userid and login.userid=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString("fname");
				String password = AESCrypto.decrypt(rs.getString("password"));
				String uname = rs.getString("username");
				String mobile = "91" + rs.getString("mobile");
				String msg = "Dear " + name
						+ ", your login credentials are\nUSERNAME : " + uname
						+ "\nPASSWORD : " + password + "\n- RAT Online Panel";

				if (msg.length() > 160) {
					msg = "Dear " + name
							+ ", your login credentials are\nUSERNAME : "
							+ uname + "\nPASSWORD : " + password + "";
				}

				String response = SmsLane.sendSMS(mobile, msg);

				return response;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}

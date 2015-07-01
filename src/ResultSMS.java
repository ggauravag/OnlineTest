import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResultSMS extends SmsLane {
	static Connection con = DBInfo.getConn();

	public static String send(String uid) {

		String query = "SELECT user.fname,user.mobile,result.marks, result.exam_name, result.result FROM user, result WHERE user.userid = result.userid and result.userid=?; ";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString("fname");
				String exam = rs.getString("exam_name");
				int marks = rs.getInt("marks");
				String result = rs.getString("result");
				String mobile = "91" + rs.getString("mobile");
				String msg = "Dear " + name + ", your " + exam.toUpperCase()
						+ " Exam result is -\nMARKS : " + marks + "\nRESULT : "
						+ result.toUpperCase() + "\n- RAT Online Panel";

				if (msg.length() > 160) {
					msg = "Dear " + name + ", your " + exam.toUpperCase()
							+ " Exam result is -\nMARKS : " + marks
							+ "\nRESULT : " + result.toUpperCase() + "";
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

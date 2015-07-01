import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerifyAdmin extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3403143094917377011L;
	String userid = null;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession(false);

		PrintWriter out = res.getWriter();
		Connection con = DBInfo.getConn();
		if (session != null && session.getAttribute("adminid") != null) {
			userid = (String) session.getAttribute("adminid");
			String id = req.getParameter("id");
			try {
				if (req.getParameter("verify") != null) {

					String query = "update user set status_admin='Y' where userid=?";
					String query1 = "select uname,fname,email from user where userid=?";
					PreparedStatement ps = con.prepareStatement(query);
					PreparedStatement ps1 = con.prepareStatement(query1);
					ps.setString(1, id);
					ps1.setString(1, id);
					int result = ps.executeUpdate();
					ResultSet rs = ps1.executeQuery();
					rs.next();
					String username = rs.getString("uname");
					String fname = rs.getString("fname");
					String password = "";
					String email = rs.getString("email");
					String random = "abcdefghijklmnopqrstuvwxyz0123456789";
					int c;
					for (int i = 0; i < 8; i++) {
						c = (int) (Math.random() * 36);
						password = password + random.charAt(c);
					}

					query = "insert into login values(?,?,?,?,?,?,NULL)";
					ps = con.prepareStatement(query);
					ps.setString(1, username);
					ps.setString(2, AESCrypto.encrypt(password));
					ps.setString(3, "student");
					ps.setString(4, "Y");
					ps.setString(5, "N");
					ps.setString(6, id);
					int result1 = ps.executeUpdate();
					if (result == 1 && result1 == 1) {
						SendMailAdmin mail = new SendMailAdmin(email);

						mail.sendVerify(fname, username, password);
						String response = LoginSMS.send(id);
						if (response != null
								&& response == "Failed#Invalid Login") {
							out.println("<script>alert(\"Unable to send SMS.\n\n Please contact Gaurav for authentication.\");</script>");
						}
						out.println("<script>alert('User has been successfully verified')</script>");
					} else {
						out.println("<script>alert('Error : Unable to verify user')</script>");
					}
					res.setHeader("Refresh",
							"0;URL=http://localhost:8080/OnlineTest/servlet/ManageUser");
				}

				if (req.getParameter("disable") != null) {
					String query = "update user set status_admin='N' where userid=?";
					String query1 = "update login set verified='N',loggedin='N' where userid=?";
					PreparedStatement ps = con.prepareStatement(query);
					PreparedStatement ps1 = con.prepareStatement(query1);
					ps.setString(1, id);
					ps1.setString(1, id);
					int i = ps.executeUpdate();
					i = ps1.executeUpdate();
					if (i == 1) {
						out.println("<script>alert('User has been disabled. All login privileges are suspended')</script>");
					} else {
						out.println("<script>alert('Error : Unable to disable user.')</script>");

					}
					res.setHeader("Refresh",
							"0;URL=http://localhost:8080/OnlineTest/servlet/ManageUser");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			out.println("<script>alert('Requested page not available');</script>");
			res.setHeader("Refresh",
					"0;URL=http://localhost:8080/OnlineTest/servlet/FirstPage");
		}

	}

}
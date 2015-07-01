import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOut extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8033787454146795579L;
	String userid = null;
	String adminid = null;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		HttpSession session = req.getSession();
		PrintWriter out = res.getWriter();
		if (session == null
				|| (session.getAttribute("userid") == null && session
						.getAttribute("adminid") == null)) {

			Cookie[] c = req.getCookies();
			int flag = 1;
			for (Cookie ck : c) {
				if (ck.getName().equals("userid")) {
					userid = AESCrypto.decrypt(ck.getValue().toString());
					flag = 0;
				}
			}

			if (flag == 0) {
				int i = 0;
				try {
					String query = "update login set loggedin='N' where userid=?";
					Connection con = DBInfo.getConn();
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, userid);
					i = ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (i == 1) {
					out.println("<script>alert('You have been successfully logged out');</script>");
					session.invalidate();
					res.setHeader("Refresh",
							"0; URL=http://localhost:8080/OnlineTest/servlet/FirstPage");
				}

				else {
					out.println("<script>alert('Error : Unable to log out');</script>");
				}
			} else {
				res.sendRedirect("FirstPage");
			}
		} else {
			userid = (String) session.getAttribute("userid");
			adminid = (String) session.getAttribute("adminid");
			if (adminid != null && userid == null) {
				userid = adminid;
			}
			System.out.println("Id available to logout : " + userid);

			try {
				String query = "update login set loggedin='N' where userid=?";
				Connection con = DBInfo.getConn();
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, userid);

				int i = ps.executeUpdate();
				System.out.println("Userid available : " + userid);
				if (i == 1) {
					out.println("<script>alert('You have been successfully logged out');</script>");
					session.invalidate();
					res.setHeader("Refresh",
							"0; URL=http://localhost:8080/OnlineTest/servlet/FirstPage");
				}

				else {
					out.println("<script>alert('Error : Unable to log out');</script>");
				}

			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
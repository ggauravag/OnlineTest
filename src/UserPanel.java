import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserPanel extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6894047264262940165L;
	String userid = null;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession(false);

		PrintWriter out = res.getWriter();
		Connection con = DBInfo.getConn();
		if (session != null && session.getAttribute("userid") != null) {
			userid = (String) session.getAttribute("userid");
			System.out.println("Userid available : " + userid);
			try {

				String query = "select * from login where userid=?";
				String query1 = "select * from user where userid=?";
				String query2 = "update login set last_login=now(), loggedin='Y' where userid=?";

				PreparedStatement ps = con.prepareStatement(query);
				PreparedStatement ps1 = con.prepareStatement(query1);
				PreparedStatement ps2 = con.prepareStatement(query2);
				ps.setString(1, userid);
				ps1.setString(1, userid);
				ps2.setString(1, userid);

				ResultSet rs = ps.executeQuery();
				ResultSet rs1 = ps1.executeQuery();
				if (rs.next() && rs1.next()) {
					System.out.println("Data available");
					Timestamp t = rs.getTimestamp(7);
					String name = rs1.getString(3) + " " + rs1.getString(4);
					int i = ps2.executeUpdate();
					System.out.println("Record update : " + i);
					out.write("<!DOCTYPE html>\r\n");
					out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
					out.write("<head>\r\n");
					out.write("    <title>...::: RAT Online Academic Panel :::...</title>\r\n");
					out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
					out.write("    <link rel=\"stylesheet\" href=\"../styles/StyleSheet.css\" type=\"text/css\"/>\r\n");
					out.write("\r\n");
					out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js\"></script>\r\n");
					out.write("    <script src=\"http://code.jquery.com/ui/1.9.2/jquery-ui.js\"></script>\r\n");
					out.write("    <script type=\"text/javascript\" src=\"../script/marquee.js\"></script>\r\n");
					out.write("</head>\r\n");
					out.write("<body>\r\n");
					out.write("    \r\n");
					out.write("    <table style=\"background:url(../images/bg.jpg) no-repeat\">\r\n");
					out.write("        <tr>\r\n");
					out.write("            <td><div id=\"left\"></div></td>\r\n");
					out.write("            <td>\r\n");
					out.write("                <div id=\"center\">\r\n");
					out.write("\r\n");
					out.write("\r\n");
					out.write("                <div id=\"top\" height=\"400\" style=\"font-family: Lucida Calligraphy; background-color: #336699; color:cyan\">\r\n");
					out.write("                    <img src=\"../images/rat.png\" height=\"100\" style=\"float:left\"><h1 align=\"right\">Road Ahead Technologies&nbsp;</h1><br /><h2 align=\"right\">Online Academic Panel&nbsp;&nbsp;</h2>\r\n");
					out.write("                </div>\r\n");
					out.write("                    <br />\r\n");
					out.write("                   \r\n");
					out.write("                    <div id=\"display\">\r\n");
					out.write("                        <p id=\"text\" style=\"font-family:Consolas\">\r\n");
					out.write("                           The Online Academic Panel aims to provide practice questions and mock test for all online certifications exam like OCJP ( Oracle Certified Java Programmer ) etc. to all RATians. \r\n");
					out.write("                        </p>\r\n");
					out.write("                    </div>\r\n");
					out.write("\r\n");
					out.write("                    \r\n");
					out.write("                    \r\n");
					out.write("               \r\n");
					out.write("                    <br />\r\n");
					out.write("                    <br />\r\n");
					out.write("                    <center>\r\n");
					out.write("                    <div id=\"menu\">\r\n");
					out.write("                        <ul>\r\n");
					out.write("                                <li><a href=\"UserPanel\">Home</a></li>\r\n");
					out.write("                                <li><a href=\"ViewResultUser\">View Results</a></li>\r\n");
					out.write("                                <li><a href=\"ViewUserProfile\">View / Edit Profile</a></li>\r\n");
					out.write("                                <li><a href=\"AttemptExam\">Attempt Exam</a></li>\r\n");
					out.write("                                <li><a href=\"LogOut\">Log Out</a></li>\r\n");
					out.write("                            </ul>\r\n");
					out.write("                    </div>\r\n");
					out.write("                        </center>\r\n");
					out.write("                   \r\n");
					out.write("                    <br />\r\n");
					out.write("                    <center>\r\n");
					out.write("                    <div id=\"content\">\r\n");
					out.write("                        <br />\r\n");
					out.write("                        <br />\r\n");
					out.write("                        <h1>Welcome, " + name
							+ "</h1>\r\n");
					out.write("                        <br />\r\n");
					out.write("\r\n");
					out.write("                        <h2>Your last login : "
							+ t.toString() + " </h2>\r\n");
					out.write("\r\n");
					out.write("                    </div>\r\n");
					out.write("                    </center>\r\n");
					out.write("                    <br />\r\n");
					out.write("                   \r\n");
					out.write("                   \r\n");
					out.write("                \r\n");
					out.write("               \r\n");
					out.write("               \r\n");
					out.write("                    <center>\r\n");
					out.write("                <table width=\"700px\" style=\"font-size:20px;\">\r\n");
					out.write("                    <tr>\r\n");
					out.write("                        <td><a href=\"FirstPage\">Login</a></td>\r\n");
					out.write("                        <td><a href=\"UserReg\">Student Registration</a></td>\r\n");
					out.write("                        <td><a href=\"#\">About Us</a></td>\r\n");
					out.write("                        <td><a href=\"#\">Contact Us</a></td>\r\n");
					out.write("\r\n");
					out.write("                    </tr>\r\n");
					out.write("                </table>\r\n");
					out.write("                        </center>\r\n");
					out.write("                <div id=\"bot\" style=\"background-color: #336699; \">\r\n");
					out.write("                    <br />\r\n");
					out.write("                    Copyright @ RAT 2014 </br><br />Developed By <a href=\"#\">Gaurav Agarwal</a>\r\n");
					out.write("                </div>\r\n");
					out.write("                    </div>\r\n");
					out.write("            </td>\r\n");
					out.write("            <td><div id=\"right\"></div></td>\r\n");
					out.write("        </tr>\r\n");
					out.write("\r\n");
					out.write("    </table>\r\n");
					out.write("\r\n");
					out.write("\r\n");
					out.write("</body>\r\n");
					out.write("</html>\r\n");

				}

				else {
					System.out.println("Data unavailable");
					session.removeAttribute("userid");
					session.invalidate();
					res.sendRedirect("FirstPage");
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

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession(false);

		PrintWriter out = res.getWriter();
		Connection con = DBInfo.getConn();
		if (session != null && session.getAttribute("userid") != null) {
			userid = (String) session.getAttribute("userid");
			System.out.println("Userid available : " + userid);
			try {

				String query = "select * from login where userid=?";
				String query1 = "select * from user where userid=?";
				String query2 = "update login set last_login=now(), loggedin='Y' where userid=?";

				PreparedStatement ps = con.prepareStatement(query);
				PreparedStatement ps1 = con.prepareStatement(query1);
				PreparedStatement ps2 = con.prepareStatement(query2);
				ps.setString(1, userid);
				ps1.setString(1, userid);
				ps2.setString(1, userid);

				ResultSet rs = ps.executeQuery();
				ResultSet rs1 = ps1.executeQuery();
				if (rs.next() && rs1.next()) {
					System.out.println("Data available");
					Timestamp t = rs.getTimestamp(7);
					String name = rs1.getString(3) + " " + rs1.getString(4);
					int i = ps2.executeUpdate();
					System.out.println("Record update : " + i);
					out.write("<!DOCTYPE html>\r\n");
					out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
					out.write("<head>\r\n");
					out.write("    <title>...::: RAT Online Academic Panel :::...</title>\r\n");
					out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
					out.write("    <link rel=\"stylesheet\" href=\"../styles/StyleSheet.css\" type=\"text/css\"/>\r\n");
					out.write("\r\n");
					out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js\"></script>\r\n");
					out.write("    <script src=\"http://code.jquery.com/ui/1.9.2/jquery-ui.js\"></script>\r\n");
					out.write("    <script type=\"text/javascript\" src=\"../script/marquee.js\"></script>\r\n");
					out.write("</head>\r\n");
					out.write("<body>\r\n");
					out.write("    \r\n");
					out.write("    <table style=\"background:url(../images/bg.jpg) no-repeat\">\r\n");
					out.write("        <tr>\r\n");
					out.write("            <td><div id=\"left\"></div></td>\r\n");
					out.write("            <td>\r\n");
					out.write("                <div id=\"center\">\r\n");
					out.write("\r\n");
					out.write("\r\n");
					out.write("                <div id=\"top\" height=\"400\" style=\"font-family: Lucida Calligraphy; background-color: #336699; color:cyan\">\r\n");
					out.write("                    <img src=\"../images/rat.png\" height=\"100\" style=\"float:left\"><h1 align=\"right\">Road Ahead Technologies&nbsp;</h1><br /><h2 align=\"right\">Online Academic Panel&nbsp;&nbsp;</h2>\r\n");
					out.write("                </div>\r\n");
					out.write("                    <br />\r\n");
					out.write("                   \r\n");
					out.write("                    <div id=\"display\">\r\n");
					out.write("                        <p id=\"text\" style=\"font-family:Consolas\">\r\n");
					out.write("                           The Online Academic Panel aims to provide practice questions and mock test for all online certifications exam like OCJP ( Oracle Certified Java Programmer ) etc. to all RATians. \r\n");
					out.write("                        </p>\r\n");
					out.write("                    </div>\r\n");
					out.write("\r\n");
					out.write("                    \r\n");
					out.write("                    \r\n");
					out.write("               \r\n");
					out.write("                    <br />\r\n");
					out.write("                    <br />\r\n");
					out.write("                    <center>\r\n");
					out.write("                    <div id=\"menu\">\r\n");
					out.write("                        <ul>\r\n");
					out.write("                                <li><a href=\"UserPanel\">Home</a></li>\r\n");
					out.write("                                <li><a href=\"ViewResultUser\">View Results</a></li>\r\n");
					out.write("                                <li><a href=\"ViewUserProfile\">View / Edit Profile</a></li>\r\n");
					out.write("                                <li><a href=\"AttemptExam\">Attempt Exam</a></li>\r\n");
					out.write("                                <li><a href=\"LogOut\">Log Out</a></li>\r\n");
					out.write("                            </ul>\r\n");
					out.write("                    </div>\r\n");
					out.write("                        </center>\r\n");
					out.write("                   \r\n");
					out.write("                    <br />\r\n");
					out.write("                    <center>\r\n");
					out.write("                    <div id=\"content\">\r\n");
					out.write("                        <br />\r\n");
					out.write("                        <br />\r\n");
					out.write("                        <h1>Welcome, " + name
							+ "</h1>\r\n");
					out.write("                        <br />\r\n");
					out.write("\r\n");
					if (t != null) {
						out.write("                        <h2>Your last login : "
								+ t.toString() + " </h2>\r\n");
					} else {
						out.write("                        <h2>Your last login : None </h2>\r\n");
					}
					out.write("\r\n");
					out.write("                    </div>\r\n");
					out.write("                    </center>\r\n");
					out.write("                    <br />\r\n");
					out.write("                   \r\n");
					out.write("                   \r\n");
					out.write("                \r\n");
					out.write("               \r\n");
					out.write("               \r\n");
					out.write("                    <center>\r\n");
					out.write("                <table width=\"700px\" style=\"font-size:20px;\">\r\n");
					out.write("                    <tr>\r\n");
					out.write("                        <td><a href=\"FirstPage\">Login</a></td>\r\n");
					out.write("                        <td><a href=\"UserReg\">Student Registration</a></td>\r\n");
					out.write("                        <td><a href=\"AboutUs\">About Us</a></td>\r\n");
					out.write("                        <td><a href=\"ContactUs\">Contact Us</a></td>\r\n");
					out.write("\r\n");
					out.write("                    </tr>\r\n");
					out.write("                </table>\r\n");
					out.write("                        </center>\r\n");
					out.write("                <div id=\"bot\" style=\"background-color: #336699; \">\r\n");
					out.write("                    <br />\r\n");
					out.write("                    Copyright @ RAT 2014 </br><br />Developed By <a href=\"#\">Gaurav Agarwal</a>\r\n");
					out.write("                </div>\r\n");
					out.write("                    </div>\r\n");
					out.write("            </td>\r\n");
					out.write("            <td><div id=\"right\"></div></td>\r\n");
					out.write("        </tr>\r\n");
					out.write("\r\n");
					out.write("    </table>\r\n");
					out.write("\r\n");
					out.write("\r\n");
					out.write("</body>\r\n");
					out.write("</html>\r\n");

				}

				else {
					System.out.println("Data unavailable");
					session.removeAttribute("userid");
					session.invalidate();
					res.sendRedirect("FirstPage");
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
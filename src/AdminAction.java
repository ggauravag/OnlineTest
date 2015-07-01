import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminAction extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1018105028489571292L;
	String userid = null;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession(false);

		PrintWriter out = res.getWriter();
		String id = req.getParameter("id");
		System.out.println(id);
		id = id.replace(' ', '+');
		String uid = AESCrypto.decrypt(id);
		try {
			Connection con = DBInfo.getConn();

			if (session != null && session.getAttribute("adminid") != null) {
				userid = (String) session.getAttribute("adminid");
				out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
				out.write("<head>\r\n");
				out.write("    <title>...::: RAT Online Academic Panel :::...</title>\r\n");
				out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
				out.write("    <link rel=\"stylesheet\" href=\"../styles/StyleSheet1.css\" type=\"text/css\" />\r\n");
				out.write("\r\n");
				out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js\"></script>\r\n");
				out.write("    <script src=\"http://code.jquery.com/ui/1.9.2/jquery-ui.js\"></script>\r\n");
				out.write("    <script type=\"text/javascript\" src=\"../script/marquee.js\"></script>\r\n");
				out.write("</head>\r\n");
				out.write("<body>\r\n");
				out.write("\r\n");
				out.write("    <table style=\"background:url(../images/bg.jpg) no-repeat\">\r\n");
				out.write("        <tr>\r\n");
				out.write("            <td><div id=\"left\"></div></td>\r\n");
				out.write("            <td>\r\n");
				out.write("                <div id=\"center\">\r\n");
				out.write("\r\n");
				out.write("\r\n");
				out.write("                    <div id=\"top\" height=\"400\" style=\"font-family: Lucida Calligraphy; background-color: #336699; color:cyan\">\r\n");
				out.write("                        <img src=\"../images/rat.png\" height=\"100\" style=\"float:left\"><h1 align=\"right\">Road Ahead Technologies&nbsp;</h1><br /><h2 align=\"right\">Online Academic Panel&nbsp;&nbsp;</h2>\r\n");
				out.write("                    </div>\r\n");
				out.write("                    <br />\r\n");
				out.write("\r\n");
				out.write("                    <div id=\"display\">\r\n");
				out.write("                        <p id=\"text\" style=\"font-family:Consolas\">\r\n");
				out.write("                            The Online Academic Panel aims to provide practice questions and mock test for all online certifications exam like OCJP ( Oracle Certified Java Programmer ) etc. to all RATians.\r\n");
				out.write("                        </p>\r\n");
				out.write("                    </div>\r\n");
				out.write("\r\n");
				out.write("\r\n");
				out.write("\r\n");
				out.write("\r\n");
				out.write("                    <br />\r\n");
				out.write("                    <br />\r\n");
				out.write("                    <center>\r\n");
				out.write("                        <div id=\"menu\">\r\n");
				out.write("                            <ul>\r\n");
				out.write("                                <li><a href=\"AdminPanel\">Home</a></li>\r\n");
				out.write("                                <li><a href=\"ManageUser\">Manage Users</a></li>\r\n");
				out.write("                                <li><a href=\"ViewResultAdmin\">View Results</a></li>\r\n");
				out.write("                                <li><a href=\"ManageExam\">Manage Exams</a></li>\r\n");
				out.write("                                <li><a href=\"ViewAdminProfile\">View/Edit Profile</a></li>\r\n");
				out.write("                                <li><a href=\"LogOut\">Log Out</a></li>\r\n");
				out.write("                            </ul>\r\n");
				out.write("                        </div>\r\n");
				out.write("                    </center>\r\n");
				out.write("\r\n");
				out.write("                    \r\n");
				out.write("                    <br />\r\n");
				out.write("                    <br />\r\n");
				out.write("                    <br />\r\n");
				out.write("               \r\n");
				out.write("                    <center>\r\n");
				out.write("                        <form action=\"VerifyAdmin\" method=\"post\">\r\n");

				String query = "select * from user where userid=?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, uid);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {

					out.write("                            <input type=\"hidden\" name=\"id\" value=\""
							+ uid + "\" />\r\n");
					out.write("                            <table style=\"font-family:Consolas\">\r\n");
					out.write("                                <tr>\r\n");
					out.write("                                    <td><br/><label>Student's Id : </label></td>\r\n");
					out.write("                                    <td><br/><input type=\"text\" value=\""
							+ uid + "\" readonly/></td>\r\n");
					out.write("                                </tr>\r\n");
					out.write("                                <tr>\r\n");
					out.write("                                    <td><br/><label>Student's Institute Branch : </label></td>\r\n");
					out.write("                                    <td><br/><input type=\"text\" value=\""
							+ rs.getString("branch")
							+ "\" readonly /></td>\r\n");
					out.write("                                </tr>\r\n");
					out.write("                                <tr>\r\n");
					out.write("                                    <td><br/><label>Student's First Name : </label></td>\r\n");
					out.write("                                    <td><br/><input type=\"text\" value=\""
							+ rs.getString("fname") + "\" readonly /></td>\r\n");
					out.write("                                </tr>\r\n");
					out.write("                                <tr>\r\n");
					out.write("                                    <td><br/><label>Student's Last Name : </label></td>\r\n");
					out.write("                                    <td><br/><input type=\"text\" value=\""
							+ rs.getString("lname") + "\" readonly /></td>\r\n");
					out.write("                                </tr>\r\n");
					out.write("                                <tr>\r\n");
					out.write("                                    <td><br/><label>Student's User Name : </label></td>\r\n");
					out.write("                                    <td><br/><input type=\"text\" value=\""
							+ rs.getString("uname") + "\" readonly /></td>\r\n");
					out.write("                                </tr>\r\n");
					out.write("                                <tr>\r\n");
					out.write("                                    <td><br/><label>Student's Mobile : </label></td>\r\n");
					out.write("                                    <td><br/><input type=\"text\" value=\""
							+ rs.getString("mobile")
							+ "\" readonly /></td>\r\n");
					out.write("                                </tr>\r\n");
					out.write("                                <tr>\r\n");
					out.write("                                    <td><br/><label>Student's Email : </label></td>\r\n");
					out.write("                                    <td><br/><input type=\"text\" value=\""
							+ rs.getString("email") + "\" readonly /></td>\r\n");
					out.write("                                </tr>\r\n");
					out.write("                                <tr>\r\n");
					out.write("                                    <td><br/><label>Student's Address : </label></td>\r\n");
					out.write("                                    <td><br/><textarea readonly>"
							+ rs.getString("address") + "</textarea></td>\r\n");
					out.write("                                </tr>\r\n");

					if (rs.getString("status_user").equals("Y")) {

						out.write("<tr>\r\n");
						out.write("<td><br/><label>Student's Email : </label></td>\r\n");
						out.write("<td><br/><label style=\"color:green\">Verified</label></td>\r\n");
					}
					if (rs.getString("status_user").equals("N")) {
						out.write("<tr>\r\n");
						out.write("<td><br/><label>Student's Email : </label></td>\r\n");
						out.write("<td><br/><label style=\"color:red\">Not Verified</label></td>\r\n");
					}
					if (rs.getString("status_admin").equals("Y")) {
						out.write("<tr>\r\n");
						out.write("<td><br/><label>Student Verified : </label></td>\r\n");
						out.write("<td><br/><label style=\"color:green\">Yes</label></td>\r\n");
						out.write("</tr>\r\n");
					}
					if (rs.getString("status_admin").equals("N")) {
						out.write("<tr>\r\n");
						out.write("<td><br/><label>Student Verified : </label></td>\r\n");
						out.write("<td><br/><label style=\"color:red\">No</label></td>\r\n");
						out.write("</tr>\r\n");
					}
					out.write("                                <tr>\r\n");
					out.write("                                    <td><br /><br /><input type=\"submit\" name=\"verify\" value=\" Verify User \" /></td>\r\n");
					out.write("                                    <td><br /><br /><input type=\"submit\" name=\"disable\" value=\" Disable User \" /></td>\r\n");
					out.write("                                </tr>\r\n");
					out.write("                            </table>\r\n");
					out.write("\r\n");
					out.write("\r\n");
					out.write("                        </form>\r\n");
					out.write("\r\n");
					out.write("                    </center>\r\n");
					out.write("\r\n");
					out.write("                    <br />\r\n");
					out.write("                    <br />\r\n");
					out.write("\r\n");
					out.write("                    <center>\r\n");
					out.write("                        <table width=\"700px\" style=\"font-size:20px;\">\r\n");
					out.write("                            <tr>\r\n");
					out.write("                                <td><a href=\"FirstPage\">Login</a></td>\r\n");
					out.write("                                <td><a href=\"UserReg\">Student Registration</a></td>\r\n");
					out.write("                                <td><a href=\"AboutUs\">About Us</a></td>\r\n");
					out.write("                                <td><a href=\"ContactUs\">Contact Us</a></td>\r\n");
					out.write("\r\n");
					out.write("                            </tr>\r\n");
					out.write("\r\n");
					out.write("                        </table>\r\n");
					out.write("                    </center>\r\n");
					out.write("                    <div id=\"bot\" style=\"background-color: #336699; \">\r\n");
					out.write("                        <br />\r\n");
					out.write("                        Copyright @ RAT 2014 </br><br />Developed By <a href=\"#\">Gaurav Agarwal</a>\r\n");
					out.write("                    </div>\r\n");
					out.write("                </div>\r\n");
					out.write("            </td>\r\n");
					out.write("            <td><div id=\"right\"></div></td>\r\n");
					out.write("        </tr>\r\n");
					out.write("\r\n");
					out.write("    </table>\r\n");
					out.write("\r\n");
					out.write("\r\n");
					out.write("</body>\r\n");
					out.write("</html>\r\n");
					out.write("\r\n");

				}

			}

			else {
				out.println("<script>alert('Page Unavailable');</script>");
				res.setHeader("Refresh",
						"0;URL=http://localhost:8080/OnlineTest/servlet/FirstPage");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

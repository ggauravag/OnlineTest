import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePassword extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7926570275031134993L;
	String userid = null;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession(false);

		PrintWriter out = res.getWriter();
		if (session != null && session.getAttribute("userid") != null) {
			userid = (String) session.getAttribute("userid");
			out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
			out.write("<head>\r\n");
			out.write("    <title>...::: RAT Online Academic Panel :::...</title>\r\n");
			out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
			out.write("    <link rel=\"stylesheet\" href=\"../styles/StyleSheet.css\" type=\"text/css\" />\r\n");
			out.write("\r\n");
			out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js\"></script>\r\n");
			out.write("    <script src=\"http://code.jquery.com/ui/1.9.2/jquery-ui.js\"></script>\r\n");
			out.write("    <script type=\"text/javascript\" src=\"../script/marquee.js\"></script>\r\n");
			out.write("\t<script type=\"text/javascript\">\r\n");
			out.write("\tfunction validate()\r\n");
			out.write("\t{\r\n");
			out.write("\t\tvar x = document.my.newp.value;\r\n");
			out.write("\t\tvar y = document.my.cnfp.value;\r\n");
			out.write("\t\tif ( x == y )\r\n");
			out.write("\t\t\treturn true;\r\n");
			out.write("\t\telse\r\n");
			out.write("\t\t{\r\n");
			out.write("\t\t\talert('Passwords does not match');\r\n");
			out.write("\t\t\treturn false;\r\n");
			out.write("\t\t}\r\n");
			out.write("\t}\r\n");
			out.write("\t</script>\r\n");
			out.write("</head>\r\n");
			out.write("<body>\r\n");
			out.write("\r\n");
			out.write("    <table style=\"background:url(../images/bg.jpg) no-repeat\">\r\n");
			out.write("        <tr>\r\n");
			out.write("            <td><div id=\"left\" style=\"height:820px\"></div></td>\r\n");
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
			out.write("                                <li><a href=\"UserPanel\">Home</a></li>\r\n");
			out.write("                                <li><a href=\"ViewResultUser\">View Results</a></li>\r\n");
			out.write("                                <li><a href=\"EditProfile\">View / Edit Profile</a></li>\r\n");
			out.write("                                <li><a href=\"AttemptExam\">Attempt Exam</a></li>\r\n");
			out.write("                                <li><a href=\"LogOut\">Log Out</a></li>\r\n");
			out.write("                            </ul>\r\n");
			out.write("                        </div>\r\n");
			out.write("                    </center>\r\n");
			out.write("\r\n");
			out.write("                    <br />\r\n");
			out.write("                    <br />\r\n");
			out.write("                    <br />\r\n");
			out.write("                    <br />\r\n");
			out.write("\t\t\t\t\t <br />\r\n");
			out.write("                    <br />\r\n");
			out.write("\t\t\t\t\t\r\n");
			out.write("               \r\n");
			out.write("                    <center>\r\n");
			out.write("\t\t\t\t\t<hr>\r\n");
			out.write("\t\t\t\t\t<br/>\r\n");
			out.write("\t\t\t\t\t<br/>\r\n");
			out.write("\t\t\t\t\t<form name=\"my\" action=\"\" method=\"post\" onsubmit=\"return validate();\">\r\n");
			out.write("                            <input type=\"hidden\" name=\"id\" value=\"\" />\r\n");
			out.write("                            <table style=\"font-family:Consolas\">\r\n");
			out.write("                               \r\n");
			out.write("                                   \r\n");
			out.write("\t\t\t\t\t\t\t\t\r\n");
			out.write("                                <tr>\r\n");
			out.write("                                    <td><br /><label>Enter New Password : </label></td>\r\n");
			out.write("                                    <td><br /><input type=\"password\" name=\"newp\" /></td>\r\n");
			out.write("                                </tr>\r\n");
			out.write("                                <tr>\r\n");
			out.write("                                    <td><br /><label>Confirm Password : </label></td>\r\n");
			out.write("                                    <td><br /><input type=\"password\" name=\"cnfp\" /></td>\r\n");
			out.write("                                </tr>\r\n");
			out.write("                                \r\n");
			out.write("\r\n");
			out.write("                                    <td><br /><br /><input type=\"submit\" name=\"change\" value=\" Change Password \" /></td>\r\n");
			out.write("                                    <td><br /><br /><input type=\"reset\"  value=\" Reset \" /></td>\r\n");
			out.write("                                </tr>\r\n");
			out.write("                            </table>\r\n");
			out.write("\r\n");
			out.write("\r\n");
			out.write("                        </form>\r\n");
			out.write("\t\t\t\t\t\r\n");
			out.write("                    </center>\r\n");
			out.write("\r\n");
			out.write("                    <br />\r\n");
			out.write("                    <br />\r\n");
			out.write("\t\t\t\t\t<br/>\r\n");
			out.write("\t\t\t\t\t<hr>\r\n");
			out.write("\t\t\t\t\t<br/>\r\n");
			out.write("\t\t\t\t\t<br/>\r\n");
			out.write("\t\t\t\t\t<br />\r\n");
			out.write("                    <br />\r\n");
			out.write("\t\t\t\t\t<br/>\r\n");
			out.write("\t\t\t\t\t<br/>\r\n");
			out.write("                    <br/>\r\n");
			out.write("\t\t\t\t\t<br/>\r\n");
			out.write("\t\t\t\r\n");
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
			out.write("            <td><div id=\"right\" style=\"height:820px\"></div></td>\r\n");
			out.write("        </tr>\r\n");
			out.write("\r\n");
			out.write("    </table>\r\n");
			out.write("\r\n");
			out.write("\r\n");
			out.write("</body>\r\n");
			out.write("</html>\r\n");

		} else {
			out.println("<script>alert('Page unavailable');</script>");
			res.setHeader("Refresh",
					"0;URL=http://localhost:8080/OnlineTest/servlet/Firstpage");

		}

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String pass = req.getParameter("newp");

		HttpSession session = req.getSession(false);
		userid = (String) session.getAttribute("userid");
		PrintWriter out = res.getWriter();
		String query = "update login set password=? where userid=?";
		try {
			Connection con = DBInfo.getConn();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, AESCrypto.encrypt(pass));
			ps.setString(2, userid);
			int i = ps.executeUpdate();
			if (i == 1) {
				out.println("<script>confirm('Password Successfully changed');</script>");
				res.sendRedirect("UserPanel");
			} else {
				out.println("<script>confirm('Unable to change password');</script>");
				res.sendRedirect("UserPanel");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
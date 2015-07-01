import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewUserProfile extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4461113283809456219L;
	String userid = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = DBInfo.getConn();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession(false);

		PrintWriter out = res.getWriter();
		if (session != null && session.getAttribute("userid") != null) {
			userid = (String) session.getAttribute("userid");
			try {
				String query = "select * from user where userid=?";
				ps = con.prepareStatement(query);
				ps.setString(1, userid);
				rs = ps.executeQuery();
				rs.next();

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
				out.write("\t\r\n");
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
				out.write("                    <br/>\r\n");
				out.write("                    <center>\r\n");
				out.write("                    <div id=\"menu\">\r\n");
				out.write("                        <ul>\r\n");
				out.write("                                <li><a href=\"UserPanel\">Home</a></li>\r\n");
				out.write("                                \r\n");
				out.write("                            <li><a href=\"ViewResultUser\">View Results</a></li>\r\n");
				out.write("                              \r\n");
				out.write("                                <li><a href=\"ViewUserProfile\">View/Edit Profile</a></li>\r\n");
				out.write("\t\t\t\t\t\t\t\t <li><a href=\"AttemptExam\">Attempt Exam</a></li>\r\n");
				out.write("                                <li><a href=\"LogOut\">Log Out</a></li>\r\n");
				out.write("                            </ul>\r\n");
				out.write("                    </div>\r\n");
				out.write("                        </center>\r\n");
				out.write("                   \r\n");
				out.write("                    \r\n");
				out.write("                    <br />\r\n");
				out.write("                   \r\n");
				out.write("\t\t\t\t\t\t \r\n");
				out.write("\t\t\t\t\t\t<div style=\"font-family:Consolas\">\r\n");
				out.write("\t\t\t\t\t\t <center>\r\n");
				out.write("                        <br />\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t<form name=my method=post action=\"\" onsubmit=\"return check();\" >\r\n");

				out.write("\t\t\t\t\t\t\t\t\t\t\t\t<h3><u>**** User Details ****</u></h3>\r\n");
				out.write("\t\t\t\t                                  <table >\r\n");
				out.write("\t\t\t\t                                      <tr>\r\n");
				out.write("\t\t\t\t                                          <td><br/><label>Your Id : </label></td>\r\n");
				out.write("\t\t\t\t                                          <td><br/><input type=\"text\" value=\""
						+ userid + "\" disabled/></td>\r\n");
				out.write("\t\t\t\t                                      </tr>\r\n");
				out.write("\t\t\t\t                                      <tr>\r\n");
				out.write("\t\t\t\t                                          <td><br/><label>Institute Branch : </label></td>\r\n");
				out.write("\t\t\t\t                                          <td><br/><input type=\"text\" value=\""
						+ rs.getString("branch") + "\" disabled /></td>\r\n");
				out.write("\t\t\t\t                                      </tr>\r\n");
				out.write("\t\t\t\t                                      <tr>\r\n");
				out.write("\t\t\t\t                                          <td><br/><label> First Name : </label></td>\r\n");
				out.write("\t\t\t\t                                          <td><br/><input type=\"text\" value=\""
						+ rs.getString("fname") + "\" disabled /></td>\r\n");
				out.write("\t\t\t\t                                      </tr>\r\n");
				out.write("\t\t\t\t                                      <tr>\r\n");
				out.write("\t\t\t\t                                          <td><br/><label>Last Name : </label></td>\r\n");
				out.write("\t\t\t\t                                          <td><br/><input type=\"text\" value=\""
						+ rs.getString("lname") + "\" disabled /></td>\r\n");
				out.write("\t\t\t\t                                      </tr>\r\n");
				out.write("\t\t\t\t                                      <tr>\r\n");
				out.write("\t\t\t\t                                          <td><br/><label>User Name : </label></td>\r\n");
				out.write("\t\t\t\t                                          <td><br/><input type=\"text\" value=\""
						+ rs.getString("uname") + "\" disabled /></td>\r\n");
				out.write("\t\t\t\t                                      </tr>\r\n");
				out.write("\t\t\t\t                                      <tr>\r\n");
				out.write("\t\t\t\t                                          <td><br/><label>Your Mobile : </label></td>\r\n");
				out.write("\t\t\t\t                                          <td><br/><input type=\"text\" value=\""
						+ rs.getString("mobile") + "\" name=mobile /></td>\r\n");
				out.write("\t\t\t\t                                      </tr>\r\n");
				out.write("\t\t\t\t                                      <tr>\r\n");
				out.write("\t\t\t\t                                          <td><br/><label>Your Email : </label></td>\r\n");
				out.write("\t\t\t\t                                          <td><br/><input type=\"text\" value=\""
						+ rs.getString("email") + "\" disabled /></td>\r\n");
				out.write("\t\t\t\t                                      </tr>\r\n");
				out.write("\t\t\t\t                                      <tr>\r\n");
				out.write("\t\t\t\t                                          <td><br/><label> Address : </label></td>\r\n");
				out.write("\t\t\t\t                                          <td><br/><textarea name=add>"
						+ rs.getString("address") + "</textarea></td>\r\n");
				out.write("\t\t\t\t                                      </tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t  \r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t</table>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t<br/>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t<hr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t<br/>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t<table>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td>Change Password : &nbsp;&nbsp;</td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td><input type=password name=pass /></td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td><br/>Retype Password : &nbsp;&nbsp;</td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td><br/><input type=password name=cpass /></td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td><br/><br/><input type=submit value=\" UPDATE \" /></td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td><br/><br/><input type=reset value=\" RESET \" /></td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t\t</table>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t\t\t</form>\r\n");
				out.write("                    </center>\r\n");
				out.write("                    </div>\r\n");
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
				out.write("<script type=\"text/javascript\">\r\n");
				out.write("\tfunction check()\r\n");
				out.write("\t{\r\n");
				out.write("\t\tif(document.my.pass.value == \"\" && document.my.cpass.value == \"\")\r\n");
				out.write("\t\t{\r\n");
				out.write("\t\t\t\r\n");
				out.write("\t\t\tdocument.my.pass.disabled = true;\r\n");
				out.write("\t\t\tdocument.my.cpass.disabled = true;\r\n");
				out.write("\t\t\treturn true;\r\n");
				out.write("\t\t}\r\n");
				out.write("\t\telse\r\n");
				out.write("\t\t{\r\n");
				out.write("\t\t\tif(document.my.pass.value != document.my.cpass.value)\r\n");
				out.write("\t\t\t{\r\n");
				out.write("\t\t\t\talert('Both Passwords doesnt match.');\r\n");
				out.write("\t\t\t\treturn false;\r\n");
				out.write("\t\t\t}\r\n");
				out.write("\t\t\telse\r\n");
				out.write("\t\t\t{\r\n");
				out.write("\t\t\t\tdocument.my.cpass.disabled = true;\r\n");
				out.write("\t\t\t\treturn true;\r\n");
				out.write("\t\t\t}\r\n");
				out.write("\t\t}\r\n");
				out.write("\t}\r\n");
				out.write("\t</script>\r\n");
				out.write("</body>\r\n");
				out.write("</html>\r\n");
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
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String mobile = req.getParameter("mobile");
		String address = req.getParameter("add");
		String pass = req.getParameter("pass");
		PrintWriter out = res.getWriter();

		try {
			if (pass == null) {
				String query = "update user set mobile=?,address=? where userid=?";
				ps = con.prepareStatement(query);
				ps.setString(1, mobile);
				ps.setString(2, address);
				ps.setString(3, userid);
				int i = ps.executeUpdate();
				if (i == 1) {
					out.println("<script>alert('Details successfully updated');</script>");
				} else {
					out.println("<script>alert('Unable to update details');</script>");
				}
				res.setHeader("Refresh",
						"0;URL=http://localhost:8080/OnlineTest/servlet/ViewUserProfile");

			} else {
				String query = "update user set mobile=?,address=? where userid=?";
				ps = con.prepareStatement(query);
				ps.setString(1, mobile);
				ps.setString(2, address);
				ps.setString(3, userid);
				int i = ps.executeUpdate();
				query = "update login set password=? where userid=?";
				ps = con.prepareStatement(query);
				ps.setString(1, AESCrypto.encrypt(pass));
				ps.setString(2, userid);
				i = ps.executeUpdate();
				if (i == 1) {
					out.println("<script>alert('Details successfully updated');</script>");
				} else {
					out.println("<script>alert('Unable to update details');</script>");
				}
				res.setHeader("Refresh",
						"0;URL=http://localhost:8080/OnlineTest/servlet/ViewUserProfile");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
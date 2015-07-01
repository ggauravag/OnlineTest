import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForgotPassword extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6780833521835914471L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		PrintWriter out = res.getWriter();

		out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
		out.write("<head>\r\n");
		out.write("    <title>...::: RAT Online Academic Panel :::...</title>\r\n");
		out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
		out.write("    <link rel=\"stylesheet\" href=\"../styles/StyleSheet.css\" type=\"text/css\" />\r\n");
		out.write("\r\n");
		out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js\"></script>\r\n");
		out.write("    <script src=\"http://code.jquery.com/ui/1.9.2/jquery-ui.js\"></script>\r\n");
		out.write("    <script type=\"text/javascript\" src=\"../script/marquee.js\"></script>\r\n");
		out.write("\t\r\n");
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
		out.write("\t\t\t\t\t<br/>\r\n");
		out.write("\r\n");
		out.write("                  \r\n");
		out.write("                    <br />\r\n");
		out.write("                 \r\n");
		out.write("                    <br />\r\n");
		out.write("\t\t\t\t\t\r\n");
		out.write("               \r\n");
		out.write("                    <center>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t\r\n");
		out.write("\t\t\t\t\t<div style=\"height:430px;width:800px;font-family:Consolas;font-size:25px;\">\r\n");
		out.write("\t\t\t\t\t<h2><u>**** Password Reset ****</u></h2>\r\n");
		out.write("\t\t\t\t\t<br/>\r\n");
		out.write("\t\t\t\t\t<br/>\r\n");
		out.write("\t\t\t\t\t<hr>\r\n");
		out.write("\t\t\t\t\t<br/>\r\n");
		out.write("\t\t\t\t\t<br/>\r\n");
		out.write("\t\t\t\t\t<form action = '' method=post>\r\n");
		out.write("\t\t\t\t\t<table>\r\n");
		out.write("\t\t\t\t\t<tr>\r\n");
		out.write("\t\t\t\t\t\t<td><label>Enter registered E-Mail Id : </label></td>\r\n");
		out.write("\t\t\t\t\t\t<td><input type=text name=email /></td>\r\n");
		out.write("\t\t\t\t\t\r\n");
		out.write("\t\t\t\t\t</tr>\r\n");
		out.write("\t\t\t\t\t<tr>\r\n");
		out.write("\t\t\t\t\t\t<td><br/><br/><input type=submit value=' SUBMIT '/></td>\r\n");
		out.write("\t\t\t\t\t\t<td><br/><br/><input type=reset value=' RESET ' /></td>\r\n");
		out.write("\t\t\t\t\t</tr>\r\n");
		out.write("\t\t\t\t\t</table>\r\n");
		out.write("\t\t\t\t\t</form>\r\n");
		out.write("\t\t\t\t\t<br/>\r\n");
		out.write("\t\t\t\t\t<br/>\r\n");
		out.write("\t\t\t\t\t<hr>\r\n");
		out.write("\t\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t\t\r\n");
		out.write("                    </center>\r\n");
		out.write("\r\n");
		out.write("                    <br />\r\n");
		out.write("                   \r\n");
		out.write("\t\t\t\t\t\r\n");
		out.write("\t\t\t\t\t<br/>\r\n");
		out.write("\t\t\t\t\t<br />\r\n");
		out.write("              \r\n");
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
		out.write("\r\n");

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			Connection con = DBInfo.getConn();
			String email = req.getParameter("email");
			String query = "select * from user where email=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			PrintWriter out = res.getWriter();
			if (rs.next()) {
				ResetPasswordMail.sendResetLink(email);
				out.println("<script>alert('Password reset link sent on email.');</script>");
				res.setHeader("Refresh",
						"0;URL=http://localhost:8080/OnlineTest/servlet/FirstPage");
			} else {
				out.println("<script>alert('E-Mail Id not registered');</script>");
				res.setHeader("Refresh",
						"0;URL=http://localhost:8080/OnlineTest/servlet/FirstPage");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

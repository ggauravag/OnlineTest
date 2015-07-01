import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewResultUser extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3173573525851808956L;
	String userid = null;
	Connection con = DBInfo.getConn();
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession(false);
		PrintWriter out = res.getWriter();

		if (session != null && session.getAttribute("userid") != null) {
			System.out.println("No exception yet ");
			userid = (String) session.getAttribute("userid");
			String query = "select * from result where userid = ?";

			try {

				ps = con.prepareStatement(query);
				ps.setString(1, userid);
				rs = ps.executeQuery();

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
				out.write("                    <br />\r\n");
				out.write("                    <center>\r\n");
				out.write("                        <div id=\"menu\">\r\n");
				out.write("                            <ul>\r\n");
				out.write("                                <li><a href=\"UserPanel\">Home</a></li>\r\n");
				out.write("                                <li><a href=\"ViewResultUser\">View Results</a></li>\r\n");
				out.write("                                <li><a href=\"ViewUserProfile\">View / Edit Profile</a></li>\r\n");
				out.write("                                <li><a href=\"AttemptExam\">Attempt Exam</a></li>\r\n");
				out.write("                                <li><a href=\"LogOut\">Log Out</a></li>\r\n");
				out.write("                            </ul>\r\n");
				out.write("                        </div>\r\n");
				out.write("                    </center>\r\n");
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
				out.write("\t\t\t\t\t<u><b>***** Details of Online Test successfully attempted *****</b></u>\r\n");
				out.write("                    <br />\r\n");
				out.write("                    <br />\r\n");
				out.write("                    \r\n");
				out.write("\t\t\t\t\t<table style=\"font-size:20px;width:1000px;margin-left:-100px;text-align:center;\" >\r\n");
				out.write("\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t<th>Exam Date<br/></th>\r\n");
				out.write("\t\t\t\t\t\t\t<th>Exam Name<br/></th>\r\n");
				out.write("\t\t\t\t\t\t\t<th>Marks Obtained<br/></th>\r\n");
				out.write("\t\t\t\t\t\t\t<th>Total Marks<br/></th>\r\n");
				out.write("\t\t\t\t\t\t\t<th>Result<br/></th>\r\n");
				out.write("\t\t\t\t\t\t\t<th>Options<br/></th>\r\n");
				out.write("\t\t\t\t\t\t</tr>\r\n");
				String q1 = null;
				ResultSet rs1;
				while (rs.next()) {
					q1 = "select question from exam where exam_name=?";
					ps = con.prepareStatement(q1);
					ps.setString(1, rs.getString("exam_name"));
					rs1 = ps.executeQuery();
					rs1.next();
					out.write("\t\t\t\t\t\t<tr>\r\n");
					out.write("\t\t\t\t\t\t\t<td><br/>"
							+ rs.getDate("exam_date") + "<br/></td>\r\n");
					out.write("\t\t\t\t\t\t\t<td><br/>"
							+ rs.getString("exam_name").toUpperCase()
							+ "</td>\r\n");
					out.write("\t\t\t\t\t\t\t<td><br/>" + rs.getInt("marks")
							+ "</td>\r\n");
					out.write("\t\t\t\t\t\t\t<td><br/>" + (rs1.getInt(1) * 4)
							+ "</td>\r\n");
					out.write("\t\t\t\t\t\t\t<td><br/>"
							+ rs.getString("result").toUpperCase()
							+ "</td>\r\n");
					out.write("\t\t\t\t\t\t\t<td><br/><a href=\"ResultDetail?exam="
							+ rs.getString(1)
							+ "\">Detailed Result</a></td>\r\n");
					out.write("\t\t\t\t\t\t</tr>\r\n");
				}

				out.write("\t\t\t\t\t</table>\r\n");
				out.write("\t\t\t\t\t\r\n");
				out.write("\t\t\t\t\t<br/>\r\n");
				out.write("\t\t\t\t\t<br/>\r\n");
				out.write("\t\t\t\t\t\r\n");
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
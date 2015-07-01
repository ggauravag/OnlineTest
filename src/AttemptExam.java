import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AttemptExam extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 359814227281771646L;
	String userid = null;
	Connection con = DBInfo.getConn();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession(false);

		PrintWriter out = res.getWriter();

		PreparedStatement ps = null;
		ResultSet rs = null;
		if (session != null && session.getAttribute("userid") != null) {
			userid = (String) session.getAttribute("userid");
			try {
				String query = "select * from exam";
				ps = con.prepareStatement(query);
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
				out.write("\t\t\t\t\t<div style=\"height:430px;\">\r\n");
				out.write("\t\t\t\t\t<form name=\"my\" action=\"\" method=\"post\" >\r\n");
				out.write("                            <input type=\"hidden\" name=\"id\" value=\"\" />\r\n");
				out.write("                            <table style=\"font-family:Consolas;font-size:20px;text-align:center;width=750px;\" border=1 cellspacing=10 >\r\n");
				out.write("                               \r\n");
				out.write("                                   <tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t   <th>Exam-Name</th>\r\n");
				out.write("\t\t\t\t\t\t\t\t   <th>Number of Questions</th>\r\n");
				out.write("\t\t\t\t\t\t\t\t   <th>Total Marks</th>\r\n");
				out.write("\t\t\t\t\t\t\t\t   <th>Select</th>\r\n");
				out.write("\t\t\t\t\t\t\t\t   </tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\r\n");
				out.write("                                \r\n");

				while (rs.next()) {
					out.write("                               <tr>\r\n");
					out.write("                                    \r\n");
					out.write("\t\t\t\t\t\t\t\t\t<td><br /><label>"
							+ rs.getString("exam_name").toUpperCase()
							+ "</label></td>\r\n");
					out.write("\t\t\t\t\t\t\t\t\t<td><br /><label>"
							+ rs.getInt("marks") + "</label></td>\r\n");
					out.write("\t\t\t\t\t\t\t\t\t<td><br /><label>"
							+ rs.getInt("question") + "</label></td>\r\n");
					out.write("<td><br /><input type=\"radio\" name=\"exam\" value='"
							+ rs.getString("exam_name") + "' /></td>\r\n");
					out.write("</tr>\r\n");
				}

				out.write("\t\t\t\t\t\t\t\t\r\n");
				out.write("\t\t\t\t\t\t\t\t</table>\r\n");
				out.write("\t\t\t\t\t\t\t\t<table>\r\n");
				out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
				out.write("                                    <td colspan=2><br /><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"submit\" name=\"change\" value=\" Select Exam \" /></td>\r\n");
				out.write("                                    <td colspan=2><br /><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"reset\"  value=\" Reset \" /></td>\r\n");
				out.write("                                </tr>\r\n");
				out.write("                            </table>\r\n");
				out.write("\r\n");
				out.write("\r\n");
				out.write("                        </form>\r\n");
				out.write("\t\t\t\t\t</div>\r\n");
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
			out.println("<script>alert('Page Unavailable');</script>");
			res.setHeader("Refresh",
					"0;URL=http://localhost:8080/OnlineTest/servlet/FirstPage");

		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String exam = req.getParameter("exam");
		String query = "select exam_name,userid from result";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			PrintWriter out = res.getWriter();
			int flag = 0;
			while (rs.next()) {
				if (rs.getString("exam_name").equals(exam)
						&& rs.getString("userid").equals(userid)) {
					flag = 1;
					break;
				}
			}
			if (flag == 1) {
				out.println("<script>alert('You have already attempted " + exam
						+ " exam. Please check result')</script>");
				res.setHeader("Refresh",
						"0;URL=http://localhost:8080/OnlineTest/servlet/ViewResultUser");
			}
			query = "select * from exam where exam_name=?";
			ps = con.prepareStatement(query);
			ps.setString(1, exam);
			rs = ps.executeQuery();

			if (rs.next()) {

				out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
				out.write("<head>\r\n");
				out.write("    <title>...::: RAT Online Academic Panel :::...</title>\r\n");
				out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
				out.write("    <link rel=\"stylesheet\" href=\"../styles/StyleSheet.css\" type=\"text/css\" />\r\n");
				out.write("\r\n");
				out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js\"></script>\r\n");
				out.write("    <script src=\"http://code.jquery.com/ui/1.9.2/jquery-ui.js\"></script>\r\n");
				out.write("    <script type=\"text/javascript\" src=\"../script/marquee.js\"></script>\r\n");
				out.write("\t<script>\r\n");
				out.write("\t\r\n");
				out.write("\t\r\n");
				out.write("\tfunction test()\r\n");
				out.write("\t{\r\n");
				out.write("\t\twin = window.open(\"StartExam?exam="
						+ AESCrypto.encrypt(exam)
						+ "\",\"_blank\",'alwaysRaised=yes');\r\n");
				out.write("\t\tmyInterval = window.setInterval(\"checkWindow()\",0);\r\n");
				out.write("\t}\r\n");
				out.write("\t\r\n");
				out.write("\tfunction checkWindow()\r\n");
				out.write("\t{\r\n");
				out.write("\t\tif(win.closed)\r\n");
				out.write("\t\t{ \r\n");
				out.write("\t\talert('Test Over ');window.open('ViewResultUser?exam="
						+ AESCrypto.encrypt(exam) + "','_self','');\r\n");
				out.write("\t\twindow.clearInterval(myInterval); \r\n");
				out.write("\t\t}\r\n");
				out.write("\t\r\n");
				out.write("\t}\r\n");
				out.write("\t\r\n");
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
				out.write("\t\t\t\t\t<div style=\"height:430px;width:800px;font-family:Consolas;font-size:17px;\">\r\n");
				out.write("\t\t\t\t\t<u><b>*****  Instructions : Online "
						+ rs.getString("exam_name").toUpperCase()
						+ " Test  *****</b></u>\r\n");
				out.write("                    <br />\r\n");
				out.write("                    <br />\r\n");
				out.write("                    <ul>\r\n");
				out.write("                        <li> The RAT Student Assessment Online Test will be of "
						+ (rs.getInt("question") * 20)
						+ " seconds duration</li>\r\n");
				out.write("                        <br />\r\n");
				out.write("                        <li>The RAT Student Assessment Test Paper will consist "
						+ rs.getInt("question")
						+ " questions and all question will have equal weightage.</li>\r\n");
				out.write("                        <br />\r\n");
				out.write("                        <li>  For Each Correct Answer in Exam, Students will be allotted 4 (four) marks.</li>\r\n");
				out.write("                        <br />\r\n");
				out.write("                        <li> ¼ (one fourth) marks i.e. one mark will be deducted for each incorrect Answers. No deduction from the total score will be made if no response is indicated for a question.</li>\r\n");
				out.write("                        <br />\r\n");
				out.write("                        <li> There is only one correct response for each question out of four responses given.</li>\r\n");
				out.write("                        <br />\r\n");
				out.write("                        <li> Do not hit back or refresh button of the browser during the test.</li>\r\n");
				out.write("                    </ul>\r\n");
				out.write("\t\t\t\t\t<br/>\r\n");
				out.write("\t\t\t\t\t<br/>\r\n");
				out.write("\t\t\t\t\t<table>\r\n");
				out.write("\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t<td><input style=\"background-color:lightgreen;height:40px;width:80px;\" type=button onclick=\"test()\" value=\" I Agree \" /></td>\r\n");
				out.write("\t\t\t\t\t\t<td>&nbsp;&nbsp;&nbsp;&nbsp;<input style=\"background-color:red;height:40px;width:100px;\" type=button onclick=\"window.history.back()\" value=\" I do not Agree \" /></td>\r\n");
				out.write("\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t</table>\r\n");
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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
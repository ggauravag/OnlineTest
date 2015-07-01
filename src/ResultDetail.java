import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ResultDetail extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8356513484204583884L;
	String userid = null;
	Connection con = DBInfo.getConn();
	PreparedStatement ps = null;
	ResultSet rs = null;
	String exam = null;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession(false);

		PrintWriter out = res.getWriter();

		if (session != null && session.getAttribute("userid") != null) {
			userid = (String) session.getAttribute("userid");
			exam = req.getParameter("exam");
			String query = "select * from result where exam_name=? and userid=?";
			try {
				ps = con.prepareStatement(query);
				ps.setString(1, exam);
				ps.setString(2, userid);
				rs = ps.executeQuery();
				rs.next();
				String correctans = rs.getString("correct_ans");
				String userans = rs.getString("user_ans");
				rs.getInt("marks");
				String result = rs.getString("result");
				int quest = correctans.length();
				int attempt = 0;
				int correct = 0;
				int wrong = 0;

				for (int i = 0; i < quest; i++) {
					if (correctans.charAt(i) == userans.charAt(i)) {
						attempt++;
						correct++;
					} else if (userans.charAt(i) == 'e') {

					} else {
						attempt++;
						wrong++;
					}

				}

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
				out.write("                                <li><a href=\"EditProfile\">View / Edit Profile</a></li>\r\n");
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
				out.write("\t\t\t\t\t<u><b>***** Detailed Result Online Exam : "
						+ exam.toUpperCase() + " *****</b></u>\r\n");
				out.write("                    <br />\r\n");
				out.write("                    <br />\r\n");
				out.write("                    <table style=\"width:800px;\">\r\n");
				out.write("\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t<td>\r\n");
				out.write("\t\t\t\t\t\t\t<table style=\"font-size:20px;text-align:center;\" >\r\n");
				out.write("\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t<td>Total Number of Questions : </td>\r\n");
				out.write("\t\t\t\t\t\t\t\t<td>" + quest + "</td>\r\n");
				out.write("\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t<td>Attempted : </td>\r\n");
				out.write("\t\t\t\t\t\t\t\t<td>" + attempt + "</td>\r\n");
				out.write("\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t<td>Correct : </td>\r\n");
				out.write("\t\t\t\t\t\t\t\t<td>" + correct + "</td>\r\n");
				out.write("\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t<td>Incorrect : </td>\r\n");
				out.write("\t\t\t\t\t\t\t\t<td>" + wrong + "</td>\r\n");
				out.write("\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t<td>Result : </td>\r\n");
				out.write("\t\t\t\t\t\t\t\t<td>" + result.toUpperCase()
						+ "</td>\r\n");
				out.write("\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t</table>\r\n");
				out.write("\t\t\t\t\t\t</td>\r\n");
				out.write("\t\t\t\t\t\t<td>\r\n");
				out.write("\t\t\t\t\t\t<img height=100 src=\"../images/"
						+ result + "1.jpg\" />\r\n");
				out.write("\t\t\t\t\t\t</td>\r\n");
				out.write("\t\t\t\t\t</tr>\r\n");

				out.write("\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t<td><br/><br/>\r\n");
				out.write("\t\t\t\t\t\t\t<table style=\"margin-left:-75px;\">\r\n");
				out.write("\t\t\t\t\t\t\t\t<tr>\r\n");

				out.write("\t\t\t\t\t\t\t\t\t<td><strong>Ques No&nbsp;&nbsp;</strong></td>\r\n");
				int h = 0;
				for (h = 0; h < quest; h++) {
					out.println("<td>" + (h + 1) + "&nbsp;</td>");
				}
				out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t<tr>\r\n");

				out.write("\t\t\t\t\t\t\t\t\t<td><strong>Attempt&nbsp;&nbsp;</strong></td>\r\n");
				for (h = 0; h < quest; h++) {
					out.println("<td>" + userans.charAt(h) + "&nbsp;</td>");
				}
				out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t<tr>\r\n");

				out.write("\t\t\t\t\t\t\t\t\t<td><strong>Correct&nbsp;&nbsp;</strong></td>\r\n");
				for (h = 0; h < quest; h++) {
					out.println("<td>" + correctans.charAt(h) + "&nbsp;</td>");
				}
				out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t</table>\r\n");
				out.write("\t\t\t\t\t\t</td>\r\n");
				out.write("\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t</table>\r\n");

				out.write("\t\t\t\t\t\r\n");
				out.write("\t\t\t\t\t</div>\r\n");
				out.write("\t\t\t\t\t\r\n");
				out.println("<form action='' method='post' >");
				out.println("<table style='margin-top:-32px;'><tr><td><input type=button value=' Print Result ' onclick='print();' />&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type=submit value=' Email Result ' /></td></tr></table>");
				out.write("\t\t\t\t\t<br/>\r\n");
				out.write("                    </center>\r\n");
				out.write("\r\n");
				out.write("                    <br />\r\n");
				out.write("                   \r\n");
				out.write("\t\t\t\t\t\r\n");
				out.write("\t\t\t\t\t<br/>\r\n");

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
		SendResult.send(userid, exam);
		PrintWriter out = res.getWriter();
		out.println("<script>alert('Result successfully emailed. Check your email !');</script>");
		res.setHeader("Refresh",
				"0;URL=http://localhost:8080/OnlineTest/servlet/ViewResultUser");
	}

}
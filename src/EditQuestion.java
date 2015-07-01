import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditQuestion extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2506678796930913790L;
	String userid = null;
	Connection con = DBInfo.getConn();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession(false);

		PrintWriter out = res.getWriter();

		if (session != null && session.getAttribute("adminid") != null) {
			userid = (String) session.getAttribute("adminid");
			String exam = req.getParameter("exam");
			int quesno = Integer.parseInt(req.getParameter("ques"));
			String query = "select * from " + exam + " where ques_no = ?";
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, quesno);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					out.write("<!DOCTYPE html>\r\n");
					out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
					out.write("<head>\r\n");
					out.write("    <title>...::: RAT Online Academic Panel :::...</title>\r\n");
					out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
					out.write("    <link rel=\"stylesheet\" href=\"../styles/StyleSheet1.css\" type=\"text/css\"/>\r\n");
					out.write("\r\n");
					out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js\"></script>\r\n");
					out.write("    <script src=\"http://code.jquery.com/ui/1.9.2/jquery-ui.js\"></script>\r\n");
					out.write("    <script type=\"text/javascript\" src=\"../script/marquee.js\"></script>\r\n");
					out.write("    \r\n");
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
					out.write("                                <li><a href=\"AdminPanel\">Home</a></li>\r\n");
					out.write("                                <li><a href=\"ManageUser\">Manage Users</a></li>\r\n");
					out.write("                            <li><a href=\"ViewResultAdmin\">View Results</a></li>\r\n");
					out.write("                                <li><a href=\"ManageExam\">Manage Exams</a></li>\r\n");
					out.write("                                <li><a href=\"ViewAdminProfile\">View/Edit Profile</a></li>\r\n");
					out.write("                                <li><a href=\"LogOut\">Log Out</a></li>\r\n");
					out.write("                            </ul>\r\n");
					out.write("                    </div>\r\n");
					out.write("                        </center>\r\n");
					out.write("                   \r\n");
					out.write("                    <br />\r\n");
					out.write("                    \r\n");
					out.write("                    <center>\r\n");
					out.write("                      \r\n");
					out.write("                        <div id=\"ques\" style=\"font-family:Consolas;\">\r\n");
					out.write("                            <br />\r\n");
					out.write("                            <h2><u>Question Details</u></h2>\r\n");
					out.write("                            <br />\r\n");
					out.write("                            <br />\r\n");
					out.write("                            <form action=\"\" method=\"post\" name=\"my\" onsubmit=\"\">\r\n");
					out.write("                                <center>\r\n");
					out.write("                                    <table >\r\n");

					out.write("                                       <tr>\r\n");
					out.write("                                         \r\n");
					out.write("                                           <td>&nbsp;&nbsp;&nbsp;Question No : </td>\r\n");
					out.write("                                           <td>\r\n");
					out.write("                                               <input type=text name=quesno value='"
							+ rs.getInt("ques_no") + "' readonly />\r\n");
					out.write("                                           </td>\r\n");
					out.write("\r\n");
					out.write("                                       </tr>\r\n");
					out.write("                                        <tr>\r\n");
					out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Question :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
					out.write("                                            <td><br /><textarea name=\"question\">"
							+ rs.getString("question") + "</textarea></td>\r\n");
					out.write("                                        </tr>\r\n");
					out.write("                                        <tr>\r\n");
					out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;Option (a) :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
					out.write("                                            <td><br /><textarea name=\"opa\">"
							+ rs.getString("op1") + "</textarea></td>\r\n");
					out.write("                                        </tr>\r\n");
					out.write("                                        <tr>\r\n");
					out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;Option (b) :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
					out.write("                                            <td><br /><textarea name=\"opb\">"
							+ rs.getString("op2") + "</textarea></td>\r\n");
					out.write("                                        </tr>\r\n");
					out.write("                                        <tr>\r\n");
					out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;Option (c) :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
					out.write("                                            <td><br /><textarea name=\"opc\">"
							+ rs.getString("op3") + "</textarea></td>\r\n");
					out.write("                                        </tr>\r\n");
					out.write("                                        <tr>\r\n");
					out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;Option (d) :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
					out.write("                                            <td><br /><textarea name=\"opd\">"
							+ rs.getString("op4") + "</textarea></td>\r\n");
					out.write("                                        </tr>\r\n");
					out.write("                                        <tr>\r\n");
					out.write("                                            <td><br />Correct Answer :&nbsp;&nbsp;</td>\r\n");
					out.write("                                            <td><br /><input type=\"text\" name=\"ans\" value=\""
							+ rs.getString("correct") + "\"/></td>\r\n");
					out.write("                                        </tr>\r\n");
					out.write("                                        <tr>\r\n");
					out.write("                                            <td><br /><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"submit\" value=\" Edit Question \"/></td>\r\n");
					out.println("<td><input type=hidden name=exam value='"
							+ exam + "' /></td>");
					out.write("                                        </tr>\r\n");
					out.write("                            </table>\r\n");
					out.write("                                    </center>\r\n");
					out.write("                         </form>\r\n");
					out.write("                        </div>\r\n");
					out.write("                   \r\n");
					out.write("                      \r\n");
					out.write("                    \r\n");
					out.write("                    \r\n");
					out.write("                   \r\n");
					out.write("                    <br />\r\n");
					out.write("                <br />\r\n");
					out.write("                <br />\r\n");
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
					out.write("         \r\n");
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

			}

			catch (Exception e) {
				e.printStackTrace();
			}

		}

		else {
			out.println("<script>alert('Page Unavailable');</script>");
			res.setHeader("Refresh",
					"0;URL=http://localhost:8080/OnlineTest/servlet/FirstPage");
		}

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String quesno = req.getParameter("quesno");
		String ques = req.getParameter("question");
		String op1 = req.getParameter("opa");
		String op2 = req.getParameter("opb");
		String op3 = req.getParameter("opc");
		String op4 = req.getParameter("opd");
		String correct = req.getParameter("ans");
		String exam = req.getParameter("exam");
		System.out.println("Question is : " + ques);
		PrintWriter out = res.getWriter();
		try {

			String query = "update "
					+ exam
					+ " set question=?,op1=?,op2=?,op3=?,op4=?,correct=? where ques_no=?";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, ques);
			ps.setString(2, op1);
			ps.setString(3, op2);
			ps.setString(4, op3);
			ps.setString(5, op4);
			ps.setString(6, correct);
			ps.setInt(7, Integer.parseInt(quesno));
			System.out.println(ps.toString());
			int i = ps.executeUpdate();

			if (i == 1) {
				out.println("<script>alert('Question successfully updated');</script>");
				res.setHeader("Refresh",
						"0;URL=http://localhost:8080/OnlineTest/servlet/ManageExam");
			} else {
				out.println("<script>alert('Error : Unable to update question');</script>");
				res.sendRedirect("AdminPanel");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
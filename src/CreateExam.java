import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateExam extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7297449192085279579L;
	String userid = null;
	Connection con = DBInfo.getConn();
	int cur_ques = 1;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String exam = null;
	int quesno = 0;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession(false);
		exam = req.getParameter("name");
		quesno = Integer.parseInt(req.getParameter("numq"));
		PrintWriter out = res.getWriter();

		if (session != null && session.getAttribute("adminid") != null) {

			try {
				String query = "select exam_name from exam";
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				int flag = 0;
				while (rs.next()) {
					if (rs.getString(1).equals(exam)) {
						flag = 1;
						break;
					}
				}
				if (flag == 1) {
					out.println("<script>alert('Exam already exists with name : "
							+ exam + " ');</script>");
					res.setHeader("Refresh",
							"0;URL=http://localhost:8080/OnlineTest/servlet/ManageExam");
				}

				query = "create table "
						+ exam
						+ " (ques_no int NOT NULL auto_increment,question varchar(300) NOT NULL,op1 varchar(150) NOT NULL,op2 varchar(150) NOT NULL,op3 varchar(150) NOT NULL,op4 varchar(150) NOT NULL,correct char(3) NOT NULL,PRIMARY KEY  (`ques_no`) )";
				ps = con.prepareStatement(query);
				int i = ps.executeUpdate();

				query = "insert into exam values(?,?,?,?)";
				ps = con.prepareStatement(query);
				ps.setString(1, null);
				ps.setString(2, exam);
				ps.setInt(3, quesno * 4);
				ps.setInt(4, quesno);
				i = ps.executeUpdate();

				if (i == 1) {
					out.println("<script>alert('Exam created with name : "
							+ exam + " successfully')</script>");
				}

			}

			catch (Exception e) {
				e.printStackTrace();
			}
			out.write("<!DOCTYPE html>\r\n");
			out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
			out.write("<head>\r\n");
			out.write("    <title>...::: RAT Online Academic Panel :::...</title>\r\n");
			out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
			out.write("    <link rel=\"stylesheet\" href=\"../styles/StyleSheet1.css\" type=\"text/css\"/>\r\n");
			out.write("\t<script type=\"text/javascript\">\r\n");
			out.write("\t\r\n");
			out.write("\tfunction final()\r\n");
			out.write("\t{\r\n");
			out.write("\t\t\r\n");
			out.write("\t\tif(document.my.ques.value == \"\")\r\n");
			out.write("\t\t{\r\n");
			out.write("\t\t\talert('Question cant be left blank');\r\n");
			out.write("\t\t\tdocument.my.ques.focus();\r\n");
			out.write("\t\t}\r\n");
			out.write("\t\telse if(document.my.opa.value == \"\")\r\n");
			out.write("\t\t{\r\n");
			out.write("\t\t\talert('Option (a) cant be left blank');\r\n");
			out.write("\t\t\tdocument.my.opa.focus();\r\n");
			out.write("\t\t\treturn false;\r\n");
			out.write("\t\t}\r\n");
			out.write("\t\telse if(document.my.opb.value == \"\")\r\n");
			out.write("\t\t{\t\r\n");
			out.write("\t\t\talert('Option (b) cant be left blank');\r\n");
			out.write("\t\t\tdocument.my.opb.focus();\r\n");
			out.write("\t\t\treturn false;\r\n");
			out.write("\t\t}\r\n");
			out.write("\t\telse if(document.my.opc.value == \"\")\r\n");
			out.write("\t\t{\r\n");
			out.write("\t\t\talert('Option (c) cant be left blank');\r\n");
			out.write("\t\t\tdocument.my.opc.focus();\r\n");
			out.write("\t\t\treturn false;\r\n");
			out.write("\t\t\t\r\n");
			out.write("\t\t}\r\n");
			out.write("\t\telse if(document.my.opd.value == \"\")\r\n");
			out.write("\t\t{\r\n");
			out.write("\t\t\talert('Option (d) cant be left blank');\r\n");
			out.write("\t\t\tdocument.my.opd.focus();\r\n");
			out.write("\t\t\treturn false;\r\n");
			out.write("\t\t}\r\n");
			out.write("\t\telse if(document.my.ans.value == \"\")\r\n");
			out.write("\t\t{\r\n");
			out.write("\t\t\talert('Correct Option cant be left blank');\r\n");
			out.write("\t\t\tdocument.my.ans.focus();\r\n");
			out.write("\t\t\treturn false;\r\n");
			out.write("\t\t}\r\n");
			out.write("\t\telse\r\n");
			out.write("\t\t\treturn true;\r\n");
			out.write("\t}\r\n");
			out.write("\t</script>\r\n");
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
			out.write("                <div id=\"top\" height=\"380\" style=\"font-family: Lucida Calligraphy; background-color: #336699; color:cyan\">\r\n");
			out.write("                    <img src=\"../images/rat.png\" height=\"100\" style=\"float:left\"><h1 align=\"right\">Road Ahead Technologies&nbsp;</h1><br /><h2 align=\"right\">Online Academic Panel&nbsp;&nbsp;</h2>\r\n");
			out.write("                </div>\r\n");
			out.write("                    <br/>\r\n");
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
			out.write("                            <form action=\"\" method=\"post\" name=\"my\" onsubmit=\"return final();\">\r\n");
			out.write("                                <center>\r\n");
			out.write("                                    <table >\r\n");
			out.write("                                       <tr>\r\n");
			out.write("                                         \r\n");
			out.write("                                           <td>&nbsp;&nbsp;&nbsp;Question No : </td>\r\n");
			out.write("                                           <td>\r\n");
			out.write("                                               <input type=text name=quesno value='"
					+ cur_ques + "' readonly/>\r\n");
			out.write("                                           </td>\r\n");
			out.write("\r\n");
			out.write("                                       </tr>\r\n");
			out.write("                                        <tr>\r\n");
			out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Question :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
			out.write("                                            <td><br /><textarea name=\"question\"></textarea></td>\r\n");
			out.write("                                        </tr>\r\n");
			out.write("                                        <tr>\r\n");
			out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;Option (a) :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
			out.write("                                            <td><br /><textarea name=\"opa\"></textarea></td>\r\n");
			out.write("                                        </tr>\r\n");
			out.write("                                        <tr>\r\n");
			out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;Option (b) :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
			out.write("                                            <td><br /><textarea name=\"opb\"></textarea></td>\r\n");
			out.write("                                        </tr>\r\n");
			out.write("                                        <tr>\r\n");
			out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;Option (c) :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
			out.write("                                            <td><br /><textarea name=\"opc\"></textarea></td>\r\n");
			out.write("                                        </tr>\r\n");
			out.write("                                        <tr>\r\n");
			out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;Option (d) :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
			out.write("                                            <td><br /><textarea name=\"opd\"></textarea></td>\r\n");
			out.write("                                        </tr>\r\n");
			out.write("                                        <tr>\r\n");
			out.write("                                            <td><br />Correct Answer :&nbsp;&nbsp;</td>\r\n");
			out.write("                                            <td><br /><input type=\"text\" name=\"ans\" value=\"\"/></td>\r\n");
			out.write("                                        </tr>\r\n");
			out.write("                                        <tr>\r\n");
			out.write("                                            <td ><br /><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"submit\" value=\" Add Question \"/></td>\r\n");
			out.write("\t\t\t\t\t\t\t\t\t\t\t<td><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;<input type=reset value= ' Reset '/></td>\r\n");
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
			out.write("\t\r\n");
			out.write("\t\r\n");
			out.write("\r\n");
			out.write("</body>\r\n");
			out.write("</html>\r\n");
		} else {
			out.println("<script>alert('Page Unavailable');</script>");
			res.setHeader("Refresh",
					"0;URL=http://localhost:8080/OnlineTest/servlet/FirstPage");
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		if (cur_ques > quesno) {
			res.sendRedirect("ManageExam");
		}
		String ques = req.getParameter("question");
		String op1 = req.getParameter("opa");
		String op2 = req.getParameter("opb");
		String op3 = req.getParameter("opc");
		String op4 = req.getParameter("opd");
		String correct = req.getParameter("ans");
		PrintWriter out = res.getWriter();
		String query = "insert into " + exam + " values (?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, null);
			ps.setString(2, ques);
			ps.setString(3, op1);
			ps.setString(4, op2);
			ps.setString(5, op3);
			ps.setString(6, op4);
			ps.setString(7, correct);

			int i = ps.executeUpdate();
			if (i == 1) {
				cur_ques++;
				out.println("<script>alert('Question successfully added');</script>");
			} else {
				out.println("<script>alert('Error : Question not added');</script>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		out.write("<!DOCTYPE html>\r\n");
		out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
		out.write("<head>\r\n");
		out.write("    <title>...::: RAT Online Academic Panel :::...</title>\r\n");
		out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
		out.write("    <link rel=\"stylesheet\" href=\"../styles/StyleSheet1.css\" type=\"text/css\"/>\r\n");
		out.write("\t<script type=\"text/javascript\">\r\n");
		out.write("\t\r\n");
		out.write("\tfunction final()\r\n");
		out.write("\t{\r\n");
		out.write("\t\t\r\n");
		out.write("\t\tif(document.my.ques.value == \"\")\r\n");
		out.write("\t\t{\r\n");
		out.write("\t\t\talert('Question cant be left blank');\r\n");
		out.write("\t\t\tdocument.my.ques.focus();\r\n");
		out.write("\t\t}\r\n");
		out.write("\t\telse if(document.my.opa.value == \"\")\r\n");
		out.write("\t\t{\r\n");
		out.write("\t\t\talert('Option (a) cant be left blank');\r\n");
		out.write("\t\t\tdocument.my.opa.focus();\r\n");
		out.write("\t\t\treturn false;\r\n");
		out.write("\t\t}\r\n");
		out.write("\t\telse if(document.my.opb.value == \"\")\r\n");
		out.write("\t\t{\t\r\n");
		out.write("\t\t\talert('Option (b) cant be left blank');\r\n");
		out.write("\t\t\tdocument.my.opb.focus();\r\n");
		out.write("\t\t\treturn false;\r\n");
		out.write("\t\t}\r\n");
		out.write("\t\telse if(document.my.opc.value == \"\")\r\n");
		out.write("\t\t{\r\n");
		out.write("\t\t\talert('Option (c) cant be left blank');\r\n");
		out.write("\t\t\tdocument.my.opc.focus();\r\n");
		out.write("\t\t\treturn false;\r\n");
		out.write("\t\t\t\r\n");
		out.write("\t\t}\r\n");
		out.write("\t\telse if(document.my.opd.value == \"\")\r\n");
		out.write("\t\t{\r\n");
		out.write("\t\t\talert('Option (d) cant be left blank');\r\n");
		out.write("\t\t\tdocument.my.opd.focus();\r\n");
		out.write("\t\t\treturn false;\r\n");
		out.write("\t\t}\r\n");
		out.write("\t\telse if(document.my.ans.value == \"\")\r\n");
		out.write("\t\t{\r\n");
		out.write("\t\t\talert('Correct Option cant be left blank');\r\n");
		out.write("\t\t\tdocument.my.ans.focus();\r\n");
		out.write("\t\t\treturn false;\r\n");
		out.write("\t\t}\r\n");
		out.write("\t\telse\r\n");
		out.write("\t\t\treturn true;\r\n");
		out.write("\t}\r\n");
		out.write("\t</script>\r\n");
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
		out.write("                <div id=\"top\" height=\"380\" style=\"font-family: Lucida Calligraphy; background-color: #336699; color:cyan\">\r\n");
		out.write("                    <img src=\"../images/rat.png\" height=\"100\" style=\"float:left\"><h1 align=\"right\">Road Ahead Technologies&nbsp;</h1><br /><h2 align=\"right\">Online Academic Panel&nbsp;&nbsp;</h2>\r\n");
		out.write("                </div>\r\n");
		out.write("                    <br/>\r\n");
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
		out.write("                            <form action=\"\" method=\"post\" name=\"my\" onsubmit=\"return final();\">\r\n");
		out.write("                                <center>\r\n");
		out.write("                                    <table >\r\n");
		out.write("                                       <tr>\r\n");
		out.write("                                         \r\n");
		out.write("                                           <td>&nbsp;&nbsp;&nbsp;Question No : </td>\r\n");
		out.write("                                           <td>\r\n");
		out.write("                                               <input type=text name=quesno value='"
				+ cur_ques + "' readonly/>\r\n");
		out.write("                                           </td>\r\n");
		out.write("\r\n");
		out.write("                                       </tr>\r\n");
		out.write("                                        <tr>\r\n");
		out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Question :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
		out.write("                                            <td><br /><textarea name=\"question\"></textarea></td>\r\n");
		out.write("                                        </tr>\r\n");
		out.write("                                        <tr>\r\n");
		out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;Option (a) :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
		out.write("                                            <td><br /><textarea name=\"opa\"></textarea></td>\r\n");
		out.write("                                        </tr>\r\n");
		out.write("                                        <tr>\r\n");
		out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;Option (b) :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
		out.write("                                            <td><br /><textarea name=\"opb\"></textarea></td>\r\n");
		out.write("                                        </tr>\r\n");
		out.write("                                        <tr>\r\n");
		out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;Option (c) :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
		out.write("                                            <td><br /><textarea name=\"opc\"></textarea></td>\r\n");
		out.write("                                        </tr>\r\n");
		out.write("                                        <tr>\r\n");
		out.write("                                            <td><br />&nbsp;&nbsp;&nbsp;&nbsp;Option (d) :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
		out.write("                                            <td><br /><textarea name=\"opd\"></textarea></td>\r\n");
		out.write("                                        </tr>\r\n");
		out.write("                                        <tr>\r\n");
		out.write("                                            <td><br />Correct Answer :&nbsp;&nbsp;</td>\r\n");
		out.write("                                            <td><br /><input type=\"text\" name=\"ans\" value=\"\"/></td>\r\n");
		out.write("                                        </tr>\r\n");
		out.write("                                        <tr>\r\n");
		out.write("                                            <td ><br /><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"submit\" value=\" Add Question \"/></td>\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\t<td><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;<input type=reset value= ' Reset '/></td>\r\n");
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
		out.write("\t\r\n");
		out.write("\t\r\n");
		out.write("\r\n");
		out.write("</body>\r\n");
		out.write("</html>\r\n");
	}

}
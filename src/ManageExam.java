import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManageExam extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2174370653296226932L;
	String userid = null;
	int index = 0;
	Connection con = DBInfo.getConn();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		HttpSession session = req.getSession(false);

		PrintWriter out = res.getWriter();
		if (session != null && session.getAttribute("adminid") != null) {
			userid = (String) session.getAttribute("adminid");
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
			out.write("</head>\r\n");
			out.write("<body>\r\n");
			out.write("    \r\n");
			out.write("    <table style=\"background:url(../images/bg.jpg) no-repeat\">\r\n");
			out.write("        <tr>\r\n");
			out.write("            <td><div id=\"left\"></div></td>\r\n");
			out.write("            <td>\r\n");
			out.write("                <div id=\"center\">\r\n");
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
			out.write("                    <br />\r\n");
			out.write("                   \r\n");
			out.write("                    <div style=\"text-align:center;height:200px;\" id=\"edit\" >\r\n");
			out.write("                        <u><h2 style=\"font-family:Consolas\">Existing Exams</h2></u>\r\n");
			out.write("                        <br/>\r\n");
			out.write("                        <br />\r\n");
			out.write("                        <form name=edit action=\"\" method=\"post\" onsubmit='return checkEdit();' >\r\n");
			out.write("                            <table width=\"950\">\r\n");
			out.write("                                <tr>\r\n");

			String q = "select * from exam";
			try {
				PreparedStatement ps = con.prepareStatement(q);
				ResultSet rs = ps.executeQuery();
				String exam_name = null;
				while (rs.next()) {
					exam_name = rs.getString("exam_name");
					out.write("<td><input type=\"radio\" name=\"exam\" value=\""
							+ exam_name
							+ "\" />&nbsp;&nbsp;"
							+ exam_name.toUpperCase() + "</td>\r\n");
				}

				out.write("                                </tr>\r\n");
				out.write("                                <tr>\r\n");
				out.write("                                    <td colspan=\"2\"><br /><input type=\"submit\" value=\" Edit Exam \"  /></td>\r\n");
				out.write("                                    <td colspan=\"2\"><br /><input type=\"reset\" value=\" Reset \"/></td>\r\n");
				out.write("                                </tr>\r\n");
				out.write("                            </table>\r\n");
				out.write("                        </form>\r\n");
				out.write("                    </div>\r\n");
				out.write("                       <br />\r\n");
				out.write("                    <br />\r\n");
				out.write("                    <hr />\r\n");
				out.write("                    <br />\r\n");
				out.write("                    <br />\r\n");
				out.write("                    <center>\r\n");
				out.write("                        <div id=\"new\" style=\"font-family:Consolas;height:210px;\">\r\n");
				out.write("                           <u> <h2>Create New Exam</h2></u>\r\n");
				out.write("                            <br />\r\n");
				out.write("                            <br />\r\n");
				out.write("                            <form action=\"CreateExam\" name=create method=\"get\" onsubmit='return checkCreate();' >\r\n");
				out.write("                                <table>\r\n");
				out.write("                                    <tr>\r\n");
				out.write("                                        <td><label>Enter Name Of Subject : </label></td>\r\n");
				out.write("                                        <td><input type=\"text\" name=\"name\" /></td>\r\n");
				out.write("                                    </tr>\r\n");
				out.write("                                    <tr>\r\n");
				out.write("                                        <td><br /><label>Enter Number of Questions : </label></td>\r\n");
				out.write("                                        <td><br /><input type=\"number\" name=\"numq\" min=\"5\" max=\"50\" step='5'/></td>\r\n");
				out.write("                                    </tr>\r\n");
				out.write("                                    <tr>\r\n");
				out.write("                                        <td><br /><input type=\"submit\" value=\" Add Exam \"/></td>\r\n");
				out.write("                                        <td><br /><input type=\"reset\" value=\" Reset \" /></td>\r\n");
				out.write("                                    </tr>\r\n");
				out.write("                                </table>\r\n");
				out.write("                            </form>\r\n");
				out.write("\r\n");
				out.write("\r\n");
				out.write("                        </div>\r\n");
				out.write("                    </center>\r\n");
				out.write("                    <br />\r\n");
				out.write("                   \r\n");
				out.write("                   <br />\r\n");
				out.write("                    <br />\r\n");
				out.write("                \r\n");
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
				out.println("<script>");
				out.println("function checkEdit() {");
				out.println("if(document.edit.exam.checked == false) { alert('Please select an exam to continue !');return false; }");
				out.println("else { return true;}   }");
				out.println("function checkCreate() {");
				out.println("if(document.create.name.value == '' || document.create.numq.value == '' ) { alert('Please fill all the inputs !');return false; }");
				out.println("else { return true; }");
				out.println("}");
				out.println("</script>");
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
		String exam = req.getParameter("exam");
		String query = "select * from " + exam + "";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			PrintWriter out = res.getWriter();
			Vector<Question> ques = new Vector<Question>();
			while (rs.next()) {
				ques.add(new Question(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getString(4), rs.getString(5), rs
						.getString(6), rs.getString(7)));

			}

			int size = ques.size();
			int tot_page = (int) Math.ceil(size / 10.0);
			String next = req.getParameter("next");
			String prev = req.getParameter("prev");
			String curpage = req.getParameter("curpage");

			if (curpage != null) {
				int curpagei = Integer.parseInt(curpage);

				if (next != null) {
					if (curpagei < tot_page) {
						index = index + 1;
					}

				}

				if (prev != null) {
					if (curpagei > 1) {
						index = index - 1;
					}
				}

			}

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
			out.write("                        <div id=\"ques\" style=\"font-family:Consolas;height:490px;\">\r\n");
			out.write("                            <h2><u>Question Details</u></h2>\r\n");
			out.write("                            <br />\r\n");
			out.write("                            <br />\r\n");
			out.write("                            <form action=\"ManageExam\" method=\"post\" name=\"my\" >\r\n");
			out.println("<input type='hidden' value='" + exam
					+ "' name='exam' />");
			out.write("                                <center>\r\n");
			out.write("                                    <table width=\"950\">\r\n");
			out.write("                                        <tr>\r\n");
			out.write("                                            <td>\r\n");
			out.write("                                               <input type=\"submit\" name=\"prev\" value=\" <<< PREV \" />\r\n");
			out.write("                                            </td>\r\n");

			out.write("                                            <td align=\"center\">\r\n");
			out.write("                                                <input type=\"text\" name=\"curpage\" value = '"
					+ String.valueOf(index + 1) + "'readonly />&nbsp;\r\n");
			out.write("                                                <label>OF</label>&nbsp;<input type=\"text\" name=\"totpage\" value = '"
					+ String.valueOf(tot_page) + "' readonly />\r\n");
			out.write("                                            </td>\r\n");
			out.write("                                            <td>\r\n");
			out.write("                                                <input type=\"submit\" name=\"next\" value=\" NEXT >>> \" />\r\n");
			out.write("                                            </td>\r\n");
			out.write("                                        </tr>\r\n");
			out.write("                                    </table>\r\n");
			out.write("                                    </center>\r\n");
			out.write("                                <br />\r\n");
			out.write("                                <br />\r\n");
			out.write("\r\n");
			out.write("\r\n");
			out.write("\r\n");
			out.write("                               \r\n");
			out.write("                            <table>\r\n");
			out.write("                                <tr>\r\n");
			out.write("                                 \r\n");
			out.write("                                    <th>Ques No</th>\r\n");
			out.write("                                    <th>Question </th>\r\n");
			out.write("                                    <th>Option (a)</th>\r\n");
			out.write("                                    <th>Option (b)</th>\r\n");
			out.write("                                    <th>Option (c)</th>\r\n");
			out.write("                                    <th>Option (d)</th>\r\n");
			out.write("                                    <th>Correct Answer</th>\r\n");
			out.write("                                    <th>Option</th>\r\n");
			out.write("                                </tr>\r\n");

			int ind = 10 * index;
			for (int j = 0; j < 10 && ind < size; j++) {
				Question u = ques.get(ind);
				out.println("<tr><td><br/><label>Q. " + u.getQuesno()
						+ "</label></td>");
				out.println("<td><br /><input type=\"text\"  value='"
						+ u.getQuestion() + "'\" /></td>");
				out.println("<td><br /><input type=\"text\" value='"
						+ u.getOp1() + "' /></td>");
				out.println("<td><br /><input type=\"text\" value='"
						+ u.getOp2() + "' /></td>");
				out.println("<td><br /><input type=\"text\" value='"
						+ u.getOp3() + "' /></td>");
				out.println("<td><br /><input type=\"text\" value='"
						+ u.getOp4() + "' /></td>");
				out.println("<td><br /><input type=\"text\" value='"
						+ u.getCorrect() + "' /></td>");
				out.println("<td><br/><a href='http://localhost:8080/OnlineTest/servlet/EditQuestion?exam="
						+ exam
						+ "&ques="
						+ (u.getQuesno())
						+ "' >Edit Question</a></td></tr>");

				ind++;
			}

			out.write("                            </table>\r\n");
			out.write("                         </form>\r\n");
			out.write("                        </div>\r\n");
			out.write("                   \r\n");
			out.write("                      \r\n");
			out.write("                    \r\n");
			out.write("                    \r\n");
			out.write("                   <br />\r\n");
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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
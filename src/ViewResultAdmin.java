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

public class ViewResultAdmin extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7975001129896208566L;
	String userid = null;
	int index = 0;

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
			out.write("                    <br />\r\n");
			out.write("                    <form action=\"\" method=\"get\">\r\n");
			out.write("                    <div style=\"text-align:center\">\r\n");
			out.write("                        <table  width=\"950\">\r\n");
			out.write("                            <tr>\r\n");
			out.write("                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
			out.write("                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
			out.write("<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
			out.write("                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=\"submit\" name=\"prev\" value=\" <<< PREV \"/>\r\n");
			out.write("                                </td>\r\n");
			out.write("                                <td align=\"center\">\r\n");
			out.write("                                \r\n");

			try {

				Connection con = DBInfo.getConn();
				String query = "select * from result";
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				Vector<Result> results = new Vector<Result>();

				while (rs.next()) {
					results.add(new Result(rs.getString(1), rs.getDate(2), rs
							.getString(3), rs.getInt(6), rs.getString(7)));
				}
				int size = results.size();
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

				out.write("                                        <input type=\"text\" name=\"curpage\" readonly value='"
						+ String.valueOf(index + 1) + "'/>&nbsp;\r\n");
				out.write("                                        <label>OF</label>&nbsp;<input type=\"text\" name=\"totpage\" value='"
						+ String.valueOf(tot_page) + "' readonly />\r\n");
				out.write("                                    \r\n");
				out.write("                                </td>\r\n");
				out.write("                                <td >\r\n");
				out.write("                                    <input type=\"submit\" name=\"next\" value=\" NEXT >>> \" />\r\n");
				out.write("                                </td>\r\n");
				out.write("                            </tr>\r\n");
				out.write("                        </table>\r\n");
				out.write("                        \r\n");
				out.write("               \r\n");
				out.write("                        \r\n");
				out.write("                        \r\n");
				out.write("                    </div>\r\n");
				out.write("                        </form>\r\n");
				out.write("                    <center>\r\n");
				out.write("                    <div id=\"content\" style='height:500px;'>\r\n");
				out.write("                        <br />\r\n");
				out.write("                        \r\n");
				out.write("                        <table width=\"1000\" style='font-size:23px;' >\r\n");
				out.write("                            <tr>\r\n");
				out.write("                                <th>Exam - Name</th>\r\n");
				out.write("                                <th>Exam - Date</th>\r\n");
				out.write("                                <th>User - ID</th>\r\n");
				out.write("                                <th>Marks</th>\r\n");
				out.write("                                <th>Result</th>\r\n");
				out.write("                                <th>Option</th>\r\n");
				out.write("\r\n");
				out.write("                            </tr>\r\n");

				out.println("<tr><td>&nbsp;</td>");
				out.println("<td>&nbsp</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td></tr>");

				int ind = 10 * index;
				for (int j = 0; j < 10 && ind < size; j++) {
					Result u = results.get(ind);
					out.println("<tr><td>" + u.getExamName() + "</td>");
					out.println("<td>" + u.getExamDate() + "</td>");
					out.println("<td>" + u.getUserid() + "</td>");
					out.println("<td>" + u.getMarks() + "</td>");
					out.println("<td>" + u.getResult() + "</td>");
					out.println("<td><a href='http://localhost:8080/OnlineTest/servlet/ResultDetailAdmin?exam="
							+ u.getExamName()
							+ "&id="
							+ AESCrypto.encrypt(u.getUserid())
							+ "' >View Details</a></td></tr>");

					ind++;
				}

				out.write("\r\n");
				out.write("                        </table>\r\n");
				out.write("                     \r\n");
				out.write("\r\n");
				out.write("                    </div>\r\n");
				out.write("                    </center>\r\n");
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
				out.write("                        <td><a href=\"AboutUs\">About Us</a></td>\r\n");
				out.write("                        <td><a href=\"ContactUs\">Contact Us</a></td>\r\n");
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
				out.write("\r\n");
				out.write("</body>\r\n");
				out.write("</html>\r\n");
			}

			catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			out.println("<script>alert('You are not authorized to access this page');</script>");
			res.setHeader("Refresh",
					"0;URL=http://localhost:8080/OnlineTest/servlet/FirstPage");
		}

	}

}

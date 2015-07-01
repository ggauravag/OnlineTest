import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLogin extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9007016895001025995L;
	String uname = null;
	String pass = null;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		uname = req.getParameter("uname");
		pass = req.getParameter("pass");
		String op = req.getParameter("remember");
		PrintWriter out = res.getWriter();
		String query = "select * from login where username=? AND password=?";
		try {
			Connection con = DBInfo.getConn();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, uname);
			ps.setString(2, AESCrypto.encrypt(pass));

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Username/password match");
				if (rs.getString(4).equals("Y")) {
					System.out.println("user is verified");
					HttpSession session = req.getSession(true);

					if (rs.getString(5).equals("N")
							&& rs.getDate("last_login") == null) {
						System.out.println("First Time login user");
						session.setAttribute("userid", rs.getString(6));
						if (op != null && op.equals("y")) {
							Cookie cookie = new Cookie("userid",
									AESCrypto.encrypt(rs.getString(6)));
							cookie.setMaxAge(3 * 24 * 60 * 60);
							res.addCookie(cookie);
							System.out.println("Cookie Added Successfully");
						}
						res.sendRedirect("ChangePassword");
					}

					else {
						if (op != null && op.equals("y")) {
							Cookie cookie = new Cookie("userid",
									AESCrypto.encrypt(rs.getString(6)));
							cookie.setMaxAge(3 * 24 * 60 * 60);
							res.addCookie(cookie);
							System.out.println("Cookie Added Successfully");
						}

						if (rs.getString(3).equals("admin")) {
							RequestDispatcher rd = req
									.getRequestDispatcher("AdminPanel");
							System.out.println("User is admin");
							session.setAttribute("adminid", rs.getString(6));
							rd.forward(req, res);
						}

						if (rs.getString(3).equals("student")) {
							System.out.println("User is student");
							RequestDispatcher rd = req
									.getRequestDispatcher("UserPanel");

							session.setAttribute("userid", rs.getString(6));
							session.setMaxInactiveInterval(60 * 10);
							rd.forward(req, res);
						}

					}
				} else {
					System.out.println("user is not verified "
							+ rs.getString(4));
					out.write("<!DOCTYPE html>\r\n");
					out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
					out.write("<head>\r\n");
					out.write("    <title>...::: RAT Online Academic Panel :::...</title>\r\n");
					out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
					out.write("    <style type=\"text/css\">\r\n");
					out.write("        #top {\r\n");
					out.write("            margin-top: -31px;\r\n");
					out.write("        }\r\n");
					out.write("\r\n");
					out.write("        #left {\r\n");
					out.write("            width: 150px;\r\n");
					out.write("            background-color: #666666;\r\n");
					out.write("            background-color: darkgrey;\r\n");
					out.write("            margin-left: -10px;\r\n");
					out.write("            margin-bottom: -10px;\r\n");
					out.write("            margin-top: -10px;\r\n");
					out.write("            margin-right: -3px;\r\n");
					out.write("            height: 1010px;\r\n");
					out.write("        }\r\n");
					out.write("\r\n");
					out.write("        #right {\r\n");
					out.write("            width: 150px;\r\n");
					out.write("            background-color: darkgrey;\r\n");
					out.write("            margin-right: -10px;\r\n");
					out.write("            margin-bottom: -10px;\r\n");
					out.write("            margin-top: -10px;\r\n");
					out.write("            margin-left: -3px;\r\n");
					out.write("            height: 1010px;\r\n");
					out.write("        }\r\n");
					out.write("\r\n");
					out.write("        #mar {\r\n");
					out.write("            margin-top: -20px;\r\n");
					out.write("        }\r\n");
					out.write("\r\n");
					out.write("        #info {\r\n");
					out.write("            width: 460px;\r\n");
					out.write("            font-family: Consolas;\r\n");
					out.write("            margin-left: -5px;\r\n");
					out.write("        }\r\n");
					out.write("\r\n");
					out.write("        #bot {\r\n");
					out.write("            height: 90px;\r\n");
					out.write("            text-align: center;\r\n");
					out.write("            font-family: Verdana;\r\n");
					out.write("            color: white;\r\n");
					out.write("            margin-bottom: -10px;\r\n");
					out.write("        }\r\n");
					out.write("    </style>\r\n");
					out.write("</head>\r\n");
					out.write("<body>\r\n");
					out.write("    \r\n");
					out.write("    <table style=\"background:url(../images/bg.jpg) no-repeat\">\r\n");
					out.write("        <tr>\r\n");
					out.write("            <td><div id=\"left\"></div></td>\r\n");
					out.write("            <td>\r\n");
					out.write("                <div id=\"top\" height=\"250\" style=\"font-family: Lucida Calligraphy; background-color: #336699; color:cyan\">\r\n");
					out.write("                    <img src=\"../images/rat.png\" height=\"100\" style=\"float:left\"><h1 align=\"right\">Road Ahead Technologies&nbsp;</h1><h2 align=\"right\">Online Academic Panel&nbsp;&nbsp;</h2>\r\n");
					out.write("                </div>\r\n");
					out.write("                \r\n");
					out.write("                    <div id=\"mar\">\r\n");
					out.write("                        <marquee style=\"background-color:#FFCC00;color:#000000;font:\" times new roman\", times, serif\" onmouseover=\"this.stop()\" onmouseout=\"this.start();\" direction=\"left\" home.html\" onsubmit=\"return v()\">Road Ahead Technologies is structured around \"center of  excellence\" - our practice areas operate as a coordinated whole:  separate, yet capable of providing integrated services for our  student's benefit. This structure enables us to quickly mobilize the right resources to implement the strategies we develop - whenever and wherever students need.</marquee>\r\n");
					out.write("                    </div>\r\n");
					out.write("                    <br />\r\n");
					out.write("\r\n");
					out.write("                    \r\n");
					out.write("                    <center>\r\n");
					out.write("                        <div style='border:5px dotted red;height:400px;width:710px;margin-left:-25px;font-family:Consolas'>\r\n");
					out.write("                            <h1 style=\" text-align:center\"><img src=\"../images/error1.jpg\" />&nbsp;Login Unsuccessful</h1>\r\n");
					out.write("                            <hr>\r\n");
					out.write("                            <b>\r\n");
					out.write("                              <h3>  Your account is not verified. Hence all your login privileges remain suspended.</h3>\r\n");
					out.write("                            </b>\r\n");
					out.write("                            <h3>What next ?</br></br>\r\n");
					out.write("                            &nbsp;Contact System Admin ( admrat2014@gmail.com ) by mentioning your user name & E-Mail Id.<br />\r\n");
					out.write("                            &nbsp;&nbsp;Following which you will receive an e-mail upon successful verification/authentication of your details by Admin<br /><br />\r\n");
					out.write("                                &nbsp;&nbsp;After that you would be able to login into your account and attempt the Online Exam.<br /><br />\r\n");
					out.write("\r\n");
					out.write("                            </h3>\r\n");
					out.write("                            <br />\r\n");
					out.write("                            <br />\r\n");
					out.write("                          \r\n");
					out.write("                        </div>\r\n");
					out.write("                    </center>\r\n");
					out.write("                    <br />\r\n");
					out.write("                   \r\n");
					out.write("                    <table align=\"center\">\r\n");
					out.write("                        <tr>\r\n");
					out.write("                            <br />\r\n");
					out.write("                            <br />\r\n");
					out.write("                            <br />\r\n");
					out.write("                            <td><img src=\"../images/s1.jpg\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
					out.write("\r\n");
					out.write("                            <td><img src=\"../images/s2.jpg\" /></td>\r\n");
					out.write("                        </tr>\r\n");
					out.write("\r\n");
					out.write("\r\n");
					out.write("\r\n");
					out.write("\r\n");
					out.write("\r\n");
					out.write("                    </table>\r\n");
					out.write("                \r\n");
					out.write("                <br />\r\n");
					out.write("                <br />\r\n");
					out.write("                <table align=center width=700px style=\"font-size:20px;\">\r\n");
					out.write("                    <tr>\r\n");
					out.write("                        <td><a href=\"FirstPage\">Login</a></td>\r\n");
					out.write("                        <td><a href=\"UserReg\">Student Registration</a></td>\r\n");
					out.write("                        <td><a href=\"AboutUs\">About Us</a></td>\r\n");
					out.write("                        <td><a href=\"ContactUs\">Contact Us</a></td>\r\n");
					out.write("\r\n");
					out.write("                    </tr>\r\n");
					out.write("                </table>\r\n");
					out.write("                <div id=\"bot\" style=\"background-color: #336699; \">\r\n");
					out.write("                    <br />\r\n");
					out.write("                    Copyright @ RAT 2014 </br><br />Developed By <a href=\"\">Gaurav Agarwal</a>\r\n");
					out.write("                </div>\r\n");
					out.write("            </td>\r\n");
					out.write("            <td><div id=\"right\"></div></td>\r\n");
					out.write("        </tr>\r\n");
					out.write("\r\n");
					out.write("    </table>\r\n");
					out.write("\r\n");
					out.write("\r\n");

				}

			} else {
				System.out.println("username/password illegal");
				out.println("<script>alert('Username/password doesnt match');</script>");
				// res.sendRedirect("FirstPage");
				res.setHeader("Refresh",
						"0; URL=http://localhost:8080/OnlineTest/servlet/FirstPage");

			}
			out.write("</body>\r\n");
			out.write("</html>\r\n");
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		PrintWriter out = res.getWriter();
		out.write("<!DOCTYPE html>\r\n");
		out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
		out.write("<head>\r\n");
		out.write("    <title>...::: RAT Online Academic Panel :::...</title>\r\n");
		out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
		out.write("    <style type=\"text/css\">\r\n");
		out.write("        #top {\r\n");
		out.write("            margin-top: -31px;\r\n");
		out.write("        }\r\n");
		out.write("\r\n");
		out.write("        #left {\r\n");
		out.write("            width: 150px;\r\n");
		out.write("            background-color: #666666;\r\n");
		out.write("            background-color: darkgrey;\r\n");
		out.write("            margin-left: -10px;\r\n");
		out.write("            margin-bottom: -10px;\r\n");
		out.write("            margin-top: -10px;\r\n");
		out.write("            margin-right: -3px;\r\n");
		out.write("            height: 970px;\r\n");
		out.write("        }\r\n");
		out.write("\r\n");
		out.write("        #right {\r\n");
		out.write("            width: 150px;\r\n");
		out.write("            background-color: darkgrey;\r\n");
		out.write("            margin-right: -10px;\r\n");
		out.write("            margin-bottom: -10px;\r\n");
		out.write("            margin-top: -10px;\r\n");
		out.write("            margin-left: -3px;\r\n");
		out.write("            height: 970px;\r\n");
		out.write("        }\r\n");
		out.write("\r\n");
		out.write("        #mar {\r\n");
		out.write("            margin-top: -20px;\r\n");
		out.write("        }\r\n");
		out.write("\r\n");
		out.write("        #info {\r\n");
		out.write("           \r\n");
		out.write("            font-family: Consolas;\r\n");
		out.write("            color:red;\r\n");
		out.write("        }\r\n");
		out.write("\r\n");
		out.write("        #bot {\r\n");
		out.write("            height: 90px;\r\n");
		out.write("            text-align: center;\r\n");
		out.write("            font-family: Verdana;\r\n");
		out.write("            color: white;\r\n");
		out.write("            margin-bottom: -10px;\r\n");
		out.write("        }\r\n");
		out.write("    </style>\r\n");
		out.write("</head>\r\n");
		out.write("<body>\r\n");
		out.write("    <script type=\"text/javascript\">\r\n");
		out.write("\r\n");
		out.write("       \r\n");
		out.write("\r\n");
		out.write("    </script>\r\n");
		out.write("    <table style=\"background:url(../images/bg.jpg) no-repeat\">\r\n");
		out.write("        <tr>\r\n");
		out.write("            <td><div id=\"left\"></div></td>\r\n");
		out.write("            <td>\r\n");
		out.write("                <div id=\"top\" height=\"250\" style=\"font-family: Lucida Calligraphy; background-color: #336699; color:cyan\">\r\n");
		out.write("                    <img src=\"../images/rat.png\" height=\"100\" style=\"float:left\"><h1 align=\"right\">Road Ahead Technologies&nbsp;</h1><h2 align=\"right\">Online Academic Panel&nbsp;&nbsp;</h2>\r\n");
		out.write("                </div>\r\n");
		out.write("                <form name=\"myForm\" action=\"CheckReg\" onsubmit=\"return validateForm();\" method=\"post\">\r\n");
		out.write("                    <div id=\"mar\">\r\n");
		out.write("                        <marquee style=\"background-color:#FFCC00;color:#000000;font:\" times new roman\", times, serif\" onmouseover=\"this.stop()\" onmouseout=\"this.start();\" direction=\"left\" home.html\" onsubmit=\"return v()\">Road Ahead Technologies is structured around \"center of  excellence\" - our practice areas operate as a coordinated whole:  separate, yet capable of providing integrated services for our  student's benefit. This structure enables us to quickly mobilize the right resources to implement the strategies we develop - whenever and wherever students need.</marquee>\r\n");
		out.write("                    </div>\r\n");
		out.write("                    <br />\r\n");
		out.write("                    <div id=\"info\">\r\n");
		out.write("                    <h1 style=\"font-family: Lucida Calligraphy; text-align:center\">Page Unavailable</h1>\r\n");
		out.write("                    <table style=\"height:200px;\" >\r\n");
		out.write("\r\n");
		out.write("                        <h2 align=center>\r\n");
		out.write("                            This page is accessed through unrecognized way. <br />Hence the page is not available<br>\r\n");
		out.write("                            Please try through authorized means.\r\n");
		out.write("                        </h2>\r\n");
		out.write("\r\n");
		out.write("                    </table>\r\n");
		out.write("                        </div>\r\n");
		out.write("                    <table align=\"center\">\r\n");
		out.write("                        <tr>\r\n");
		out.write("                            <br />\r\n");
		out.write("                            <br />\r\n");
		out.write("                            <br />\r\n");
		out.write("                            <td><img src=\"../images/s1.jpg\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
		out.write("\r\n");
		out.write("                            <td><img src=\"../images/s2.jpg\" /></td>\r\n");
		out.write("                        </tr>\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("                    </table>\r\n");
		out.write("                </form>\r\n");
		out.write("                <br />\r\n");
		out.write("                <br />\r\n");
		out.write("                <table align=center width=700px style=\"font-size:20px;\">\r\n");
		out.write("                    <tr>\r\n");
		out.write("                        <td><a href=\"FirstPage\">Login</a></td>\r\n");
		out.write("                        <td><a href=\"UserReg\">Student Registration</a></td>\r\n");
		out.write("                        <td><a href=\"AboutUs\">About Us</a></td>\r\n");
		out.write("                        <td><a href=\"ContactUs\">Contact Us</a></td>\r\n");
		out.write("\r\n");
		out.write("                    </tr>\r\n");
		out.write("                </table>\r\n");
		out.write("                <div id=\"bot\" style=\"background-color: #336699; \">\r\n");
		out.write("                    <br />\r\n");
		out.write("                    Copyright @ RAT 2014 </br><br />Developed By <a href=\"\">Gaurav Agarwal</a>\r\n");
		out.write("                </div>\r\n");
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
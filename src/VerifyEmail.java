import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyEmail extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1707810217713594357L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		PrintWriter out = res.getWriter();
		String email = null;
		if (req.getParameter("email") != null) {
			// System.out.println("Email parameter is not null");
			email = AESCrypto.decrypt(req.getParameter("email").replace(' ',
					'+'));
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
			out.write("                <form name=\"myForm\" action=\"servlet/CheckReg\" onsubmit=\"return validateForm();\" method=\"post\">\r\n");
			out.write("                    <div id=\"mar\">\r\n");
			out.write("                        <marquee style=\"background-color:#FFCC00;color:#000000;font:\" times new roman\", times, serif\" onmouseover=\"this.stop()\" onmouseout=\"this.start();\" direction=\"left\" home.html\" onsubmit=\"return v()\">Road Ahead Technologies is structured around \"center of  excellence\" - our practice areas operate as a coordinated whole:  separate, yet capable of providing integrated services for our  student's benefit. This structure enables us to quickly mobilize the right resources to implement the strategies we develop - whenever and wherever students need.</marquee>\r\n");
			out.write("                    </div>\r\n");
			out.write("                    <br />\r\n");
			out.write("\r\n");
			out.write("                    \r\n");
			out.write("                    <center>\r\n");

			try {
				String query = "update user set status_user='Y' where email=? AND status_user='N'";
				String query1 = "select * from user where email=?";
				Connection con = DBInfo.getConn();
				PreparedStatement ps = con.prepareStatement(query);
				PreparedStatement ps1 = con.prepareStatement(query1);
				ps.setString(1, email);
				ps1.setString(1, email);
				ResultSet rs = ps1.executeQuery();
				if (rs.next()) {
					rs.getString(3);
					// System.out.println("Email Id is registered");
					int i = ps.executeUpdate();
					if (i == 1) {
						// System.out.println("Email id is not verified");
						String response = RegSMS.send(rs.getString("userid"));
						if (response != null
								&& response == "Failed#Invalid Login") {
							out.println("<script>alert(\"Unable to send SMS.\n\n Please contact Gaurav for authentication.\");</script>");
						}
						out.write("                        <div style='border:5px dotted green;height:400px;width:710px;margin-left:-25px;font-family:Consolas'>\r\n");
						out.write("                            <h1 style=\" text-align:center\"><img src=\"../images/success.jpg\" />&nbsp;Verification Success</h1>\r\n");
						out.write("                            <hr>\r\n");
						out.write("                            <b>\r\n");
						out.write("                              <h3>  Your E-Mail Id has been successfully verified.</h3>\r\n");
						out.write("                            </b>\r\n");
						out.write("                            <h3>What next ?</br></br>\r\n");
						out.write("\r\n");
						out.write("                            &nbsp;&nbsp;You will receive an e-mail within 24 hrs upon successful verification/authentication of your details by Admin<br /><br />\r\n");
						out.write("                                &nbsp;&nbsp;After that you will be able to login into your account and attempt the Online Exam.<br /><br />\r\n");
						out.write("\r\n");
						out.write("                            </h3>\r\n");
						out.write("                            <br />\r\n");
						out.write("                            <br />\r\n");
						out.write("                          \r\n");
						out.write("                        </div>\r\n");

					}

					else {
						// System.out.println("Email parameter is already verified");
						out.write("                        <div style='border:5px dotted green;height:400px;width:710px;margin-left:-25px;font-family:Consolas'>\r\n");
						out.write("                            <h1 style=\" text-align:center\"><img src=\"../images/success.jpg\" />&nbsp;Verification Already Successful</h1>\r\n");
						out.write("                            <hr>\r\n");
						out.write("                            <b>\r\n");
						out.write("                              <h3>  Your E-Mail Id has already been successfully verified.</h3>\r\n");
						out.write("                            </b>\r\n");
						out.write("                            <h3>What next ?</br></br>\r\n");
						out.write("\r\n");
						out.write("                            &nbsp;&nbsp;You will receive or you would have received an e-mail upon successful verification/authentication of your details by Admin<br /><br />\r\n");
						out.write("                                &nbsp;&nbsp;After that you would be able to login into your account and attempt the Online Exam.<br /><br />\r\n");
						out.write("\r\n");
						out.write("                            </h3>\r\n");
						out.write("                            <br />\r\n");
						out.write("                            <br />\r\n");
						out.write("                          \r\n");
						out.write("                        </div>\r\n");

					}

				}

				else {
					// System.out.println("Email is not registered");
					out.write("                        <div style='border:5px dotted red;height:400px;width:710px;margin-left:-25px;font-family:Consolas'>\r\n");
					out.write("                            <h1 style=\" text-align:center\"><img src=\"../images/error1.jpg\" />&nbsp;Verification Unsuccessful</h1>\r\n");
					out.write("                            <hr>\r\n");
					out.write("                            <b>\r\n");
					out.write("                              <h3>  Your E-Mail Id is not registered with us.</h3>\r\n");
					out.write("                            </b>\r\n");
					out.write("                            <h3>What next ?</br></br>\r\n");
					out.write("                            &nbsp;Register your account and verify your E-Mail Id.<br />\r\n");
					out.write("                            &nbsp;&nbsp;Following which you will receive an e-mail upon successful verification/authentication of your details by Admin<br /><br />\r\n");
					out.write("                                &nbsp;&nbsp;After that you would be able to login into your account and attempt the Online Exam.<br /><br />\r\n");
					out.write("\r\n");
					out.write("                            </h3>\r\n");
					out.write("                            <br />\r\n");
					out.write("                            <br />\r\n");
					out.write("                          \r\n");
					out.write("                        </div>\r\n");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

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
			out.write("                </form>\r\n");
			out.write("                <br />\r\n");
			out.write("                <br />\r\n");
			out.write("                <table align=center width=700px style=\"font-size:20px;\">\r\n");
			out.write("                    <tr>\r\n");
			out.write("                        <td><a href=\"FirstPage\">Login</a></td>\r\n");
			out.write("                        <td><a href=\"UserReg\">Student Registration</a></td>\r\n");
			out.write("                        <td><a href=\"\">About Us</a></td>\r\n");
			out.write("                        <td><a href=\"\">Contact Us</a></td>\r\n");
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

		else {
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
			out.write("                    <table style=\"font-family: Cambria;height:200px;\" >\r\n");
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
			out.write("                        <td><a href=\"\">About Us</a></td>\r\n");
			out.write("                        <td><a href=\"\">Contact Us</a></td>\r\n");
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

}
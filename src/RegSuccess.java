import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegSuccess extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7031158424638474627L;

	public void error(PrintWriter out1) {
		out1.println("<div  align='center' style='font-family:Consolas;border:5px dotted red;height:250px;font-family:Consolas;margin-left:-25px;' >");

		out1.println("<h1>Unable to Register</h1>");
		out1.println("<hr>");
		out1.println("<b>An error has been encountered while trying to register your details</br>");
		out1.println("Please try again.");
		out1.println("</b>");
		out1.println("<br><a href='../UserReg.html'>Try Again</a>");
		out1.println("</div>");

	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		PrintWriter out = res.getWriter();
		out.write("<!DOCTYPE html>\r\n");
		out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
		out.write("<head>\r\n");
		out.write("    <title>...::: RAT Online Academic Panel :::...</title>\r\n");
		out.write("    <link rel=\"shortcut icon\" href=\"images/ratico.ico\" />\r\n");
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
		out.write("    <table style=\"background:url(images/bg.jpg) no-repeat\">\r\n");
		out.write("        <tr>\r\n");
		out.write("            <td><div id=\"left\"></div></td>\r\n");
		out.write("            <td>\r\n");
		out.write("                <div id=\"top\" height=\"250\" style=\"font-family: Lucida Calligraphy; background-color: #336699; color:cyan\">\r\n");
		out.write("                    <img src=\"images/rat.png\" height=\"100\" style=\"float:left\"><h1 align=\"right\">Road Ahead Technologies&nbsp;</h1><h2 align=\"right\">Online Academic Panel&nbsp;&nbsp;</h2>\r\n");
		out.write("                </div>\r\n");
		out.write("                <form name=\"myForm\" action=\"servlet/CheckReg\" onsubmit=\"return validateForm();\" method=\"post\">\r\n");
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
		out.write("                            <td><img src=\"images/s1.jpg\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
		out.write("\r\n");
		out.write("                            <td><img src=\"images/s2.jpg\" /></td>\r\n");
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

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		PrintWriter out = res.getWriter();
		out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
		out.write("<head>\r\n");
		out.write("    <title>...::: RAT Online Academic Panel :::...</title>\r\n");
		out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
		out.write("    <style type=\"text/css\">\r\n");
		out.write("        #top {\r\n");
		out.write("            margin-top: -180px;\r\n");
		out.write("\t\t\tmargin-left: -57px;\r\n");
		out.write("            \r\n");
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
		out.write("            height: 810px;\r\n");
		out.write("        }\r\n");
		out.write("\r\n");
		out.write("        #right {\r\n");
		out.write("            width: 150px;\r\n");
		out.write("            background-color: darkgrey;\r\n");
		out.write("            margin-right: -50px;\r\n");
		out.write("            margin-bottom: -10px;\r\n");
		out.write("            margin-top: -10px;\r\n");
		out.write("            margin-left: -3px;\r\n");
		out.write("            height: 810px;\r\n");
		out.write("        }\r\n");
		out.write("\r\n");
		out.write("        #mar\r\n");
		out.write("        {\r\n");
		out.write("            margin-top:-20px;\r\n");
		out.write("        }\r\n");
		out.write("\t\t #bot\r\n");
		out.write("        {\r\n");
		out.write("            height:90px;\r\n");
		out.write("            text-align:center;\r\n");
		out.write("            font-family:Verdana;\r\n");
		out.write("            color:white;\r\n");
		out.write("            margin-bottom:-160px;\r\n");
		out.write("\t\t\tmargin-left: -57px;\r\n");
		out.write("\t\t\tfont-size:15px;\r\n");
		out.write("\r\n");
		out.write("        }\r\n");
		out.write("    </style>\r\n");
		out.write("</head>\r\n");
		out.write("<body>\r\n");
		out.write("\r\n");
		out.write("<table style=\"background:url(../images/bg.jpg) no-repeat\" width=1340px>\r\n");
		out.write("        <tr>\r\n");
		out.write("            <td><div id=\"left\"></div></td>\r\n");
		out.write("            <td>\r\n");
		out.write("                <div id=\"top\" height=\"250\" style=\"font-family: Lucida Calligraphy; background-color: #336699; color:cyan\">\r\n");
		out.write("                    <img src=\"../images/rat.png\" height=\"100\" style=\"float:left\"><h1 align=\"right\">Road Ahead Technologies&nbsp;</h1><h2 align=\"right\">Online Academic Panel&nbsp;&nbsp;</h2>\r\n");
		out.write("                </div>\r\n");
		out.write("\t\t\t\r\n");
		out.write("\t\t\t\t\r\n");
		out.write("\t\t\t\r\n");
		out.write("\t\t\t\r\n");

		String str1 = req.getParameter("br");
		String str2 = req.getParameter("fname");
		String str3 = req.getParameter("lname");
		String str4 = req.getParameter("uname");
		String str5 = req.getParameter("mob");
		String str6 = req.getParameter("email");
		String id = "stu";
		for (int i = 0; i < 6; i++) {
			id = id + (int) (Math.random() * 10);
		}

		Connection con = DBInfo.getConn();
		String query = "insert into user values(?,?,?,?,?,?,?,?,?,?)";

		System.out.println(id + ":" + str1 + ":" + str2 + ":" + str3 + ":"
				+ str4 + ":" + str5 + ":" + str6);
		try {

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, str1);
			ps.setString(3, str2);
			ps.setString(4, str3);
			ps.setString(5, str4);
			ps.setString(6, str5);
			ps.setString(7, str6);
			ps.setString(8, "");
			ps.setString(9, "N");
			ps.setString(10, "N");

			int i = ps.executeUpdate();

			if (i == 1) {
				FirstSMS.send(id);
				SendMail mail = new SendMail(str6);
				mail.sendVerify(str2);
				out.println("<center><div style='border:5px dotted green;height:250px;width:710px;margin-left:-25px;font-family:Consolas' >");

				out.println("<h1>Registration Successful</h1>");
				out.println("<hr>");
				out.println("<b>Your details has been succesfully saved.");
				out.println("</b>");
				out.println("What next ?</br></br>");
				out.println("1. Please login into your e-mail account and click the verification link sent in the registration mail.<br /><br />");
				out.println("2. After successful verification of E-Mail Id, you will get the activation email within 24 hrs following which you would be able to login.");

				out.println("</div></center>");

			} else {
				error(out);
			}

		}

		catch (Exception e) {
			error(out);
			e.printStackTrace();

		}

		out.write("\t\t\t\t  <table align=\"center\">\r\n");
		out.write("                        <tr><br /><br /><br />\r\n");
		out.write("                            <td><img src=\"../images/s1.jpg\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
		out.write("                        \r\n");
		out.write("                            <td><img src=\"../images/s2.jpg\" /></td>\r\n");
		out.write("                        </tr>\r\n");
		out.write("\t\t\t\t\t</table>\r\n");
		out.write("\t\t\t\t\t<br />\r\n");
		out.write("                <br />\r\n");
		out.write("                <table align=center width=700px style=\"font-size:20px;\">\r\n");
		out.write("                    <tr>\r\n");
		out.write("                        <td><a href=\"FirstPage\">Login</a></td>\r\n");
		out.write("                        <td><a href=\"UserReg\">Student Registration</a></td>\r\n");
		out.write("\t\t\t\t\t\t<td><a href=\"AboutUs\">About Us</a></td>\r\n");
		out.write("\t\t\t\t\t\t<td><a href=\"ContactUs\">Contact Us</a></td>\r\n");
		out.write("\t\t\t\t\t\t\r\n");
		out.write("                    </tr>\r\n");
		out.write("                </table>\r\n");
		out.write("                <div id=\"bot\"  style=\"background-color: #336699; \">\r\n");
		out.write("                <br />\r\n");
		out.write("                    Copyright @ RAT 2014 </br><br />Developed By <a href=\"\">Gaurav Agarwal</a></div>\r\n");
		out.write("\t\t\t</td>\r\n");
		out.write("\t\t\t<td><div id=\"right\"></div></td>\r\n");
		out.write("        </tr>\r\n");
		out.write("   \r\n");
		out.write("    </table>\r\n");
		out.write("</body>\r\n");
		out.write("</html>\r\n");
		out.write("\r\n");

	}

}
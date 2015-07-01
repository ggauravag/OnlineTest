import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserReg extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3397375206852548804L;
	int i = 0;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		PrintWriter out = res.getWriter();
		/*
		 * if(i==0) { res.setHeader("Refresh","1"); i++; }
		 */
		out.write("<html>\r\n");
		out.write("<head>\r\n");
		out.write(" <title>...::: RAT Online Academic Panel :::...</title>\r\n");
		out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
		out.write("   <link rel=\"stylesheet\" href=\"../styles/StyleSheet3.css\" type=\"text/css\"/>\r\n");
		out.write("\r\n");
		out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js\"></script>\r\n");
		out.write("    <script src=\"http://code.jquery.com/ui/1.9.2/jquery-ui.js\"></script>\r\n");
		out.write("    <script type=\"text/javascript\" src=\"../script/marquee1.js\"></script>\r\n");
		out.write("</head>\r\n");
		out.write("<body>\r\n");
		out.write("    <script type=\"text/javascript\">\r\n");
		out.write("        \r\n");
		out.write("        function validate(e) {\r\n");
		out.write("            var keynum;\r\n");
		out.write("            if (e.which)\r\n");
		out.write("            { keynum = e.which; }\r\n");
		out.write("            keychar = String.fromCharCode(keynum);\r\n");
		out.write("            //document.write(keychar,keynum);\r\n");
		out.write("            numcheck = /\\d/;\r\n");
		out.write("            return numcheck.test(keychar);\r\n");
		out.write("        }\r\n");
		out.write("\r\n");
		out.write("        function validmob()\r\n");
		out.write("        {\r\n");
		out.write("            \r\n");
		out.write("            var num = document.myForm.mob.value;\r\n");
		out.write("            var num = num.toString().length;\r\n");
		out.write("            \r\n");
		out.write("            if(num < 10)\r\n");
		out.write("            {\r\n");
		out.write("                document.getElementById('labmob').style.color = \"red\";\r\n");
		out.write("                document.getElementById('labmob').innerText = \"* Invalid Mobile\";\r\n");
		out.write("              \r\n");
		out.write("            }\r\n");
		out.write("            else\r\n");
		out.write("            {\r\n");
		out.write("                document.getElementById('labmob').style.color = \"white\";\r\n");
		out.write("                document.getElementById('labmob').innerText = \"\";\r\n");
		out.write("               \r\n");
		out.write("            }\r\n");
		out.write("            \r\n");
		out.write("        }\r\n");
		out.write("\r\n");
		out.write("        function checkmail()\r\n");
		out.write("        {\r\n");
		out.write("            var x = document.myForm.email.value;\r\n");
		out.write("\r\n");
		out.write("            var exp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$/;\r\n");
		out.write("            if (exp.test(x)) {\r\n");
		out.write("                document.getElementById('labemail').style.color = \"white\";\r\n");
		out.write("                document.getElementById('labemail').innerText = \"\";\r\n");
		out.write("            }\r\n");
		out.write("            else {\r\n");
		out.write("                document.getElementById('labemail').style.color = \"red\";\r\n");
		out.write("                document.getElementById('labemail').innerText = \"*Invalid E-Mail\";\r\n");
		out.write("            }\r\n");
		out.write("            \r\n");
		out.write("        }\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("        function validateForm() {\r\n");
		out.write("            var x = document.forms[\"myForm\"][\"fname\"].value;\r\n");
		out.write("           \r\n");
		out.write("            if (document.forms[\"myForm\"][\"br\"].value == \"\") {\r\n");
		out.write("                alert(\"Branch must be selected\");\r\n");
		out.write("                document.myForm.br.focus();\r\n");
		out.write("                return false;\r\n");
		out.write("            }\r\n");
		out.write("            \r\n");
		out.write("            else if (x == null || x == \"\") {\r\n");
		out.write("                alert(\"First name must be filled out\");\r\n");
		out.write("                document.myForm.fname.focus();\r\n");
		out.write("                return false;\r\n");
		out.write("            }\r\n");
		out.write("\r\n");
		out.write("            else if(document.forms[\"myForm\"][\"lname\"].value==\"\")\r\n");
		out.write("            {\r\n");
		out.write("                alert(\"Last name must be filled out\");\r\n");
		out.write("                document.myForm.lname.focus();\r\n");
		out.write("                return false;\r\n");
		out.write("            }\r\n");
		out.write("\r\n");
		out.write("            else if (document.forms[\"myForm\"][\"uname\"].value == \"\") {\r\n");
		out.write("                alert(\"Username name must be filled out\");\r\n");
		out.write("                document.myForm.uname.focus();\r\n");
		out.write("                return false;\r\n");
		out.write("            }\r\n");
		out.write("\r\n");
		out.write("            else if (document.forms[\"myForm\"][\"mob\"].value == \"\") {\r\n");
		out.write("                alert(\"Mobile number must be filled out\");\r\n");
		out.write("                document.myForm.mob.focus();\r\n");
		out.write("                return false;\r\n");
		out.write("            }\r\n");
		out.write("\r\n");
		out.write("            else if (document.forms[\"myForm\"][\"email\"].value == \"\") {\r\n");
		out.write("                alert(\"E-Mail Id must be filled out\");\r\n");
		out.write("                document.myForm.email.focus();\r\n");
		out.write("                return false;\r\n");
		out.write("            }\r\n");
		out.write("\r\n");
		out.write("            else\r\n");
		out.write("            {\r\n");
		out.write("                var x = document.myForm.email.value;\r\n");
		out.write("                \r\n");
		out.write("                var exp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$/;\r\n");
		out.write("                if(exp.test(x))\r\n");
		out.write("                {\r\n");
		out.write("                    return true;\r\n");
		out.write("                }\r\n");
		out.write("                else {\r\n");
		out.write("                    alert(\"Email Id not valid\");\r\n");
		out.write("                    return false;\r\n");
		out.write("                }\r\n");
		out.write("            }\r\n");
		out.write("        }\r\n");
		out.write("\r\n");
		out.write("        \r\n");
		out.write("    </script>\r\n");
		out.write("    <table style=\"background:url(../images/bg.jpg) no-repeat\">\r\n");
		out.write("        <tr>\r\n");
		out.write("            <td><div id=\"left\"></div></td>\r\n");
		out.write("            <td>\r\n");
		out.write("                <div id=\"top\" height=\"250\" style=\"font-family: Lucida Calligraphy; background-color: #336699; color:cyan\">\r\n");
		out.write("                    <img src=\"../images/rat.png\" height=\"100\" style=\"float:left\"><h1 align=\"right\">Road Ahead Technologies&nbsp;</h1><br/><h2 align=\"right\">Online Academic Panel&nbsp;&nbsp;</h2>\r\n");
		out.write("                </div>\r\n");
		out.write("                <form name=\"myForm\" action=\"\" onsubmit=\"return validateForm();\" method=\"post\">\r\n");
		out.write("                    <div id=\"display\">\r\n");
		out.write("                        <p id=\"text\" style=\"font-family:Consolas\">\r\n");
		out.write("                           Road Ahead Technologies is structured around \"center of  excellence\" - our practice areas operate as a coordinated whole:  separate, yet capable of providing integrated services for our  student's benefit. This structure enables us to quickly mobilize the right resources to implement the strategies we develop - whenever and wherever students need.\r\n");
		out.write("                        </p>\r\n");
		out.write("                    </div>\r\n");
		out.write("\t\t\t\t\t<center>\r\n");
		out.write("                    <br />\r\n");
		out.write("\t\t\t\t\t<br/>\r\n");
		out.write("                    \r\n");
		out.write("                    <h1 style=\"font-family: Lucida Calligraphy; text-align:center\">*** New Student Registration ***</h1>\r\n");
		out.write("                    <br/>\r\n");
		out.write("\t\t\t\t\t<br/>\r\n");
		out.write("\t\t\t\t\t<table style=\"font-family: Cambria\">\r\n");
		out.write("                        <tr>\r\n");
		out.write("                            <td><table cellpadding=\"10\" width=\"500\" align=\"center\">\r\n");
		out.write("    <tr>\r\n");
		out.write("        <td></td>\r\n");
		out.write("        <td><b>Select Institute's Branch :</b></td>\r\n");
		out.write("        <th>\r\n");
		out.write("            <select name=\"br\">\r\n");
		out.write("                <option value=\"\">-----Select-----</option>\r\n");
		out.write("                <option value=\"TR\">Tonk Road</option>\r\n");
		out.write("                <option value=\"PN\">Pratap Nagar</option>\r\n");
		out.write("                <option value=\"KU\">Kukas</option>\r\n");
		out.write("            </select>\r\n");
		out.write("        </th>\r\n");
		out.write("        <th style=\"width:120px\"><label for=\"br\" id=\"labbr\"></label></th>\r\n");
		out.write("    </tr>\r\n");
		out.write("    <tr>\r\n");
		out.write("        <td></td>\r\n");
		out.write("        <td><b><br/>Enter First Name : </b></td>\r\n");
		out.write("        <th><br/><input type=text maxlength=20 name=\"fname\"></th>\r\n");
		out.write("        <th style=\"width:120px\"><label for=\"fname\" id=\"labfname\"></label></th>\r\n");
		out.write("    </tr>\r\n");
		out.write("    <tr>\r\n");
		out.write("        <td></td>\r\n");
		out.write("        <td><b><br/>Enter Last Name : </b></td>\r\n");
		out.write("        <th><br/><input type=text maxlength=20 name=\"lname\"></th>\r\n");
		out.write("        <th style=\"width:120px\"><label for=\"lname\" id=\"lablname\"></label></th>\r\n");
		out.write("    </tr>\r\n");
		out.write("    <tr>\r\n");
		out.write("        <td></td>\r\n");
		out.write("        <td><br/><b>Enter User Name : </b></td>\r\n");
		out.write("        <th><br/><input type=text maxlength=20 name=\"uname\"></th>\r\n");
		out.write("        <th style=\"width:120px\"><label for=\"uname\" id=\"labuname\"></label></th>\r\n");
		out.write("    </tr>\r\n");
		out.write("    <tr>\r\n");
		out.write("        <td></td>\r\n");
		out.write("        <td><br/><b>Enter Contact Number : </b></td>\r\n");
		out.write("        <th><br/><input type=\"text\" onkeypress=\"return validate(event);\" onblur=\"validmob();\" maxlength=\"10\" name=\"mob\"></th>\r\n");
		out.write("        <td style=\"width:120px\"><label id=\"labmob\" for=\"mob\" style=\"color:white\"></label></td>\r\n");
		out.write("    </tr>\r\n");
		out.write("    <tr>\r\n");
		out.write("        <td></td>\r\n");
		out.write("        <td><br/><b>Enter E-mail : </b></td>\r\n");
		out.write("        <th><br/><input type=\"text\" name=\"email\" onblur=\"checkmail();\"></th>\r\n");
		out.write("        <td style=\"width:120px\"><label for=\"email\" id=\"labemail\" style=\"color:white\"></label></td>\r\n");
		out.write("    </tr>\r\n");
		out.write("    <tr>\r\n");
		out.write("        <th colspan=\"2\"><br/><input type=\"submit\" value=\" Register \"></th>\r\n");
		out.write("        <th colspan=\"1\"><br/><input type=\"reset\" value=\" Reset \"></th>\r\n");
		out.write("    </tr>\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("</table></td>\r\n");
		out.write("                            <td><div id=\"info\"><h3>.... :::: 2-Step Verification Process :::: ....</h3><br/>\r\n");
		out.write("                                1. After successful registration , a verification e-mail would be sent to the student to confirm the authentication of given E-Mail Id.<br /></b>\r\n");
		out.write("                                <br />2. Once the student confirms the validity of E-Mail Id, another verification by Admin would be done in 24 hrs, after which Student would be able to login to his/her account.\r\n");
		out.write("                                </div></td>\r\n");
		out.write("                        </tr>\r\n");
		out.write("                    </table>\r\n");
		out.write("                   <br/><br/>\r\n");
		out.write("                    <table align=\"center\">\r\n");
		out.write("                        <tr><br /><br /><br />\r\n");
		out.write("                            <td><img src=\"../images/s1.jpg\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
		out.write("                        \r\n");
		out.write("                            <td><img src=\"../images/s2.jpg\" /></td>\r\n");
		out.write("                        </tr>\r\n");
		out.write("                        \r\n");
		out.write("                           \r\n");
		out.write("                                \r\n");
		out.write("                            \r\n");
		out.write("                       \r\n");
		out.write("                    </table>\r\n");
		out.write("                </form>\r\n");
		out.write("                <br />\r\n");
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
		out.write("\t\t\t\t</center>\r\n");
		out.write("                <div id=\"bot\"  style=\"background-color: #336699; \">\r\n");
		out.write("                <br />\r\n");
		out.write("                    Copyright @ RAT 2014 </br><br />Developed By <a href=\"\">Gaurav Agarwal</a></div>\r\n");
		out.write("            </td>\r\n");
		out.write("            <td><div id=\"right\"></div></td>\r\n");
		out.write("        </tr>\r\n");
		out.write("   \r\n");
		out.write("    </table>\r\n");
		out.write("    \r\n");
		out.write("   \r\n");
		out.write("</body>\r\n");
		out.write("</html>\r\n");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		PrintWriter out = res.getWriter();

		String uname = req.getParameter("uname");
		String email = req.getParameter("email");

		Connection con = DBInfo.getConn();
		String query = "select * from user where uname=? OR email=?";

		// System.out.println(id+":"+str1+":"+str2+":"+str3+":"+str4+":"+str5+":"+str6);
		try {

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, uname);
			ps.setString(2, email);

			ResultSet rs = ps.executeQuery();
			int flag = 0;
			while (rs.next()) {
				out.println("<script>alert('Username/email already exists. Forgot Password ?')</script>");
				doGet(req, res);
				flag = 1;
				break;
			}

			if (flag == 0) {
				RequestDispatcher rd = req.getRequestDispatcher("RegSuccess");
				rd.forward(req, res);

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

}
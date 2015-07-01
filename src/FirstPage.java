import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstPage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2771669838970777211L;
	String uname = null;
	String pass = null;
	String userid = null;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		PrintWriter out = res.getWriter();
		Cookie[] cks = req.getCookies();
		out.write("<html xmlns='http://www.w3.org/1999/xhtml'>\r\n");
		out.write("\t\t<title>...::: RAT Online Academic Panel :::...</title>\r\n");
		out.write("\t\t<link rel='shortcut icon' href='../images/ratico.ico' />\r\n");
		out.write("\t\t<link rel=\"stylesheet\" href=\"../styles/StyleSheet2.css\" type=\"text/css\"/>\r\n");
		out.write("\r\n");
		out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js\"></script>\r\n");
		out.write("    <script src=\"http://code.jquery.com/ui/1.9.2/jquery-ui.js\"></script>\r\n");
		out.write("    <script type=\"text/javascript\" src=\"../script/marquee1.js\"></script>\r\n");
		out.write("\t\t\r\n");
		out.write("\t\t</head><body>\r\n");
		out.write("\t\t\r\n");
		out.write("\t\t<script type='text/javascript'>\r\n");
		out.write("\t\tfunction validateForm() {if (document.forms['myForm']['uname'].value == '') {alert('Username cannot be left blank');document.myForm.uname.focus();return false;}\r\n");
		out.write("\t\tif(document.forms['myForm']['pass'].value==''){alert('Username/Password cannot be left blank');document.myForm.pass.focus();return false;}\r\n");
		out.write("\t\treturn true;}</script>\r\n");
		out.write("\t\t\r\n");
		out.write("\t\t\r\n");
		out.write("\t\t<table style='background:url(../images/bg.jpg) no-repeat'><tr><td><div id='left'></div></td>\r\n");
		out.write("\t\t<td><div id='top' height='250' style='font-family: Lucida Calligraphy; background-color: #336699; color:cyan'><img src='../images/rat.png' height='100' style='float:left'><h1 align='right'>Road Ahead Technologies&nbsp;</h1><br/><h2 align='right'>Online Academic Panel&nbsp;&nbsp;</h2></div>\r\n");
		out.write("\t\t<form name='myForm' action='CheckLogin' onsubmit='return validateForm();' method='post'>\r\n");
		out.write("\t\t <div id=\"display\">\r\n");
		out.write("                        <p id=\"text\" style=\"font-family:Consolas\">\r\n");
		out.write("                           Road Ahead Technologies is structured around \"center of  excellence\" - our practice areas operate as a coordinated whole:  separate, yet capable of providing integrated services for our  student's benefit. This structure enables us to quickly mobilize the right resources to implement the strategies we develop - whenever and wherever students need.\r\n");
		out.write("                        </p>\r\n");
		out.write("                    </div>\r\n");
		out.write("\t\t\t\t\t<center>\r\n");
		out.write("\t\t<br />\r\n");
		out.write("\t\t\r\n");
		out.write("\t\t<br/><h2 style=\"font-family:Lucida Calligraphy\"><u>**** Login ****</u></h2><br/><br/>\r\n");
		out.write("\t\t<table cellpadding='10' width='600' align='center' style='font-family:Consolas;' >\r\n");
		out.write("\t\t<tr><td></td><td><b>Enter Username : </b></td><th><input type='text' name='uname' /></th><th><label for='br' id='labbr'></label></th></tr>\r\n");
		out.write("\t\t<tr><td></td><td><br/><b>Enter Password : </b></td><th><br/><input type='password' name='pass'></th><th><a href='ForgotPassword'>Forgot Password ?</a></th></tr>\r\n");
		out.write("\t\t<tr> <td></td><td><br/><input type='checkbox' name='remember' value='y' />&nbsp;Remember me </td> <td></td><td></td> </tr>\r\n");
		out.write("\t\t<tr><td></td><th><br/><input type='submit' value=' Login ' /></th><th><br/><input type='reset' value=' Reset ' /></th></tr><tr><th colspan='4'><br/><br/><a href=UserReg>NEW USER ? Click Here to register</a></th></tr> </table>\r\n");
		out.write("\t\t<table align='center'><tr><br /><br /><br /><br /><td><img src='../images/s1.jpg' /></td> <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><img src='../images/s2.jpg' /></td></tr></table>  </form>\r\n");
		out.write("\t\t<br /><br />\r\n");
		out.write("\t\t\r\n");
		out.write("\t\t<table align=center width=700px style='font-size:20px;'>\r\n");
		out.write("                    <tr>\r\n");
		out.write("                        <td><a href='FirstPage'>Login</a></td>\r\n");
		out.write("                        <td><a href='UserReg'>Student Registration</a></td>\r\n");
		out.write("\t\t\t\t\t\t<td><a href='AboutUs'>About Us</a></td>\r\n");
		out.write("\t\t\t\t\t\t<td><a href='ContactUs'>Contact Us</a></td>\r\n");
		out.write("\t\t\t\t\t\t\r\n");
		out.write("                    </tr>\r\n");
		out.write("                </table>\r\n");
		out.write("\t\t\t\t</center>\r\n");
		out.write("                <div id='bot'  style='background-color: #336699; '>\r\n");
		out.write("                <br />\r\n");
		out.write("                    Copyright @ RAT 2014 </br><br />Developed By <a href='https://www.facebook.com/ggauravag' target='_TOP' title='Gaurav Agarwal'>Gaurav Agarwal</a></div>\r\n");
		out.write("\t\t</td> <td><div id='right'></div></td></tr> </table>\r\n");
		out.write("\t\t</body></html>");

		if (cks != null) {
			System.out.println("Number of cookies : " + cks.length);
			for (Cookie c : cks) {
				if (c.getName().equals("userid")) {
					userid = AESCrypto.decrypt(c.getValue().toString());
					System.out.println("User id : " + userid);
				}

				else {
					System.out.println("Unknown Cookie");
				}
			}

			try {

				String query = "select * from login where userid=?";
				Connection con = DBInfo.getConn();
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, userid);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					System.out.println("Record exists of userid");
					uname = rs.getString(1);
					pass = AESCrypto.decrypt(rs.getString(2));
					out.println("<script type=text/javascript>document.forms['myForm']['uname'].value='"
							+ uname + "';");
					out.println("document.forms['myForm']['pass'].value='"
							+ pass + "';");

					if (rs.getString(5).equals("Y")) {
						out.println("document.forms['myForm'].submit();</script>");
					} else {
						out.println("</script>");
					}

				}

			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
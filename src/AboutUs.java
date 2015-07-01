import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AboutUs extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		PrintWriter out = res.getWriter();
		out.write("<!DOCTYPE html>\r\n");
		out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
		out.write("<head>\r\n");
		out.write("    <title>...::: RAT Online Academic Panel :::...</title>\r\n");
		out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
		out.write("<link rel=\"stylesheet\" href=\"../styles/StyleSheet1.css\" type=\"text/css\" />\r\n");
		out.write("\r\n");
		out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js\"></script>\r\n");
		out.write("    <script src=\"http://code.jquery.com/ui/1.9.2/jquery-ui.js\"></script>\r\n");
		out.write("    <script type=\"text/javascript\" src=\"../script/marquee.js\"></script>\r\n");
		out.write("\t\r\n");
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
		out.write("                    <img src=\"../images/rat.png\" height=\"100\" style=\"float:left\"><h1 align=\"right\">Road Ahead Technologies&nbsp;</h1><h2 align=\"right\"><br/>Online Academic Panel&nbsp;&nbsp;</h2>\r\n");
		out.write("                </div>\r\n");
		out.write("                \r\n");
		out.write("<div style=\"width:1170px;\">\r\n");
		out.write("<center>\r\n");
		out.write("\t<table style=\"width:900px;\">\r\n");
		out.write("\t\t\t<tr>\r\n");
		out.write("\t\t\t\t<td></td>\r\n");
		out.write("\t\t\t\t<td>\r\n");
		out.write("\t\t\t\t<br/>\r\n");
		out.write("                        <h2>Know us in Detail !!!</h2>\r\n");
		out.write("\t\t\t\t\t<p>Road Ahead Technologies (I) Pvt. Ltd. incorporated in 2004 is an ISO 9001:2008 certified organization that operates through well-defined systems and procedures. We have been relentlessly endeavoring to provide end to end solutions to the Information Technology Industry with our expertise developed through the profound experience we continue contributing in training, consulting and Software Development Services,  all over Rajasthan.<Br>\r\n");
		out.write("\t\t\t\t\t<br>\r\n");
		out.write("It provides information and resources on IT education, training, development and services for everyone involved in the sector - from students to educators and employers to employees. RAT through its strategic alliances with global leaders like Oracle formulates the link between individuals and agencies with IT skills, opportunities and solutions. <br>\r\n");
		out.write("<br>\r\n");
		out.write("This helps the students keep up to date with the latest learning and professional accreditation in the industry. It has been our mission to provide timely programs that \r\n");
		out.write("respond to the needs of the industry, RAT has been at the forefront and has pioneered various training programs.</p>\r\n");
		out.write("\t\t\t\t<p>&nbsp;</p>\r\n");
		out.write("\t\t\t\t\t<h2 id=awards >AWARDS</h2>\r\n");
		out.write("\t\t\t\t\t<p><ul>\r\n");
		out.write("                <li> &nbsp; Road Ahead Technologies is prometric  centre for oracle as well as for all the certification courses.</li>\r\n");
		out.write("              \r\n");
		out.write("                <li> &nbsp; We had a tie-up with a UGC approved State  University.</li>\r\n");
		out.write("              \r\n");
		out.write("                <li> &nbsp; We are providing an opportunity of doing  Live projects in various renound companies of IT sector.</li>\r\n");
		out.write("              \r\n");
		out.write("              \r\n");
		out.write("                <li> &nbsp; In a very short span, we have trained  more than 5000 IT professionals from almost every part of Rajasthan.</li>\r\n");
		out.write("              \r\n");
		out.write("              \r\n");
		out.write("                <li> &nbsp; More than 3000 trained professionals from  Road Ahead Technologies, Jaipur are presently working at some of the very  elite posts in the industry not &nbsp; only in India but in countries like United  States, U.K., Australia, etc. also.</li>\r\n");
		out.write("              \r\n");
		out.write("              \r\n");
		out.write("                <li> &nbsp; It also holds the most unique and most  easy way to learn Java and .net correspondence course packages &ldquo;based on  technical psyche&rdquo;.</li>\r\n");
		out.write("             \r\n");
		out.write("              \r\n");
		out.write("                <li> &nbsp; We specialize in providing a unique  summer training program for IT students across the country.</li>\r\n");
		out.write("                </ul></p>\r\n");
		out.write("\t\t\t\t</td>\r\n");
		out.write("\t\t\t\t<td></td>\r\n");
		out.write("\t\t\t\t</tr>\r\n");
		out.write("\t\t\t\t</table>\r\n");
		out.write("\t\t\t\t</center>\r\n");
		out.write("\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t\t<center>\r\n");
		out.write("                    <table align=\"center\">\r\n");
		out.write("                        <tr>\r\n");
		out.write("                            \r\n");
		out.write("                            <br />\r\n");
		out.write("                            <br />\r\n");
		out.write("                            <td><img height=100 src=\"../images/s1.jpg\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
		out.write("\r\n");
		out.write("                            <td><img height=100 src=\"../images/s2.jpg\" /></td>\r\n");
		out.write("                        </tr>\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("                    </table>\r\n");
		out.write("                </center>\r\n");
		out.write("                <br />\r\n");
		out.write("                <br />\r\n");
		out.write("\t\t\t\t<center>\r\n");
		out.write("                <table  width=700px style=\"font-size:20px;\">\r\n");
		out.write("                    <tr>\r\n");
		out.write("                        <td><a href=\"FirstPage\">Login</a></td>\r\n");
		out.write("                        <td><a href=\"UserReg\">Student Registration</a></td>\r\n");
		out.write("                        <td><a href=\"AboutUs\">About Us</a></td>\r\n");
		out.write("                        <td><a href=\"ContactUs\">Contact Us</a></td>\r\n");
		out.write("\r\n");
		out.write("                    </tr>\r\n");
		out.write("                </table>\r\n");
		out.write("\t\t\t\t</center>\r\n");
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
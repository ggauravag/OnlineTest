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

public class StartExam extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6128811479373733217L;
	String userid = null;
	Connection con = DBInfo.getConn();
	int quest = 0;
	int ques = 0;

	StringBuilder correctans = null; // To Save correct answer for every
										// question
	StringBuilder ans = null; // To save attempted answer for every question
	Vector<Question> questions = new Vector<Question>(); // To load all
															// questions from
															// database
	String exam = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession(false);

		PrintWriter out = res.getWriter();
		quest = 0;

		if (session != null && session.getAttribute("userid") != null) {
			userid = (String) session.getAttribute("userid");
			exam = AESCrypto.decrypt(req.getParameter("exam"));
			String query = "select * from exam where exam_name = ?";
			try {
				ps = con.prepareStatement(query);
				ps.setString(1, exam);
				rs = ps.executeQuery();
				if (rs.next()) {
					ques = rs.getInt("question");
					String ansb = "";
					String correctansb = "";
					for (int j = 0; j < ques; j++) {
						ansb = ansb + "e"; // to initialise the attempted answer
											// all as skipped i.e e
					}
					ans = new StringBuilder(ansb);
					query = "select * from " + exam + "";
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();

					while (rs.next()) {
						// To load questions from database and correct answers
						// in the string
						correctansb = correctansb + rs.getString(7);
						questions.add(new Question(rs.getInt(1), rs
								.getString(2), rs.getString(3),
								rs.getString(4), rs.getString(5), rs
										.getString(6), rs.getString(7)));
					}
					correctans = new StringBuilder(correctansb);
					Question u = questions.get(quest);

					// to save answers of student in database
					query = "insert into result values(?,now(),?,?,?,NULL,NULL)";
					ps = con.prepareStatement(query);
					ps.setString(1, exam);

					ps.setString(2, userid);
					ps.setString(3, ans.toString());
					ps.setString(4, correctans.toString());

					int result = ps.executeUpdate();

					// to calculate marks and result
					int leng = correctans.length();
					int marks = 0;
					for (int i = 0; i < leng; i++) {
						if (correctans.charAt(i) == ans.charAt(i)) {
							marks = marks + 4;
						} else if (ans.charAt(i) == 'e') {

						} else {
							marks = marks - 1;
						}
					}

					int tot_marks = leng * 4;
					String resultex = "";
					if ((marks / (float) tot_marks) > 0.36) {
						resultex = "pass";
					} else {
						resultex = "fail";
					}

					try {
						query = "update result set marks = ? , result = ? where userid = ? and exam_name = ?";
						ps = con.prepareStatement(query);
						ps.setInt(1, marks);
						ps.setString(2, resultex);
						ps.setString(3, userid);
						ps.setString(4, exam);
						ps.executeUpdate();

					} catch (Exception e) {
						e.printStackTrace();
					}

					if (result == 1) {
						out.write("<!DOCTYPE html>\r\n");
						out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
						out.write("<head>\r\n");
						out.write("    <title>...::: Online Test :::...</title>\r\n");
						out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
						out.write("\t<link rel=\"stylesheet\" href=\"../styles/StyleSheet.css\" type=\"text/css\" />    \r\n");
						out.write("</head>\r\n");
						out.write("<body onunload=\"alert('You have submitted your test successfully');\" oncontextmenu=\"myClick(); return false;\" style=\"background:url(../images/bg.jpg);no-repeat\" >\r\n");
						out.write("    <form name=\"myform\" method='post' action=\"\">\r\n");
						out.write("        <table>\r\n");
						out.write("            <tr>\r\n");
						out.write("\t            <td><div id=\"left\" style=\"height:820px\"></div></td>\r\n");
						out.write("\t            <td>\r\n");
						out.write("\t                   \r\n");
						out.write("\t              \r\n");
						out.write("\t       \r\n");
						out.write("\t                <div id=\"center\">\r\n");
						out.write("\r\n");
						out.write("\r\n");
						out.write("\t                    <div id=\"top\" height=\"400\" style=\"font-family: Lucida Calligraphy; background-color: #336699; color:cyan\">\r\n");
						out.write("\t                        <img src=\"../images/rat.png\" height=\"100\" style=\"float:left\"><h1 align=\"right\">Road Ahead Technologies&nbsp;</h1><br /><h2 align=\"right\">Online Academic Panel&nbsp;&nbsp;</h2>\r\n");
						out.write("\t                    </div>\r\n");
						out.write("\t                    <br />\r\n");
						out.write("\r\n");
						out.write("\t                    \r\n");
						out.write("\t\t\t\t\t\t<br/>\r\n");
						out.write("\t\t\t\t\t\t\r\n");
						out.write("\t\t\t\t\t\t<table style=\"font-family: Lucida Handwriting;\" width=\"1040\">\r\n");
						out.write("\t                        <tr>\r\n");
						out.write("\t                            <td align=\"left\">\r\n");
						out.write("\t                                <strong>Time Left ( in seconds ) :&nbsp;</strong>\r\n");
						out.write("\t                                <label id=\"timeleft\"></label>\r\n");
						out.write("\t                            </td>\r\n");
						out.write("\t                            <td align=\"right\">\r\n");
						out.write("\t                                <strong>Questions Unattempted :&nbsp;</strong>\r\n");
						out.write("\t                                <label id=\"quesleft\"></label>\r\n");
						out.write("\t                            </td>\r\n");
						out.write("\t                        </tr>\r\n");
						out.write("\t                    </table>\r\n");
						out.write("\t\t\t\t\t\t<input type=hidden name=time value=\"\" />\r\n");
						out.write("\r\n");
						out.write("\t                    <br />\r\n");
						out.write("\t                    <br />\r\n");
						out.write("\t                    <center>\r\n");
						out.write("\t\t\t\t\t\t<div style='height:450px;'>\r\n");
						out.write("\t\t\t\t\t\t\r\n");
						out.write("\t\t\t\t\t\t\t<table style=\"font-family:Consolas;font-size:20px;width:950px;\">\r\n");
						out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td><br/>Q. "
								+ u.getQuesno() + "</td>\r\n");
						out.println("<input type=hidden name=quesno value="
								+ u.getQuesno() + " />");
						out.write("\t\t\t\t\t\t\t\t\t<td colspan=2 ><br/><b>"
								+ u.getQuestion() + "</b></td>\r\n");
						out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
						out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td><br/><br/>(a)&nbsp;<input type=radio name=ans value=a onclick=\"update(this.name,this.value)\"/></td>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td><br/><br/>"
								+ u.getOp1() + "</td>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td>&nbsp;</td>\r\n");
						out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
						out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td><br/>(b)&nbsp;<input type=radio name=ans value=b onclick=\"update(this.name,this.value)\"/></td>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td><br/>" + u.getOp2()
								+ "</td>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td>&nbsp;</td>\r\n");
						out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
						out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td><br/>(c)&nbsp;<input type=radio name=ans value=c onclick=\"update(this.name,this.value)\"/></td>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td><br/>" + u.getOp3()
								+ "</td>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td>&nbsp;</td>\r\n");
						out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
						out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td><br/>(d)&nbsp;<input type=radio name=ans value=d onclick=\"update(this.name,this.value)\"/></td>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td><br/>" + u.getOp4()
								+ "</td>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td>&nbsp;</td>\r\n");
						out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
						out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td><br/>(e)&nbsp;<input type=radio name=ans value=e onclick=\"update(this.name,this.value)\" checked /> </td>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td><br/>SKIP</td>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td>&nbsp;</td>\r\n");
						out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
						out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td><br/><br/><input type=submit value=\" NEXT >> \" name=next /></td>\r\n");
						out.write("\t\t\t\t\t\t\t\t\t<td><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=submit value=\" << PREV \" name=prev /></td> \r\n");
						out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
						out.write("\t\t\t\t\t\t\t</table>\r\n");
						out.write("\t\t\t\t\t\t\r\n");
						out.write("\t\t\t\t\t\t</div>\r\n");
						out.write("\t\t\t\t\t\t</center>\r\n");
						out.write("\t\t\t\t\t</div>\r\n");
						out.write("\t\t\t\t\t<br/>\r\n");
						out.write("\t\t\t\t\t<br/>\r\n");
						out.write("\t\t\t\t\t<br/>\r\n");
						out.write("\t\t\t\t\t <div id=\"bot\" style=\"background-color: #336699; \">\r\n");
						out.write("\t                        <br />\r\n");
						out.write("\t                        Copyright @ RAT 2014 </br><br />Developed By Gaurav Agarwal\r\n");
						out.write("\t                 </div>\r\n");
						out.write("\t               \r\n");
						out.write("\t            </td>\r\n");
						out.write("\t\t\t\t<td><div id=\"right\" style=\"height:820px\"></div></td>\r\n");
						out.write("            </tr>\r\n");
						out.write("        </table>\r\n");
						out.write("    </form>\r\n");
						out.write("    <script type=\"text/javascript\">\r\n");
						out.write("       \r\n");
						out.write("        \r\n");
						out.write("\r\n");
						out.write("        function myClick()\r\n");
						out.write("        {\r\n");
						out.write("            alert(\"This function is not allowed\");\r\n");
						out.write("        }\r\n");
						out.write("        var time = " + (ques * 20) + ";\r\n");
						out.write("        function upd() {\r\n");
						out.write("            var strtime = time.toString();\r\n");
						out.write("            if (time > 0) {\r\n");
						out.write("\r\n");
						out.write("                document.getElementById('timeleft').innerText = strtime;\r\n");
						out.write("\t\t\t\tdocument.myform.time.value = strtime;\r\n");
						out.write("                time--;\r\n");
						out.write("\r\n");
						out.write("            }\r\n");
						out.write("            else {\r\n");
						out.write("                document.getElementById('timeleft').innerText = 0;document.myform.time.value=0;\r\n");
						out.write("\t\t\t\t\r\n");
						out.write("                clearInterval(interval);\r\n");
						out.write("               document.myform.quesno.value = "
								+ ques + ";\r\n");
						out.write("       alert('Time Over : Test Submitted');document.myform.next.click();\r\n");
						out.write("                \r\n");
						out.write("            }\r\n");
						out.write("\r\n");
						out.write("        }\r\n");
						out.write("        upd();\r\n");
						out.write("        var interval = window.setInterval(\"upd()\", 1000);\r\n");
						out.write("        var ansradio;\r\n");
						out.write("      \r\n");
						out.write("        var qleft = " + ques + ";\r\n");
						out.write("        var flag=0;var strqleft = qleft.toString();\r\n");
						out.write("        document.getElementById('quesleft').innerText = strqleft;\r\n");
						out.write("        function update(Radio_name,value)\r\n");
						out.write("        {\r\n");
						out.write("            \r\n");
						out.write("            if(value != \"e\" && (ansradio != Radio_name || flag==0))\r\n");
						out.write("            {\r\n");
						out.write("                flag=1;qleft--;\r\n");
						out.write("                var strqleft = qleft.toString();\r\n");
						out.write("                document.getElementById('quesleft').innerText = strqleft;\r\n");
						out.write("            }\r\n");
						out.write("\t\t\tif(value == \"e\" && flag == 1)\r\n");
						out.write("\t\t\t{\r\n");
						out.write("\t\t\t\tqleft++;flag=0;\r\n");
						out.write("                var strqleft = qleft.toString();\r\n");
						out.write("                document.getElementById('quesleft').innerText = strqleft;\r\n");
						out.write("\t\t\t\r\n");
						out.write("\t\t\t}\r\n");
						out.write("         ansradio = Radio_name;   \r\n");
						out.write("            \r\n");
						out.write("        }\r\n");
						out.write("        \r\n");
						out.write("    </script>\r\n");
						out.write("\r\n");
						out.write("</body>\r\n");
						out.write("\r\n");
						out.write("</html>\r\n");
					} else {
						out.println("<script>alert('Error in adding results');</script>");
					}
				}
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
		String next = req.getParameter("next");
		String prev = req.getParameter("prev");
		int qno = Integer.parseInt(req.getParameter("quesno"));
		String ansi = req.getParameter("ans");
		int time = Integer.parseInt(req.getParameter("time"));
		ans.setCharAt(qno - 1, ansi.charAt(0));
		PrintWriter out = res.getWriter();
		int quesleft = 0;

		try {
			String query = "update result set user_ans=? where userid=? and exam_name=?";
			ps = con.prepareStatement(query);
			ps.setString(1, ans.toString());
			ps.setString(2, userid);
			ps.setString(3, exam);

			int result = ps.executeUpdate();

			for (int f = 0; f < ans.length(); f++) {
				if (ans.charAt(f) == 'e') {
					quesleft++;
				}
			}
			// System.out.println("Total number of questions is : "+ques+"\nLast ques is : "+qno);
			if (next != null || prev != null) {
				if (next != null) {
					if (qno < ques) {
						quest = quest + 1;
					} else if (qno == ques) {
						out.println("<script>alert('Please wait while your answers are being submitted.');</script>");
						calculateResult(out);

						out.println("<script>window.close();</script>");

					}
				}

				if (prev != null) {
					if (qno > 1) {
						quest = quest - 1;
					}
				}

			}
			Question u = questions.get(quest); // to load next question

			if (result == 1) {
				out.write("<!DOCTYPE html>\r\n");
				out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
				out.write("<head>\r\n");
				out.write("    <title>...::: Online Test :::...</title>\r\n");
				out.write("    <link rel=\"shortcut icon\" href=\"../images/ratico.ico\" />\r\n");
				out.write("\t<link rel=\"stylesheet\" href=\"../styles/StyleSheet.css\" type=\"text/css\" />    \r\n");
				out.write("</head>\r\n");
				out.write("<body onunload=\"alert('You have submitted your test successfully');\" oncontextmenu=\"myClick(); return false;\" style=\"background:url(../images/bg.jpg);no-repeat\" >\r\n");
				out.write("    <form name=\"myform\" method='post' action=\"\">\r\n");
				out.write("        <table>\r\n");
				out.write("            <tr>\r\n");
				out.write("\t            <td><div id=\"left\" style=\"height:820px\"></div></td>\r\n");
				out.write("\t            <td>\r\n");
				out.write("\t                   \r\n");
				out.write("\t              \r\n");
				out.write("\t       \r\n");
				out.write("\t                <div id=\"center\">\r\n");
				out.write("\r\n");
				out.write("\r\n");
				out.write("\t                    <div id=\"top\" height=\"400\" style=\"font-family: Lucida Calligraphy; background-color: #336699; color:cyan\">\r\n");
				out.write("\t                        <img src=\"../images/rat.png\" height=\"100\" style=\"float:left\"><h1 align=\"right\">Road Ahead Technologies&nbsp;</h1><br /><h2 align=\"right\">Online Academic Panel&nbsp;&nbsp;</h2>\r\n");
				out.write("\t                    </div>\r\n");
				out.write("\t                    <br />\r\n");
				out.write("\r\n");
				out.write("\t                    \r\n");
				out.write("\t\t\t\t\t\t<br/>\r\n");
				out.write("\t\t\t\t\t\t\r\n");
				out.write("\t\t\t\t\t\t<table style=\"font-family: Lucida Handwriting;\" width=\"1040\">\r\n");
				out.write("\t                        <tr>\r\n");
				out.write("\t                            <td align=\"left\">\r\n");
				out.write("\t                                <strong>Time Left ( in seconds ) :&nbsp;</strong>\r\n");
				out.write("\t                                <label id=\"timeleft\"></label>\r\n");
				out.write("\t                            </td>\r\n");
				out.write("\t                            <td align=\"right\">\r\n");
				out.write("\t                                <strong>Questions Unattempted :&nbsp;</strong>\r\n");
				out.write("\t                                <label id=\"quesleft\"></label>\r\n");
				out.write("\t                            </td>\r\n");
				out.write("\t                        </tr>\r\n");
				out.write("\t                    </table>\r\n");
				out.write("\t\t\t\t\t\t<input type=hidden name=time value=\"\" />\r\n");
				out.write("\r\n");
				out.write("\t                    <br />\r\n");
				out.write("\t                    <br />\r\n");
				out.write("\t                    <center>\r\n");
				out.write("\t\t\t\t\t\t<div style='height:450px;'>\r\n");
				out.write("\t\t\t\t\t\t\r\n");
				out.write("\t\t\t\t\t\t\t<table style=\"font-family:Consolas;font-size:20px;width:950px;\">\r\n");
				out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td><br/>Q. " + u.getQuesno()
						+ "</td>\r\n");
				out.println("<input type=hidden name=quesno value="
						+ u.getQuesno() + " />");
				out.write("\t\t\t\t\t\t\t\t\t<td colspan=2 ><br/><b>"
						+ u.getQuestion() + "</b></td>\r\n");
				out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td><br/><br/>(a)&nbsp;<input type=radio name=ans value=a onclick=\"update(this.name,this.value)\"/></td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td><br/><br/>" + u.getOp1()
						+ "</td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td>&nbsp;</td>\r\n");
				out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td><br/>(b)&nbsp;<input type=radio name=ans value=b onclick=\"update(this.name,this.value)\"/></td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td><br/>" + u.getOp2()
						+ "</td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td>&nbsp;</td>\r\n");
				out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td><br/>(c)&nbsp;<input type=radio name=ans value=c onclick=\"update(this.name,this.value)\"/></td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td><br/>" + u.getOp3()
						+ "</td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td>&nbsp;</td>\r\n");
				out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td><br/>(d)&nbsp;<input type=radio name=ans value=d onclick=\"update(this.name,this.value)\"/></td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td><br/>" + u.getOp4()
						+ "</td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td>&nbsp;</td>\r\n");
				out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td><br/>(e)&nbsp;<input type=radio name=ans value=e onclick=\"update(this.name,this.value)\" checked /> </td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td><br/>SKIP</td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td>&nbsp;</td>\r\n");
				out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td><br/><br/><input type=submit value=\" NEXT >> \" name=next /></td>\r\n");
				out.write("\t\t\t\t\t\t\t\t\t<td><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=submit value=\" << PREV \" name=prev /></td> \r\n");
				out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
				out.write("\t\t\t\t\t\t\t</table>\r\n");
				out.write("\t\t\t\t\t\t\r\n");
				out.write("\t\t\t\t\t\t</div>\r\n");
				out.write("\t\t\t\t\t\t</center>\r\n");
				out.write("\t\t\t\t\t</div>\r\n");
				out.write("\t\t\t\t\t<br/>\r\n");
				out.write("\t\t\t\t\t<br/>\r\n");
				out.write("\t\t\t\t\t<br/>\r\n");
				out.write("\t\t\t\t\t <div id=\"bot\" style=\"background-color: #336699; \">\r\n");
				out.write("\t                        <br />\r\n");
				out.write("\t                        Copyright @ RAT 2014 </br><br />Developed By Gaurav Agarwal\r\n");
				out.write("\t                 </div>\r\n");
				out.write("\t               \r\n");
				out.write("\t            </td>\r\n");
				out.write("\t\t\t\t<td><div id=\"right\" style=\"height:820px\"></div></td>\r\n");
				out.write("            </tr>\r\n");
				out.write("        </table>\r\n");
				out.write("    </form>\r\n");
				out.write("    <script type=\"text/javascript\">\r\n");
				out.write("       \r\n");
				out.write("        \r\n");
				out.write("\r\n");
				out.write("        function myClick()\r\n");
				out.write("        {\r\n");
				out.write("            alert(\"This function is not allowed\");\r\n");
				out.write("        }\r\n");
				out.write("        var time = " + (time) + ";\r\n");
				out.write("        function upd() {\r\n");
				out.write("            var strtime = time.toString();\r\n");
				out.write("            if (time > 0) {\r\n");
				out.write("\r\n");
				out.write("                document.getElementById('timeleft').innerText = strtime;\r\n");
				out.write("\t\t\t\tdocument.myform.time.value = strtime;\r\n");
				out.write("                time--;\r\n");
				out.write("\r\n");
				out.write("            }\r\n");
				out.write("            else {\r\n");
				out.write("                document.getElementById('timeleft').innerText = 0;document.myform.time.value=0;\r\n");
				out.write("\t\t\t\t\r\n");
				out.write("                clearInterval(interval);\r\n");
				out.write("                alert('Time Over : Test Submitted');\r\n");
				out.write("           document.myform.quesno.value=" + ques
						+ ";document.myform.next.click();\r\n");
				out.write("                \r\n");
				out.write("            }\r\n");
				out.write("\r\n");
				out.write("        }\r\n");
				out.write("        upd();\r\n");
				out.write("        var interval = window.setInterval(\"upd()\", 1000);\r\n");
				out.write("        var ans = new Array();\r\n");
				out.write("      \r\n");
				out.write("        var qleft = " + quesleft + ";\r\n");
				out.write("        var strqleft = qleft.toString();\r\n");
				out.write("        document.getElementById('quesleft').innerText = strqleft;\r\n");
				out.write("        function update(Radio_name,value)\r\n");
				out.write("        {\r\n");
				out.write("            \r\n");
				out.write("            if(value != \"e\")\r\n");
				out.write("            {\r\n");
				out.write("                qleft--;\r\n");
				out.write("                var strqleft = qleft.toString();\r\n");
				out.write("                document.getElementById('quesleft').innerText = strqleft;\r\n");
				out.write("            }\r\n");
				out.write("\t\t\telse\r\n");
				out.write("\t\t\t{\r\n");
				out.write("\t\t\t\tqleft++;\r\n");
				out.write("                var strqleft = qleft.toString();\r\n");
				out.write("                document.getElementById('quesleft').innerText = strqleft;\r\n");
				out.write("\t\t\t\r\n");
				out.write("\t\t\t}\r\n");
				out.write("            \r\n");
				out.write("            \r\n");
				out.write("        }\r\n");
				out.write("        \r\n");
				out.write("    </script>\r\n");
				out.write("\r\n");
				out.write("</body>\r\n");
				out.write("\r\n");
				out.write("</html>\r\n");
			} else {
				out.println("<script>alert('Error in updating answers');</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// function to calculate result
	void calculateResult(PrintWriter out) {
		int leng = correctans.length();
		int marks = 0;
		for (int i = 0; i < leng; i++) {
			if (correctans.charAt(i) == ans.charAt(i)) {
				marks = marks + 4;
			} else if (ans.charAt(i) == 'e') {

			} else {
				marks = marks - 1;
			}
		}

		int tot_marks = leng * 4;
		String result = "";
		if ((marks / (float) tot_marks) > 0.36) {
			result = "pass";
		} else {
			result = "fail";
		}

		try {
			String query = "update result set marks = ? , result = ? where userid = ? and exam_name = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, marks);
			ps.setString(2, result);
			ps.setString(3, userid);
			ps.setString(4, exam);
			int h = ps.executeUpdate();

			if (h == 1) {
				out.println("<script>alert('Answers successfully submitted');</script>");
				String response = ResultSMS.send(userid);
				if (response != null && response == "Failed#Invalid Login") {
					out.println("<script>alert(\"Unable to send SMS.\n\n Please contact Gaurav for authentication.\");</script>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
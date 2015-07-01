import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

class SendResult {
	static String from = "resultsrat@gmail.com";
	static String to = "";
	static Connection con = DBInfo.getConn();

	public static void send(String userid, String exam) {

		String query = "select * from result where userid=? and exam_name=?";
		String uname = null;
		try {

			String query1 = "select uname,email from user where userid=?";
			PreparedStatement ps1 = con.prepareStatement(query1);
			ps1.setString(1, userid);
			ResultSet rs1 = ps1.executeQuery();
			rs1.next();
			to = rs1.getString("email");
			uname = rs1.getString("uname");

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, userid);
			ps.setString(2, exam);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String userans = rs.getString("user_ans");
			String correct = rs.getString("correct_ans");
			int quest = correct.length();
			int attempted = 0;
			int correcta = 0;
			int wrong = 0;
			int[] marks = new int[quest];
			for (int i = 0; i < quest; i++) {
				if (correct.charAt(i) == userans.charAt(i)) {
					attempted++;
					correcta++;
					marks[i] = 4;
				} else if (userans.charAt(i) == 'e') {
					marks[i] = 0;
				} else {
					attempted++;
					wrong++;
					marks[i] = -1;
				}

			}

			String data = "";

			data = data
					+ "<center><h1><u><b>***** Detailed Result Online Exam : "
					+ exam.toUpperCase()
					+ " *****</b></u></h1><br /><br /><table style='width:550px;'><tr><td><table style='font-size:20px;text-align:center;' border=1 ><tr><td>Total Number of Questions : </td><td>"
					+ quest
					+ "</td></tr><tr><td>Attempted : </td><td>"
					+ attempted
					+ "</td></tr><tr><td>Correct : </td><td>"
					+ correcta
					+ "</td></tr><tr><td>Incorrect : </td><td>"
					+ wrong
					+ "</td></tr><tr><td>Result : </td><td>"
					+ rs.getString("result").toUpperCase()
					+ "</td></tr></table></td><td><img height=100 src='cid:result' /></td></tr><tr><td><br/><br/><table style='margin-left:-75px;' border=1><tr><td><strong>Q. No</strong>&nbsp;&nbsp;</td>";
			int h = 0;
			for (h = 0; h < quest; h++) {
				data = data + "<td>" + (h + 1) + "&nbsp;</td>";
			}
			data += "</tr>";
			data += "<tr>";
			data += "<td><strong>Attempt</strong>&nbsp;&nbsp;</td>";
			for (h = 0; h < quest; h++) {
				data += "<td>" + userans.charAt(h) + "&nbsp;</td>";
			}

			data += "</tr>";
			data += "<tr>";
			data += "<td><strong>Correct</strong>&nbsp;&nbsp;</td>";
			for (h = 0; h < quest; h++) {
				data += "<td>" + correct.charAt(h) + "&nbsp;</td>";
			}
			data += "</tr><tr>";
			data += "<td><strong>Marks</strong>&nbsp;&nbsp;</td>";
			for (h = 0; h < quest; h++) {
				data += "<td>" + marks[h] + "&nbsp;</td>";
			}
			data += "</tr>";
			data += "</table></td></tr></table><br/></center>";

			Properties properties = System.getProperties();
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");

			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			Session session = Session.getInstance(properties,
					new javax.mail.Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"resultsrat@gmail.com", "ratindia");// Specify
																		// the
																		// Username
																		// and
																		// the
																		// PassWord
						}
					});

			try {
				final MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(to));
				message.setSubject("Result : Online Test " + exam.toUpperCase()
						+ "");

				MimeMultipart multipart = new MimeMultipart("related");

				String workingDir = System.getProperty("user.dir");
				String path = workingDir
						.substring(0, (workingDir.length() - 3));

				BodyPart messageBodyPart = new MimeBodyPart();
				String htmltext = "<!DOCTYPE html><html xmlns='http://www.w3.org/1999/xhtml'><head><title>...::: RAT Online Academic Panel :::...</title></head><body><table style='background:url(cid:bg) no-repeat;'><tr><td><div id='top'  style='margin-top:-60px;height:100px;font-family: Lucida Calligraphy; background-color: #336699; color:cyan;'><img src='cid:logo' height='100' style='float:left'><h1 align='right'>Road Ahead Technologies&nbsp;</h1><h2 align='right'>Online Academic Panel&nbsp;&nbsp;</h2></div><br /><div id='info' style='font-family: Consolas;color:black;'>Dear, "
						+ uname
						+ ",<br/>Your result details are as follows : <br />"
						+ data
						+ "<br /><table align=center width=700px style='font-size:20px;'><tr><td><a href='http://localhost:8080/OnlineTest/servlet/FirstPage'>Login</a></td><td><a href='http://localhost:8080/OnlineTest/servlet/UserReg'>Student Registration</a></td><td><a href='http://localhost:8080/OnlineTest/servlet/AboutUs'>About Us</a></td><td><a href='http://localhost:8080/OnlineTest/servlet/ContactUs'>Contact Us</a></td></tr></table><div id='bot' style='background-color: #336699;height: 90px;text-align: center;font-family: Verdana;color: white; '><br />Copyright @ RAT 2014 <br/><br />Developed By <a href='http://www.facebook.com/ggauravag'>Gaurav Agarwal</a></div></td></tr></table></body></html>";
				messageBodyPart.setContent(htmltext, "text/html");
				multipart.addBodyPart(messageBodyPart);

				DataSource fds = new FileDataSource(path
						+ "webapps\\OnlineTest\\images\\rat.png");

				DataSource fds3 = new FileDataSource(path
						+ "webapps\\OnlineTest\\images\\bg.jpg");
				DataSource fds4 = new FileDataSource(path
						+ "webapps\\OnlineTest\\images\\"
						+ rs.getString("result") + "1.jpg");

				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setDataHandler(new DataHandler(fds));
				messageBodyPart.addHeader("Content-ID", "<logo>");
				multipart.addBodyPart(messageBodyPart);

				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setDataHandler(new DataHandler(fds3));
				messageBodyPart.addHeader("Content-ID", "<bg>");
				multipart.addBodyPart(messageBodyPart);

				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setDataHandler(new DataHandler(fds4));
				messageBodyPart.addHeader("Content-ID", "<result>");
				multipart.addBodyPart(messageBodyPart);

				message.setContent(multipart);

				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Transport.send(message);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();

				System.out.println("Sent result successfully from admin....");
				session = null;

			} catch (MessagingException mex) {
				mex.printStackTrace();
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
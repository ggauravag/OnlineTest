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

class ResetPasswordMail {
	static String from = "noreplyratindia@gmail.com";
	static String to = "";
	static Connection con = DBInfo.getConn();

	public static void sendResetLink(String email) {

		String query = "select * from user where email=?";
		String uname = null;
		try {

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			to = email;
			uname = rs.getString("fname") + " " + rs.getString("lname");
			String data = "";
			data += "Dear "
					+ uname
					+ ", <br/> Here is the link to reset your forgotten password : <br/><br/><a href='http://localhost:8080/OnlineTest/servlet/CreateSession?id="
					+ AESCrypto.encrypt(rs.getString("userid"))
					+ "' >Click here to reset your password</a><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>";

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
									"noreplyratindia@gmail.com", "ratindia");// Specify
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
				message.setSubject("Password Reset");

				MimeMultipart multipart = new MimeMultipart("related");

				BodyPart messageBodyPart = new MimeBodyPart();
				String htmltext = "<!DOCTYPE html><html xmlns='http://www.w3.org/1999/xhtml'><head><title>...::: RAT Online Academic Panel :::...</title></head><body><table style='background:url(cid:bg) no-repeat;'><tr><td><div id='top'  style='margin-top:-60px;height:100px;font-family: Lucida Calligraphy; background-color: #336699; color:cyan;'><img src='cid:logo' height='100' style='float:left'><h1 align='right'>Road Ahead Technologies&nbsp;</h1><h2 align='right'>Online Academic Panel&nbsp;&nbsp;</h2></div><br /><div id='info' style='font-family: Consolas;color:black;font-size:18px;height:400px;'>"
						+ data
						+ "<table align=center width=700px style='font-size:20px;'><tr><td><a href='http://localhost:8080/OnlineTest/servlet/FirstPage'>Login</a></td><td><a href='http://localhost:8080/OnlineTest/servlet/UserReg'>Student Registration</a></td><td><a href='http://localhost:8080/OnlineTest/servlet/AboutUs'>About Us</a></td><td><a href='http://localhost:8080/OnlineTest/servlet/ContactUs'>Contact Us</a></td></tr></table><div id='bot' style='background-color: #336699;height: 90px;text-align: center;font-family: Verdana;color: white; '><br />Copyright @ RAT 2014 <br/><br />Developed By <a href='http://www.facebook.com/ggauravag'>Gaurav Agarwal</a></div></td></tr></table></body></html>";
				messageBodyPart.setContent(htmltext, "text/html");
				multipart.addBodyPart(messageBodyPart);

				String workingDir = System.getProperty("user.dir");

				String path1 = workingDir.substring(0,
						(workingDir.length() - 3));
				path1 += "webapps\\OnlineTest\\images\\rat.png";

				DataSource fds = new FileDataSource(path1);
				// DataSource fds = new FileDataSource("rat.png");

				// System.out.println("Current working directory : " +
				// workingDir);

				String path2 = workingDir.substring(0,
						(workingDir.length() - 3));
				path2 += "webapps\\OnlineTest\\images\\bg.jpg";

				DataSource fds3 = new FileDataSource(path2);

				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setDataHandler(new DataHandler(fds));
				messageBodyPart.addHeader("Content-ID", "<logo>");
				multipart.addBodyPart(messageBodyPart);

				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setDataHandler(new DataHandler(fds3));
				messageBodyPart.addHeader("Content-ID", "<bg>");
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

				System.out
						.println("Sent password reset successfully from admin....");
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
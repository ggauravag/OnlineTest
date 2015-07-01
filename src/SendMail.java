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

class SendMail {
	String from = "";
	String to = "";

	SendMail(String to) {
		this.to = to;
		from = "noreplyratindia@gmail.com";
	}

	public int sendVerify(String name) {
		int i = 0;
		Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		// properties.put("mail.smtp.user", "ramfurnitures@gmail.com"); // User
		// name
		// properties.put("mail.smtp.password", "43993389"); // password

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
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject("Registration Success : Verification E-mail");

			MimeMultipart multipart = new MimeMultipart("related");

			String workingDir = System.getProperty("user.dir");
			String path = workingDir.substring(0, (workingDir.length() - 3));

			BodyPart messageBodyPart = new MimeBodyPart();
			String htmltext = "<!DOCTYPE html><html xmlns='http://www.w3.org/1999/xhtml'><head><title>...::: RAT Online Academic Panel :::...</title></head><body><table style='background:url(cid:bg) no-repeat;'><tr><td><div id='top'  style='margin-top:-60px;height:100px;font-family: Lucida Calligraphy; background-color: #336699; color:cyan;'><img src='cid:logo' height='100' style='float:left'><h1 align='right'>Road Ahead Technologies&nbsp;</h1><h2 align='right'>Online Academic Panel&nbsp;&nbsp;</h2></div><br /><div id='info' style='font-family: Consolas;color:black;'><h1 style='font-family: Lucida Calligraphy; text-align:center'>Registration Success</h1><table style='font-family: Cambria;' >Dear "
					+ name
					+ ",<br /><br/>You have succesfully registered at Road Ahead Technologies Online Panel.<br /><br />Please click the following link to verify your E-Mail-Id <br /><br /><a href='http://localhost:8080/OnlineTest/servlet/VerifyEmail?email="
					+ AESCrypto.encrypt(to)
					+ "'>Click Here to verify</a><br /><br /></table></div><table align='center'><tr><br /><br /><br /><td><img src='cid:imagea' height=100 />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><img src='cid:imageb' height=100/></td></tr></table></form><br /><br /><table align=center width=700px style='font-size:20px;'><tr><td><a href='http://localhost:8080/OnlineTest/servlet/FirstPage'>Login</a></td><td><a href='http://localhost:8080/OnlineTest/servlet/UserReg'>Student Registration</a></td><td><a href='http://localhost:8080/OnlineTest/servlet/AboutUs'>About Us</a></td><td><a href='http://localhost:8080/OnlineTest/servlet/ContactUs'>Contact Us</a></td></tr></table><div id='bot' style='background-color: #336699;height: 90px;text-align: center;font-family: Verdana;color: white; '><br />Copyright @ RAT 2014 <br/><br />Developed By <a href='http://www.facebook.com/ggauravag'>Gaurav Agarwal</a></div></td></tr></table></body></html>";
			messageBodyPart.setContent(htmltext, "text/html");
			multipart.addBodyPart(messageBodyPart);

			DataSource fds = new FileDataSource(path
					+ "webapps\\OnlineTest\\images\\rat.png");
			DataSource fds1 = new FileDataSource(path
					+ "webapps\\OnlineTest\\images\\s1.jpg");
			DataSource fds2 = new FileDataSource(path
					+ "webapps\\OnlineTest\\images\\s2.jpg");
			DataSource fds3 = new FileDataSource(path
					+ "webapps\\OnlineTest\\images\\bg.jpg");

			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.addHeader("Content-ID", "<logo>");
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setDataHandler(new DataHandler(fds1));
			messageBodyPart.addHeader("Content-ID", "<imagea>");
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setDataHandler(new DataHandler(fds2));
			messageBodyPart.addHeader("Content-ID", "<imageb>");
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

			System.out.println("Sent message successfully....");
			session = null;
			i = 1;

		} catch (MessagingException mex) {
			mex.printStackTrace();
			return i;
		}
		return i;
	}

}
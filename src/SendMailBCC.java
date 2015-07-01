
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailBCC {
	public static String from = "youremail@gmail.com";
	public static String to[] = { "ggauravag@gmail.com" };

	public static int sendVerify() {
		int i = 0;
		Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");

		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("yourmail@gmail.com",
								"password");// Specify the Username and the
											// PassWord
					}
				});

		try {
			final MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			InternetAddress[] addressTo = new InternetAddress[to.length];
			for (int j = 0; j < to.length; j++) {
				addressTo[j] = new InternetAddress(to[j]);
			}
			message.setRecipients(Message.RecipientType.BCC, addressTo);
			message.setSubject("Authentication Success : Confirmation E-mail");

			message.setText("This is actual message");

			Transport.send(message);

			System.out.println("Sent message successfully from admin....");
			session = null;
			i = 1;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return i;
		}
		return i;
	}

	// public static void main(String args[])
	// {
	// String[] to =
	// {"ggauravag@gmail.com","gauravag94@gmail.com","bhootsushil@ymail.com","gauravag94@live.com"};
	// SendMailBCC mail = new SendMailBCC(to);
	// mail.sendVerify();
	// }

}
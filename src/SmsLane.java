import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

//Only numbers that have not registered as DND will receive the SMS

public class SmsLane {
	public static String retval = "";

	public static String sendSMS(String msisdn, String msg) {
		String rsp = "";
		String user = "ggauravag";// /AESCrypto.decrypt("7fMs2hJHbPeLdaQM93dBJA==");
		String password = "smscode";// AESCrypto.decrypt("pZ5a+Sv2vHzSmjr2s9qRWg==");
		String sid = "WebSMS";
		String flash = "1";
		try {
			// Construct The Post Data
			String data = URLEncoder.encode("user", "UTF-8") + "="
					+ URLEncoder.encode(user, "UTF-8");
			data += "&" + URLEncoder.encode("password", "UTF-8") + "="
					+ URLEncoder.encode(password, "UTF-8");
			data += "&" + URLEncoder.encode("msisdn", "UTF-8") + "="
					+ URLEncoder.encode(msisdn, "UTF-8");
			data += "&" + URLEncoder.encode("msg", "UTF-8") + "="
					+ URLEncoder.encode(msg, "UTF-8");
			data += "&" + URLEncoder.encode("sid", "UTF-8") + "="
					+ URLEncoder.encode(sid, "UTF-8");
			data += "&" + URLEncoder.encode("fl", "UTF-8") + "="
					+ URLEncoder.encode(flash, "UTF-8");

			// Push the HTTP Request
			URL url = new URL("http://smslane.com/vendorsms/pushsms.aspx");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);

			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Read The Response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				// Process line...
				retval += line;
			}
			wr.close();
			rd.close();

			// System.out.println(retval);
			rsp = retval;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsp;
	}

	public static void main(String[] args) {
		String response = sendSMS("919414868388",
				"Hey,  this is a test message from RAT");
		System.out.println(response);
	}
}
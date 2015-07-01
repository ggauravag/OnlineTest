import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendEmailServ extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6225909729374688437L;
	String userid = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = DBInfo.getConn();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		PrintWriter out = res.getWriter();
		out.println("<html><body><form method=post><input type=submit value='Send Email' /></form></body></html>");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		SendMailBCC.sendVerify();

	}

}
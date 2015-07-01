import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateSession extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6699160489528827826L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String uid = req.getParameter("id").replace(' ', '+');
		if (uid != null) {
			String id = AESCrypto.decrypt(uid);
			HttpSession session = req.getSession();
			session.setAttribute("userid", id);
			res.sendRedirect("ChangePassword");
		} else {
			res.sendRedirect("FirstPage");
		}
	}

}
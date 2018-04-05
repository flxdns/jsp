import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Deconnexion extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {


		HttpSession session = req.getSession(true);
		int droitUtil;
		try {
			droitUtil = (Integer) session.getAttribute("droit");
			session.invalidate();
		} catch (Exception e) {
			droitUtil = -1;
		}
		if ( droitUtil == -1) {
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
		dispatcher.forward(req, res);
	}
} //fin de la classe

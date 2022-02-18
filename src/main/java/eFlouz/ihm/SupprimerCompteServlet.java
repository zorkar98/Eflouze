package eFlouz.ihm;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import eFlouz.bll.UserManager;
import eFlouz.bo.User;

/**
 * Servlet implementation class SupprimerCompteServlet
 */
@WebServlet("/SupprimerCompteServlet")
public class SupprimerCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupprimerCompteServlet() {
		super();
// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = new User(session.getAttribute("user"));
		String email = user.getEmail();
		String mdp = user.getMot_de_passe();
		String confirmation = null;
		try {
			confirmation = UserManager.supprimerCompte(email, mdp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(confirmation);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// TODO Auto-generated method stub
		doGet(request, response);
	}
}

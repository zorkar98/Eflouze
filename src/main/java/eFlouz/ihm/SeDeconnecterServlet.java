package eFlouz.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TesterSessionInit
 */
@WebServlet("/Deconnexion")
public class SeDeconnecterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SeDeconnecterServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//Détruire la session
		HttpSession session = request.getSession(false); // récupérer une session existante
		if (session != null) {
			// Invalider l'ensemble de la session
			session.invalidate();

		}
		RequestDispatcher rd = request.getRequestDispatcher("/home");
		rd.forward(request, response);
	}
}

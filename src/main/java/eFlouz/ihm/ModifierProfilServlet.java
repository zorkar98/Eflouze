package eFlouz.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eFlouz.bll.UserManager;
import eFlouz.bo.User;

/**
 * Servlet implementation class modifierProfilServlet
 */
@WebServlet("/modifierProfil")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierProfilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String pseudo = request.getParameter("Pseudo");
		String nom = request.getParameter("Nom");
		String prenom = request.getParameter("Prenom");
		String email = request.getParameter("Email");
		String telephone = request.getParameter("Telephone");
		String rue = request.getParameter("Rue");
		int codePostal = Integer.parseInt(request.getParameter("CodePostal"));
		String ville = request.getParameter("Ville");
		String motDePasse = request.getParameter("MotDePasse");

		User userAvecModif = new User(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);

		User userSession = (User) session.getAttribute("user");
		
		
		try {
			UserManager.miseAJourProfil(userSession, userAvecModif);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

		doGet(request, response);
	}

}

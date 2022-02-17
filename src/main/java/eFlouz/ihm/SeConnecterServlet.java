package eFlouz.ihm;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import eFlouz.bll.User_manager;
import eFlouz.bo.User;
import eFlouz.dal.userDAOJDBCImpl;

/**
 * Servlet implementation class se_connecterServlet
 */
@WebServlet("/SeConnecterServlet")
public class SeConnecterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User_manager usermanager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SeConnecterServlet() {
		super();
// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/seConnecter.jsp");
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
		String email = request.getParameter("email");
		String mdp = request.getParameter("mot_de_passe");
		User_manager user_manager = new User_manager();
		boolean userOk = false;
		User_manager userValide = new User_manager();
		try {
			userOk = userValide.interrogerBase(email, mdp);
			if (userOk != true) {
				String couleur = "red";
				request.setAttribute("couleur", couleur);
			}
		} catch (Exception e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/se_connecter.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
			System.out.println("Servlet" + userOk);
		}
//Création de la session
		HttpSession session = request.getSession();
		String pseudoSession = null;
		String nomSession = null;
		String prenomSession = null;
		String emailSession = null;
		int telephoneSession = (Integer) null;
		String rueSession = null;
		int code_postalSession = (Integer) null;
		String villeSession = null;
		String mot_de_passeSession = null;
		User userSession = new User(pseudoSession, nomSession, prenomSession, emailSession, telephoneSession,
				rueSession, code_postalSession, villeSession, mot_de_passeSession);
		try {
			userSession = userDAOJDBCImpl.creationSession(email, mdp);
			session.setAttribute("pseudo", userSession.getPseudo());
			session.setAttribute("nom", userSession.getNom());
			session.setAttribute("prenom", userSession.getPrenom());
			session.setAttribute("email", userSession.getEmail());
			session.setAttribute("telephone", userSession.getTelephone());
			session.setAttribute("rue", userSession.getRue());
			session.setAttribute("code_postal", userSession.getCode_postal());
			session.setAttribute("ville", userSession.getVille());
			session.setAttribute("mot_de_passe", userSession.getMot_de_passe());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

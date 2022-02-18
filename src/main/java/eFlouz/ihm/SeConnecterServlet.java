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
import eFlouz.dal.UserDAOJDBCImpl;

/**
 * Servlet implementation class se_connecterServlet
 */
@WebServlet("/connection")
public class SeConnecterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserManager usermanager;



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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		String email = request.getParameter("email");
		String mdp = request.getParameter("mot_de_passe");

		UserManager user_manager = new UserManager();


		boolean userOk = false;
		UserManager userValide = new UserManager();
	
// Se Connecter à une session utilisateur		
		try {
			userOk = userValide.interrogerBase(email, mdp);
			if (userOk != true) {
				String couleur = "red";
				request.setAttribute("couleur", couleur);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/seConnecter.jsp");
				if (rd != null) {
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Création de la session
				HttpSession session = request.getSession();
				
				String pseudoSession = null;
				String nomSession = null;
				String prenomSession = null;
				String emailSession = null;
				float telephoneSession =  0;
				String rueSession = null;
				int code_postalSession =  0;
				String villeSession = null;
				String mot_de_passeSession = null;
				User userSession = new User(pseudoSession, nomSession, prenomSession, emailSession, telephoneSession,
						rueSession, code_postalSession, villeSession, mot_de_passeSession);
				try {
					userSession = UserDAOJDBCImpl.selectUserByEmailAndMdp(email, mdp);
					session.setAttribute("user", userSession);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			System.out.println(session.getAttribute("user"));
		
			RequestDispatcher rd = request.getRequestDispatcher("/home");
			if (rd != null) {
				rd.forward(request, response);
		}
	}
}

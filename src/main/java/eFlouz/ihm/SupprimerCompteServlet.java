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
 * Servlet implementation class SupprimerCompteServlet
 */
@WebServlet("/supprimerCompte")
public class SupprimerCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager deleteUserManager = new UserManager();
	int noUser;
	String pseudo;
	String nom;
	String prenom;
	String email;
	String tel;
	String rue;
	int codePostal;
	String ville;
	String mdp;
	int credit;
	User user = new User(noUser , pseudo, nom, prenom, email, tel, rue, codePostal, ville, mdp, credit);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		email = user.getEmail();
		mdp = user.getMotDePasse();
		System.out.println("ihm" + email + mdp);
		String confirmation = null;
		try {
			confirmation = deleteUserManager.supprimerCompte(email, mdp);
			if(confirmation != null) 
			{
			request.setAttribute("confirmation", confirmation);
			}
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
			
		
	}
}

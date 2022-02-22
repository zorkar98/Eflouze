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
 * Servlet implementation class MonProfil
 */
@WebServlet("/monProfil")
public class MonProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/creeProfil.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		User userSession = (User) session.getAttribute("user"); 
		
		if (userSession == null) {
		
		// Création de mon profil
		String pseudo = request.getParameter("Pseudo");
		String nom = request.getParameter("Nom");
		String prenom = request.getParameter("Prenom");
		String email = request.getParameter("Email");
		String telephone = request.getParameter("Telephone");
		String rue = request.getParameter("Rue");
		int codePostal = Integer.parseInt(request.getParameter("CodePostal"));
		String ville = request.getParameter("Ville");
		String motDePasse = request.getParameter("MotDePasse");
			
		UserManager user_manager = new UserManager();
		int presenceBase = user_manager.ajouterUser(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
		
		
		// Retour utilisateur sur succès ou echec et cause du rejet d'inscription
		String succes = null;
		String pseudoProfil = null;
		String emailProfil = null;
		String both = null;
		switch (presenceBase) {
		case 0 :  succes = "Votre compte à été ajouter avec succès";System.out.println(succes); break;
		case 1 :  pseudoProfil = "Le pseudo est déjà utilisé, Votre compte n'à pas été ajouter désolé"; break;
		case 2 :  emailProfil = "L'email est déjà utilisé, Votre compte n'à pas été ajouter désolé"; break;
		case 3 :  both = "Le pseudo et l'email sont déjà utilisés, vérifier si vous n'avez pas déjà un compte";
		}
			request.setAttribute("Succes", succes);
			request.setAttribute("Pseudo", pseudoProfil);
			request.setAttribute("Email", emailProfil);
			request.setAttribute("Both", both);
			System.out.println(presenceBase);
		doGet(request, response);
		
		}else {	
			
			RequestDispatcher rd = request.getRequestDispatcher("/modifierProfil");
			if (rd != null) {
				rd.forward(request, response);
			}
			
		}
	}	
		
}


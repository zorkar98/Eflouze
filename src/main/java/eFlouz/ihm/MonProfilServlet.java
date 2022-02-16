package eFlouz.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eFlouz.bll.User_manager;
import eFlouz.bo.User;

/**
 * Servlet implementation class MonProfil
 */
@WebServlet("/Profil")
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
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/monProfil.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String pseudo = request.getParameter("Pseudo");
		String nom = request.getParameter("Nom");
		String prenom = request.getParameter("Prenom");
		String email = request.getParameter("Email");
		float telephone = Float.parseFloat(request.getParameter("Telephone"));
		String rue = request.getParameter("Rue");
		int codePostal = Integer.parseInt(request.getParameter("CodePostal"));
		String ville = request.getParameter("Ville");
		String motDePasse = request.getParameter("MotDePasse");
			
		User newUser = User_manager.ajouterUser(pseudo, nom, prenom, email, telephone, rue, codePostal,ville,motDePasse);
		
		doGet(request, response);}
		
		
	}



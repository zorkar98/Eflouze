package eFlouz.ihm;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eFlouz.bll.EnchereManager;
import eFlouz.bo.Article;
import eFlouz.bo.Enchere;

/**
 * Servlet implementation class DetailVente
 */
@WebServlet("/detail")
public class DetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//TODO get les parametre dans application 
		LocalDate dateEnchere = LocalDate.now(); 
		int meilleurOffre = 0; 
		int noArticle = 0;
		int noUtilisateur = 0;
		
		//Nouvelle proposition recup dans formulaire
		int proposition = 0;
		
		
		proposition = Integer.parseInt(request.getParameter("proposition"));
	
			if (proposition > meilleurOffre) {
				EnchereManager enchereAjouter = new EnchereManager();
				enchereAjouter.ajouterEnchere(dateEnchere, proposition, noArticle, noUtilisateur);
				
			}
	}
}

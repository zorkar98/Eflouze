package eFlouz.ihm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eFlouz.bll.ArticleManager;
import eFlouz.bo.Article;
import eFlouz.bo.Enchere;
import eFlouz.bo.User;
import eFlouz.dal.EnchereDAOJBDCImpl;
import eFlouz.dal.UserDAOJDBCImpl;

/**
 * Servlet implementation class AfficherArticleServlet
 */
@WebServlet("/AfficherArticleServlet")
public class AfficherArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Article articleVendu = new Article ();

		int noArticle = Integer.parseInt(request.getParameter("id"));
			
		try {
			articleVendu = ArticleManager.afficherDetailsArticle (noArticle);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		Enchere meilleureEnchere = new Enchere ();
			
		try {
			meilleureEnchere = EnchereDAOJBDCImpl.selectEnchere(noArticle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		User vendeur = new User();
		int noUtilisateur = articleVendu.getNoUtilisateur();
		
		try {
			vendeur = UserDAOJDBCImpl.selectUserByNoUtilisateur (noUtilisateur);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String nomArticle = articleVendu.getNomArticle();
		request.setAttribute("nomArticle", nomArticle);	
		
		String description = articleVendu.getDescription();
		request.setAttribute("description", description);
		
		LocalDate dateFinEnchere = articleVendu.getDateFinEnchere();
		request.setAttribute("dateFinEnchere", dateFinEnchere);
		
		int montantMeilleureEnchere = meilleureEnchere.getMontantEnchere();
		request.setAttribute("montantMeilleureEnchere", montantMeilleureEnchere);
		
		int prixInitial = articleVendu.getPrixInitial();
		request.setAttribute("prixInitial", prixInitial);
		
		String rue = vendeur.getRue();
		request.setAttribute("rue", rue);
		
		int codePostal = vendeur.getCode_postal();
		request.setAttribute("codePostal", codePostal);
		
		String ville = vendeur.getVille();
		request.setAttribute("ville", ville);
		
		String pseudoVendeur = vendeur.getPseudo();
		request.setAttribute("pseudoVendeur", pseudoVendeur);
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/afficherDetailArticle.jsp");
		if(rd!=null) {
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

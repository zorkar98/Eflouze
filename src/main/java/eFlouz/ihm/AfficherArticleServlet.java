package eFlouz.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eFlouz.bll.EnchereManager;
import eFlouz.bo.Article;
import eFlouz.bo.User;
import eFlouz.dal.ArticleDAOJBDCImpl;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Article> listeArticleEnVente = new ArrayList<Article>();
		ArticleDAOJBDCImpl articleDao = new ArticleDAOJBDCImpl();

		LocalDate date = LocalDate.now();
		// Appel de la methode de ArticleManager pour récupérer les articles en ventes
		// ce jour
		try {
			listeArticleEnVente = articleDao.selectionnerArticleEnVente(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int noArticle = Integer.parseInt(request.getParameter("titre"));

		Article articleVendu = listeArticleEnVente.get(noArticle - 1);

		String nomArticle = articleVendu.getNomArticle();
		request.setAttribute("nomArticle", nomArticle);

		String description = articleVendu.getDescription();
		request.setAttribute("description", description);

		LocalDate dateFinEnchere = articleVendu.getDateFinEnchere();
		request.setAttribute("dateFinEnchere", dateFinEnchere);

		int categorie = articleVendu.getNoCategorie();
		request.setAttribute("categorie", categorie);

		int montantMeilleureEnchere = articleVendu.getPrixVente();
		request.setAttribute("montantMeilleureEnchere", montantMeilleureEnchere);

		int prixInitial = articleVendu.getPrixInitial();
		request.setAttribute("prixInitial", prixInitial);

//		String rue = articleVendu.getRue();
//		request.setAttribute("rue", rue);
//		
//		int codePostal = articleVendu.getCodePostal();
//		request.setAttribute("codePostal", codePostal);
//		
//		String ville = articleVendu.getVille();
//		request.setAttribute("ville", ville);

		String pseudoVendeur = articleVendu.getPseudo();
		request.setAttribute("pseudoVendeur", pseudoVendeur);

		HttpSession session = request.getSession();

		session.setAttribute("nomArticle", nomArticle);
		session.setAttribute("description", description);
		session.setAttribute("montantMeilleureEnchere", montantMeilleureEnchere);
		session.setAttribute("prixInitial", prixInitial);
		session.setAttribute("vendeur", pseudoVendeur);
		session.setAttribute("noArticle", noArticle);
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/afficherDetailArticle.jsp");
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
		
		HttpSession session = request.getSession();

		User userSession = (User) session.getAttribute("user");
		
		
		LocalDate dateEnchere = LocalDate.now();
		int prixInitial = (int) session.getAttribute("prixInitial");
		int noArticle = (int) session.getAttribute("noArticle");
		int noUtilisateur = userSession.getNoUtilisateur();

		int proposition = 0;
		proposition = Integer.parseInt(request.getParameter("proposition"));
		session.setAttribute("proposition", proposition);
		
		if (proposition > prixInitial) {
		EnchereManager enchereAjouter = new EnchereManager();
		enchereAjouter.ajouterEnchere(dateEnchere, proposition, noArticle, noUtilisateur);
		}
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("remporterVente");
		if(rd != null) {
			rd.forward(request, response);
		}
	}

}

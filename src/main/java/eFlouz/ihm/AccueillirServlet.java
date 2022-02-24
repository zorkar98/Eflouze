package eFlouz.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eFlouz.bo.Article;
import eFlouz.dal.ArticleDAOJBDCImpl;

import eFlouz.bll.ArticleManager;
import eFlouz.bo.Article;
import eFlouz.dal.ArticleDAOJBDCImpl;

/**
 * Servlet implementation class AccueillirServlet
 */
@WebServlet("/home")
public class AccueillirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueillirServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleDAOJBDCImpl articleDao = new ArticleDAOJBDCImpl();
		List<Article> listeArticles = new ArrayList<Article>();
		
		try {
			
			listeArticles = articleDao.selectArticleVendus();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("listeArticles", listeArticles);
		for (Article article : listeArticles) {
			System.out.println(article.getNomArticle());
		}
		
		System.out.println(listeArticles);
		
		request.getParameter("titre");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		if (rd != null) {
		rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Article> listeArticleEnVente = new ArrayList<Article>();
		ArticleDAOJBDCImpl articleDao = new ArticleDAOJBDCImpl();
		
		LocalDate date = LocalDate.now();
		// Appel de la methode de ArticleManager pour récupérer les articles en ventes ce jour
		try {
			listeArticleEnVente = articleDao.selectionnerArticleEnVente(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Placer les article en vente dans un context d'application
		request.setAttribute("liste",listeArticleEnVente);
		System.out.println(listeArticleEnVente.toString());
		
		doGet(request, response);
	}
}

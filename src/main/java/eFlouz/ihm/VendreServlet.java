package eFlouz.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eFlouz.bll.ArticleManager;
import eFlouz.bo.User;

/**
 * Servlet implementation class VendreServlet
 */
@WebServlet("/vendre")
public class VendreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/article.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);	
		
		//Recuperer les infos de l'article
		
		String nomArticle = request.getParameter("nomArticle").trim();
		String description =  request.getParameter("description").trim();
		System.out.println("Description " +description);
		int noCategorie = Integer.parseInt(request.getParameter("categorie"));
		int prix = Integer.parseInt(request.getParameter("prix"));
		String debutDateStr = request.getParameter("debutDate");
		LocalDate debutDate = LocalDate.parse(debutDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
		String finDateStr = request.getParameter("finDate");
		LocalDate finDate = LocalDate.parse(finDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
		
		//Recuperer noUtilisateur dans session
		User userSession = (User)session.getAttribute("user");
		int noUtilisateur = userSession.getNoUtilisateur();
		
		//TODO ajouter l'adresse dans article 
		//String rue = request.getParameter("rue");
		//int codePostal = Integer.parseInt(request.getParameter("codePostal"));
		//String ville = request.getParameter("ville");
		
		//Création de l'articleManager avec nos infos
		ArticleManager articleMng = new ArticleManager();
		try {
			articleMng.ajouterArticle(nomArticle, description, debutDate, finDate, prix, noUtilisateur, noCategorie);
			request.setAttribute("flag",true);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

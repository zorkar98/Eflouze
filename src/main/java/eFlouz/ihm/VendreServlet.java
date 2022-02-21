package eFlouz.ihm;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eFlouz.bll.ArticleManager;

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
		HttpSession session = request.getSession();
		
		String nomArticle = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		int prix = Integer.parseInt(request.getParameter("prix"));
		//DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yy/mm/dd");
		LocalDate debutDate = LocalDate.parse(request.getParameter("debutDate"));
		LocalDate finDate = LocalDate.parse(request.getParameter("finDate"));
		
		//TODO ajouter noUtilisateur dans session
		int noUtilisateur = 0;
		
		
		//String rue = request.getParameter("rue");
		//String codePostal = request.getParameter("codePostal");
		//String ville = request.getParameter("ville");
		ArticleManager articleMng = new ArticleManager();
		try {
			articleMng.ajouterArticle(nomArticle, description, debutDate, finDate, prix, noUtilisateur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rd != null) {
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

package eFlouz.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eFlouz.bll.User_manager;

/**
 * Servlet implementation class se_connecterServlet
 */
@WebServlet("/SeConnecterServlet")
public class SeConnecterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private  User_manager usermanager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeConnecterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/seConnecter.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String email = request.getParameter("email");
		String mdp = request.getParameter("mot_de_passe");
		User_manager user_manager = new User_manager();
		boolean userOk = false; 
		User_manager userValide = new User_manager();
		try {
			userOk = userValide.interrogerBase(email,mdp);
			if(userOk != true)
			{
				String couleur = "red";
				request.setAttribute("couleur", couleur);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/seConnecter.jsp");
			if (rd != null) 
			{
			rd.forward(request, response);
			}
			System.out.println("Servlet" + userOk);
		}

	}
}


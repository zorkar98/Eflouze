package eFlouz.bll;

import java.time.LocalDate;

import eFlouz.bo.Article;
import eFlouz.dal.ArticleDAOJBDCImpl;

public class ArticleManager {

	ArticleDAOJBDCImpl articleDao = new ArticleDAOJBDCImpl();

	// Fonction de création d'une nouvelle vente
	public void ajouterArticle(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int prixInitial, int noUtilisateur, int noCategorie) throws Exception {

		Article articleAAjouter = new Article(nomArticle, description, dateDebutEncheres, dateFinEncheres,
				prixInitial, noUtilisateur, noCategorie);

		articleDao.insertArticle(articleAAjouter);

	}
	
	//Fonction de réccupération des détails d'un article
	public static Article afficherDetailsArticle (int noArticle) throws Exception {
		
		Article articleVendu = new Article ();
		
		try {
			articleVendu = ArticleDAOJBDCImpl.selectArticle (noArticle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return articleVendu ;
	}
	

}

package eFlouz.bll;

import java.time.LocalDate;

import eFlouz.bo.Article;
import eFlouz.dal.ArticleDAOJBDCImpl;

public class ArticleManager {
	
	ArticleDAOJBDCImpl articleDao = new ArticleDAOJBDCImpl();

	
	//Fonction de création d'une nouvelle vente
	public void ajouterArticle ( int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial, int noUtilisateur, int noCategorie) throws Exception {
		
		Article articleAAjouter = new Article (noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, noUtilisateur, noCategorie);
		
		ArticleDAOJBDCImpl articleDAOJBDCImpl = new ArticleDAOJBDCImpl();
			articleDAOJBDCImpl.insertArticle(articleAAjouter);

	}
	
	
	
	
	
	
}

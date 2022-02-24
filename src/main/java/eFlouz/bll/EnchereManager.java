package eFlouz.bll;

import java.sql.SQLException;
import java.time.LocalDate;

import eFlouz.bo.Enchere;
import eFlouz.dal.ArticleDAOJBDCImpl;
import eFlouz.dal.EnchereDAOJDBCImpl;

public class EnchereManager {

	EnchereDAOJDBCImpl enchereDao = new EnchereDAOJDBCImpl();
	
	public void ajouterEnchere(LocalDate dateEnchere, int proposition, int noArticle, int noUtilisateur) {
	
	Enchere enchereAAjouter = new Enchere(dateEnchere, proposition, noArticle, noUtilisateur);
	
	try {
		enchereDao.insererEnchere(enchereAAjouter);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	
	public static void connaitreEnchereMax (int noArticle) throws Exception {

		Enchere meilleureEnchere = new Enchere ();
		
		meilleureEnchere = EnchereDAOJDBCImpl.selectMaxByNoArticle (noArticle);
		
		System.out.println(meilleureEnchere);
		
		int prixVente;
		
		prixVente = meilleureEnchere.getMontantEnchere();
		
		System.out.println("prix :" + prixVente);
		
		ArticleDAOJBDCImpl.insertPrixVente (prixVente, noArticle);
	}
	
	
	
}
	

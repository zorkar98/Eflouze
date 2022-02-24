package eFlouz.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import eFlouz.bo.Article;
import eFlouz.bo.Enchere;

public class EnchereDAOJBDCImpl {

	private static final String SELECT_ENCHERE_BY_NO_ARTICLE = "SELECT * FROM ARTICLES WHERE no_article = ? ";

	
	public static Enchere selectEnchere (int noArticle) throws Exception {
		
		Enchere enchereMax = new Enchere ();
		
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(SELECT_ENCHERE_BY_NO_ARTICLE);
		
		rqt.setInt(1, noArticle);
		
		ResultSet rs = rqt.executeQuery();
		rs.next();
		
		LocalDate dateEnchere = (rs.getDate("date_enchere")).toLocalDate();
		
		enchereMax.setNoEnchere(rs.getInt("no_enchere"));
		enchereMax.setDateEnchere(dateEnchere);
		enchereMax.setNoEnchere(rs.getInt("montant_enchere"));
		enchereMax.setNoArticle(rs.getInt("no_article"));
		enchereMax.setNoArticle(rs.getInt("no_utilisateur"));
		
		return enchereMax;
	}
	
}

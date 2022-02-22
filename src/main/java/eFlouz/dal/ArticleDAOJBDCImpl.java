package eFlouz.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import eFlouz.bo.Article;

public class ArticleDAOJBDCImpl {

	private static final String INSERT_ARTICLE = "INSERT into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) "
			+ "VALUES (?,?,?,?,?,?,?)";

	public void insertArticle(Article articleAjoute) throws Exception {
		// Connection + Requete INSERT avec IDENTITY KEY
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
		//Passage de LocalDate (java) à Date (sql)
		java.sql.Date sqlDateDebutEnchere = java.sql.Date.valueOf(articleAjoute.getDateDebutEnchere());
		java.sql.Date sqlDateFinEnchere = java.sql.Date.valueOf(articleAjoute.getDateFinEnchere());

		// crï¿½ation article ï¿½ ajouter
		System.out.println("manager description +" +articleAjoute.getDescription());
		rqt.setString(1, articleAjoute.getNomArticle());
		rqt.setString(2, articleAjoute.getDescription());
		rqt.setDate(3, sqlDateDebutEnchere);
		rqt.setDate(4, sqlDateFinEnchere);
		rqt.setInt(5, articleAjoute.getPrixInitial());
		rqt.setInt(6, articleAjoute.getNoUtilisateur());
		rqt.setInt(7, articleAjoute.getNoCategorie());
		
		// Verifie si des lignes ont ï¿½tï¿½ ajoutï¿½es, SI OUI -> genere KEY identity
		// et ajoute ï¿½ l'article insï¿½rï¿½
		int numberAffectedLine = rqt.executeUpdate();
		if (numberAffectedLine > 0) {
			ResultSet rs = rqt.getGeneratedKeys();
			if (rs.next()) {
				articleAjoute.setNoArticle(rs.getInt(1));
			}
		}
		cnx.close();
	}
}

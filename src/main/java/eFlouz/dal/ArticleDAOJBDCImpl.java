package eFlouz.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import eFlouz.bo.Article;

public class ArticleDAOJBDCImpl {

	private static final String INSERT_ARTICLE = "INSERT into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) "
			+ "VALUES (?,?,?,?,?,?,?)";

	private static final String SELECT_ARTICLE_BY_NO_ARTICLE = "SELECT * FROM ARTICLES WHERE no_article = ? ";

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
	
	public static Article selectArticle (int noArticle) throws Exception {
		
		Article articleVendu = new Article ();
		
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(SELECT_ARTICLE_BY_NO_ARTICLE);
		
		rqt.setInt(1, noArticle);
		
		ResultSet rs = rqt.executeQuery();
		rs.next();
		
		LocalDate dateDebutEnchere = (rs.getDate("date_debut_encheres")).toLocalDate();
		LocalDate dateFinEnchere = (rs.getDate("date_fin_encheres")).toLocalDate();
		
		
		articleVendu.setNoArticle(rs.getInt("no_article"));
		articleVendu.setNomArticle(rs.getString("nom_article"));
		articleVendu.setDescription(rs.getString("description"));
		articleVendu.setDateDebutEnchere(dateDebutEnchere);
		articleVendu.setDateFinEnchere(dateFinEnchere);
		articleVendu.setPrixInitial(rs.getInt("prix_initial"));
		articleVendu.setNoUtilisateur(rs.getInt("no_utilisateur"));
		articleVendu.setNoCategorie(rs.getInt("categorie"));
		
		return articleVendu;
		
	}
	
}

package eFlouz.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eFlouz.bo.Article;

public class ArticleDAOJBDCImpl {

	private static final String INSERT_ARTICLE = "INSERT into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) "
			+ "VALUES (?,?,?,?,?,?,?)";

	private static final String SELECT_ARTICLE_EN_VENTE = "SELECT no_article, nom_article,description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente,ARTICLES_VENDUS.no_utilisateur, no_categorie,  UTILISATEURS.no_utilisateur as utilisateurnb, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS  ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE date_debut_encheres <= ? and date_fin_encheres >= ?";
	
	private static final String SELECT_ARTICLE_VENDUS = "SELECT * FROM ARTICLES_VENDUS";
		
	
	
	public List<Article> selectArticleVendus() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(SELECT_ARTICLE_VENDUS);
		
		List<Article> listeArticles = new ArrayList<Article>();
		
		ResultSet rs = rqt.executeQuery();
		rs.next();
		
		
		int noArticle = 0;
		String nomArticle = null;
		String description = null;
		int prixInitial = 0; 
		int noUtilisateur = 0;
		int noCategorie = 0;
		
		do {
			Article articleVendus = new Article(noArticle,  nomArticle,  description,  prixInitial,  noUtilisateur,  noCategorie);
		
			articleVendus.setNoArticle(rs.getInt("no_article")); ;
			articleVendus.setNomArticle(rs.getString("nom_article"));
			articleVendus.setDescription(rs.getString("description"));
			articleVendus.setPrixInitial(rs.getInt("prix_initial"));
			articleVendus.setNoUtilisateur(rs.getInt("no_utilisateur"));
			articleVendus.setNoCategorie(rs.getInt("no_categorie"));
			
			
			listeArticles.add(articleVendus);
			
		}while(rs.next());
		return listeArticles;
	}
	
	public void insertArticle(Article articleAjoute) throws Exception {
		// Connection + Requete INSERT avec IDENTITY KEY
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
		//Passage de LocalDate (java) à Date (sql)
		java.sql.Date sqlDateDebutEnchere = java.sql.Date.valueOf(articleAjoute.getDateDebutEnchere());
		java.sql.Date sqlDateFinEnchere = java.sql.Date.valueOf(articleAjoute.getDateFinEnchere());
		
		// crï¿½ation article ï¿½ ajouter
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

	// Recherche des articles en vente aujourd'hui
	public List<Article> selectionnerArticleEnVente (LocalDate date) throws Exception {
		List<Article>listeArticleEnVente = new ArrayList<>(); 
		int noArticle = 0;
		String nomArticle = null;
		String description = null;
		LocalDate dateDebutEncheres = LocalDate.now();
		LocalDate dateFinEncheres= LocalDate.now();
		int prixInitial = 0;
		int prixVente = prixInitial;
		int noUtilisateur = 0;
		int noCategorie = 0; 
		
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(SELECT_ARTICLE_EN_VENTE);
		// création article a incrementer dans la requete
		java.sql.Date sqldateDebutEncheres = java.sql.Date.valueOf(dateDebutEncheres); 
		java.sql.Date sqldateFinEncheres = java.sql.Date.valueOf(dateFinEncheres); 
		rqt.setDate(1,sqldateDebutEncheres);
		rqt.setDate(2, sqldateFinEncheres);
		ResultSet rs = rqt.executeQuery();
		rs.next();
		do
		{	Article article = new Article(noArticle, nomArticle,description, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente, noUtilisateur, noCategorie );
			LocalDate dateDebutEnchereJ = (rs.getDate("date_debut_encheres")).toLocalDate();
			LocalDate dateFinEnchereJ = (rs.getDate("date_fin_encheres")).toLocalDate();
			
			article.setNoArticle(rs.getInt("no_article"));
			article.setNomArticle(rs.getString("nom_article"));
			article.setDescription(rs.getString("description"));
			article.setDateDebutEnchere(dateDebutEnchereJ);
			article.setDateFinEnchere(dateFinEnchereJ);
			article.setPrixInitial(rs.getInt("prix_initial"));
			article.setPrixVente(rs.getInt("prix_vente"));
			article.setNoUtilisateur(rs.getInt("no_utilisateur"));
			article.setNoCategorie(rs.getInt("no_categorie"));
			article.setPseudo(rs.getString("pseudo"));
			listeArticleEnVente.add(article);
			System.out.println(article);
		}while (rs.next());
			
		System.out.println("DAO "+ listeArticleEnVente);
		
		return listeArticleEnVente;
	}
}
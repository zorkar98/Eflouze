package eFlouz.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eFlouz.bo.Enchere;

public class EnchereDAOJDBCImpl {
	
	//Ordre sql insert ENCHERE
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES VALUES (?,?,?,?)";

	//Fonction inserer enchere return Enchere a ajouter
	public void insererEnchere(Enchere enchereAjoute) throws SQLException {
		
		//Creation de la connection + rqt INSERT
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS);
		
		java.sql.Date sqlDateEnchere = java.sql.Date.valueOf(enchereAjoute.getDateEnchere());
		
		//Ajout des parametres dans l'objet Enchere à ajouter
		rqt.setDate(1, sqlDateEnchere);
		rqt.setInt(2, enchereAjoute.getMontantEnchere());
		rqt.setInt(3, enchereAjoute.getNoArticle());
		rqt.setInt(4, enchereAjoute.getNoUtilisateur());
		
		//Execute ordre + si il y a bien une ligne de créée -> ajoute la clé IDENTITY
		int numberAffectedLine = rqt.executeUpdate();
		if (numberAffectedLine > 0) {
			ResultSet rs = rqt.getGeneratedKeys();
			if (rs.next()) {
				enchereAjoute.setNoArticle(rs.getInt(1));
			}
		}
		cnx.close();
	}
}

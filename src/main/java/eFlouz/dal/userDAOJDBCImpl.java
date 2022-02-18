
package eFlouz.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import eFlouz.bo.User;

public class userDAOJDBCImpl {
	boolean flag = false;
	private static final String SELECT = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur "
			+ "FROM [UTILISATEURS] " + "WHERE (email = ? and mot_de_passe = ?)";
	private static final String SELECT_BY_PSEUDO_AND_EMAIL = "SELECT pseudo, email FROM UTILISATEURS WHERE (pseudo=? OR email=?)";
	private static final String INSERT = "INSERT into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe,credit, administrateur) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

//Méthode de création d'un user à stocker dans la session
	public static User creationSession(String email, String mot_de_passe) throws Exception {
		String pseudo = null;
		String nom = null;
		String prenom = null;
		int telephone = 0;
		String rue = null;
		int code_postal =  0;
		String ville = null;
		User userSession = new User(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe);
//Connection + Requete SELECT WHERE email = ? and mot_de_passe = ?
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(SELECT);
		rqt.setString(1, userSession.getEmail());
		rqt.setString(2, userSession.getMot_de_passe());
		ResultSet rs = rqt.executeQuery();
		rs.next();
//Attribution des donnée réccupérées avec le SELECT à notre user
		userSession.setPseudo(rs.getString("pseudo"));
		userSession.setNom(rs.getString("nom"));
		userSession.setPrenom(rs.getString("prenom"));
		userSession.setEmail(rs.getString("email"));
		userSession.setTelephone(rs.getInt("telephone"));
		userSession.setRue(rs.getString("rue"));
		userSession.setCode_postal(rs.getInt("code_postal"));
		userSession.setVille(rs.getString("ville"));
		userSession.setMot_de_passe(rs.getString("mot_de_passe"));
		return userSession;
	}

	public void ajouterUser(User userAjoute) throws Exception {
// Connection + Requete INSERT avec IDENTITY KEY
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
//crï¿½ation user ï¿½ ajouter
		rqt.setString(1, userAjoute.getPseudo());
		rqt.setString(2, userAjoute.getNom());
		rqt.setString(3, userAjoute.getPrenom());
		rqt.setString(4, userAjoute.getEmail());
		rqt.setFloat(5, userAjoute.getTelephone());
		rqt.setString(6, userAjoute.getRue());
		rqt.setInt(7, userAjoute.getCode_postal());
		rqt.setString(8, userAjoute.getVille());
		rqt.setString(9, userAjoute.getMot_de_passe());
		rqt.setInt(10, 100);
		rqt.setBoolean(11, false);
//Verifie si des lignes ont ï¿½tï¿½ ajoutï¿½es, SI OUI -> genere KEY identity et ajoute ï¿½ l'user insï¿½rï¿½
		int numberAffectedLine = rqt.executeUpdate();
		if (numberAffectedLine > 0) {
			ResultSet rs = rqt.getGeneratedKeys();
			if (rs.next()) {
				userAjoute.setNo_utilisateur(rs.getInt(1));
			}
		}
	}

//
	public int selectByPseudoAndEmail(String pseudo, String email) throws Exception {
// Connection + Requete SELECT PSEUDO ET EMAIL
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(SELECT_BY_PSEUDO_AND_EMAIL);
//Creation requete avec pseudo et email en parametre de methode
		rqt.setString(1, pseudo);
		rqt.setString(2, email);
		ResultSet rs = rqt.executeQuery();
		int presenceEnBase = 0;
		System.out.println("resulset : " + presenceEnBase);
//VERIFIER si l'email ou/et le pseudo est/sont trouvï¿½(s)
		rs.next();
		if (rs.getString("pseudo").trim().equals(pseudo.trim())) {
			presenceEnBase++;
		}
		if (rs.getString("email").trim().equals(email.trim())) {
			presenceEnBase += 2;
		}
		if (rs.getString("pseudo").trim().equals(pseudo.trim()) && rs.getString("email").trim().equals(email.trim())) {
			presenceEnBase = 3;
		}
		System.out.println("resulset2 : " + presenceEnBase);
// SI presence en base = 0 -> rien trouvï¿½
// SI presence en base = 1 -> pseudo identique
// SI presence en base = 2 -> email identique
// SI presence en base = 3 -> les deux identiques
		return presenceEnBase;
	}

	public boolean seConnecter(User user) throws Exception {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(SELECT);
		rqt.setString(1, user.getEmail());
		rqt.setString(2, user.getMot_de_passe());
		ResultSet rs = rqt.executeQuery();
		rs.next();
		try {
			if (rs.getString(5).trim().equals(user.getEmail().trim()))
				;
			{
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("JDBC" + flag);
		System.out.println(user.getEmail());
		System.out.println(user.getMot_de_passe());
		System.out.println(rs.getString(5));
		return flag;
	}
}


package eFlouz.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import eFlouz.bo.User;

public class UserDAOJDBCImpl {
	boolean flag = false;
	private static final String SELECT_USER = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur "
			+ "FROM [UTILISATEURS] " + "WHERE (email = ? and mot_de_passe = ?)";
	private static final String SELECT_USER_BY_PSEUDO_AND_EMAIL = "SELECT pseudo, email FROM UTILISATEURS WHERE (pseudo=? OR email=?)";
	private static final String INSERT_USER = "INSERT into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe,credit, administrateur) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_USER_BY_EMAIL_AND_MDP = "DELETE * FROM [UTILISATEURS] " + "WHERE (email = ? and mot_de_passe = ?)";

//Méthode de création d'un user à stocker dans la session
	public static User selectUserByEmailAndMdp(String email, String mot_de_passe) throws Exception {
		int noUtilisateur = 0;
		String pseudo = null;
		String nom = null;
		String prenom = null;
		String telephone = null;
		String rue = null;
		int code_postal =  0;
		String ville = null;
		int credit = 0;
		User userSession = new User(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit);
//Connection + Requete SELECT WHERE email = ? and mot_de_passe = ?
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(SELECT_USER);
		rqt.setString(1, userSession.getEmail());
		rqt.setString(2, userSession.getMot_de_passe());
		ResultSet rs = rqt.executeQuery();
		rs.next();
//Attribution des donnée récupérées avec le SELECT à notre user
		userSession.setNo_utilisateur(rs.getInt("no_utilisateur"));
		userSession.setPseudo(rs.getString("pseudo"));
		userSession.setNom(rs.getString("nom"));
		userSession.setPrenom(rs.getString("prenom"));
		userSession.setEmail(rs.getString("email"));
		userSession.setTelephone(rs.getString("telephone"));
		userSession.setRue(rs.getString("rue"));
		userSession.setCode_postal(rs.getInt("code_postal"));
		userSession.setVille(rs.getString("ville"));
		userSession.setMot_de_passe(rs.getString("mot_de_passe"));
		userSession.setCredit(rs.getInt("credit"));
		cnx.close();

		return userSession;
	}

	public void insertUser(User userAjoute) throws Exception {
// Connection + Requete INSERT avec IDENTITY KEY
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
//crï¿½ation user ï¿½ ajouter
		rqt.setString(1, userAjoute.getPseudo());
		rqt.setString(2, userAjoute.getNom());
		rqt.setString(3, userAjoute.getPrenom());
		rqt.setString(4, userAjoute.getEmail());
		rqt.setString(5, userAjoute.getTelephone());
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
		cnx.close();
	}

//
	public int selectUserByPseudoAndEmail(String pseudo, String email) throws Exception {
// Connection + Requete SELECT PSEUDO ET EMAIL
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(SELECT_USER_BY_PSEUDO_AND_EMAIL);
//Creation requete avec pseudo et email en parametre de methode
		rqt.setString(1, pseudo);
		rqt.setString(2, email);
		ResultSet rs = rqt.executeQuery();
		int presenceEnBase = 0;
		System.out.println("resulset : " + presenceEnBase);
//VERIFIER si l'email ou/et le pseudo est/sont trouvï¿½(s)
		if (rs.next()) {
		do {
		if (rs.getString("pseudo").trim().equals(pseudo.trim())){
		presenceEnBase= presenceEnBase+1;
		System.out.println("resultset pseudo " + presenceEnBase);
		}
		else if (rs.getString("email").trim().equals(email.trim())){
		presenceEnBase= presenceEnBase+2;
		System.out.println("resultset email " + presenceEnBase);
		}
		else if (rs.getString("pseudo").trim().equals(pseudo.trim()) && rs.getString("email").trim().equals(email.trim())) {
		presenceEnBase = 3;
		}
		}while(rs.next());
		}
		System.out.println("resulset2 : " + presenceEnBase);
// SI presence en base = 0 -> rien trouvï¿½
// SI presence en base = 1 -> pseudo identique
// SI presence en base = 2 -> email identique
// SI presence en base = 3 -> les deux identiques
		cnx.close();
		return presenceEnBase;
	}

	public boolean selectUser(User user) throws Exception {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(SELECT_USER);
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
		cnx.close();
		return flag;
	}
	//Fonction de suppression de compte à partir de l'email et du mot de passse
	public static String deleteUserByEmailAndMdp(String email, String mot_de_passe) throws Exception {

	String confirmation;

	// Connection + Requete DELETE EMAIL & MDP
	Connection cnx = ConnectionProvider.getConnection();
	PreparedStatement rqt = cnx.prepareStatement(DELETE_USER_BY_EMAIL_AND_MDP);
	//Creation requete avec email et mot_de_passe en parametre de methode
	rqt.setString(1, email);
	rqt.setString(2, mot_de_passe);

	confirmation = "Votre compte à bien été supprimé !";

	cnx.close();
	return confirmation;
	}
}

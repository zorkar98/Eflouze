
package eFlouz.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import eFlouz.bo.Enchere;
import eFlouz.bo.User;

public class UserDAOJDBCImpl {
	boolean flag = false;
	private static final String SELECT_USER = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur "
			+ "FROM [UTILISATEURS] " + "WHERE (email = ? and mot_de_passe = ?)";
	private static final String SELECT_USER_BY_PSEUDO_AND_EMAIL = "SELECT pseudo, email FROM UTILISATEURS WHERE (pseudo=? OR email=?)";
	private static final String INSERT_USER = "INSERT into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe,credit, administrateur) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_USER_BY_EMAIL_AND_MDP = "DELETE FROM [UTILISATEURS] WHERE email = ? and mot_de_passe = ?";

	private static final String UPDATE_USER_BY_NO_UTILISATEUR = "UPDATE [UTILISATEURS] SET pseudo = ?, nom = ?, prenom = ? ,email = ? ,telephone = ? , rue = ? , code_postal = ? , ville  = ?, mot_de_passe = ? WHERE no_utilisateur = ? ";
	
	private static final String SELECT_VENDEUR = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo = ? ";
//	private static final String SELECT_USER_BY_NO_UTILISATEUR = "SELECT * FROM ARTICLES WHERE no_utilisateur = ? ";
	
	//UPDATE UTILISATEURS SET pseudo ='Zorkar' , nom ='Quere', prenom = 'Clement' , email = 'clement.quere2021@campus-eni.fr' , telephone = '0652281966', rue = '2 Rue du Vicomte De La Cassecouillerie', code_postal =  44830, ville = 'Bouaye', mot_de_passe = 'azerty' WHERE no_utilisateur = 1;
	
//Méthode de création d'un user à stocker dans la session
	public static User selectUserByEmailAndMdp(String email, String motDePasse) throws Exception {
		int noUtilisateur = 0;
		String pseudo = null;
		String nom = null;
		String prenom = null;
		String telephone = null;
		String rue = null;
		int codePostal =  0;
		String ville = null;
		int credit = 0;
		User userSession = new User(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit);
//Connection + Requete SELECT WHERE email = ? and mot_de_passe = ?
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(SELECT_USER);
		rqt.setString(1, userSession.getEmail());
		rqt.setString(2, userSession.getMotDePasse());
		ResultSet rs = rqt.executeQuery();
		rs.next();
//Attribution des donnée récupérées avec le SELECT à notre user
		userSession.setNoUtilisateur(rs.getInt("no_utilisateur"));
		userSession.setPseudo(rs.getString("pseudo"));
		userSession.setNom(rs.getString("nom"));
		userSession.setPrenom(rs.getString("prenom"));
		userSession.setEmail(rs.getString("email"));
		userSession.setTelephone(rs.getString("telephone"));
		userSession.setRue(rs.getString("rue"));
		userSession.setCodePostal(rs.getInt("code_postal"));
		userSession.setVille(rs.getString("ville"));
		userSession.setMotDePasse(rs.getString("mot_de_passe"));
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
		rqt.setInt(7, userAjoute.getCodePostal());
		rqt.setString(8, userAjoute.getVille());
		rqt.setString(9, userAjoute.getMotDePasse());
		rqt.setInt(10, 100);
		rqt.setBoolean(11, false);
//Verifie si des lignes ont ï¿½tï¿½ ajoutï¿½es, SI OUI -> genere KEY identity et ajoute ï¿½ l'user insï¿½rï¿½
		int numberAffectedLine = rqt.executeUpdate();
		if (numberAffectedLine > 0) {
			ResultSet rs = rqt.getGeneratedKeys();
			if (rs.next()) {
				userAjoute.setNoUtilisateur(rs.getInt(1));
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
		rqt.setString(2, user.getMotDePasse());
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
		System.out.println(user.getMotDePasse());
		System.out.println(rs.getString(5));
		cnx.close();
		return flag;
	}
	//Fonction de suppression de compte à partir de l'email et du mot de passse
	public String deleteUserByEmailAndMdp(String email, String motDePasse) throws Exception {
		System.out.println("dao " + email);
		System.out.println("dao " + motDePasse);
	String confirmation = null;

	// Connection + Requete DELETE EMAIL & MDP
	Connection cnx = ConnectionProvider.getConnection();
	PreparedStatement rqt = cnx.prepareStatement(DELETE_USER_BY_EMAIL_AND_MDP);
	//Creation requete avec email et mot_de_passe en parametre de methode
	rqt.setString(1, email);
	rqt.setString(2, motDePasse);
	int rs = rqt.executeUpdate();
	
	if(rs == 1) {
	confirmation = "Votre compte à bien été supprimé !";
	}
	cnx.close();
	System.out.println(confirmation);
	return confirmation;

	}
	
	
	//Fonction de mise à jour du profile utilisateur
	public static void updateUserByNoUtilisateur(User userAModifier) throws Exception {
		
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(UPDATE_USER_BY_NO_UTILISATEUR);
		
		System.out.println(userAModifier.getNoUtilisateur());
		System.out.println(userAModifier.getPseudo());
		System.out.println(userAModifier.getNom());
		System.out.println(userAModifier.getPrenom());
		System.out.println(userAModifier.getEmail());
		System.out.println(userAModifier.getTelephone());
		
		
		
		rqt.setString(1, userAModifier.getPseudo());
		rqt.setString(2, userAModifier.getNom());
		rqt.setString(3, userAModifier.getPrenom());
		rqt.setString(4, userAModifier.getEmail());
		rqt.setString(5, userAModifier.getTelephone());
		rqt.setString(6, userAModifier.getRue());
		rqt.setInt(7, userAModifier.getCodePostal());
		rqt.setString(8, userAModifier.getVille());
		rqt.setString(9, userAModifier.getMotDePasse());
		rqt.setInt(10, userAModifier.getNoUtilisateur());

		
		rqt.executeUpdate();
		
		cnx.close();
	}
	//Méthode de création d'un user à stocker dans la session
		public static User selectInfoVendeur(String vendeur) throws Exception {
			System.out.println("vendeur dal " + vendeur);
			int noUtilisateur = 0;
			String pseudo = null;
			String nom = null;
			String prenom = null;
			String email = null;
			String telephone = null;
			String rue = null;
			int codePostal =  0;
			String ville = null;
			String motDePasse = null;
			int credit = 0;
			User utilisateur = new User(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit);
	//Connection + Requete SELECT WHERE pseudo = ? 
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement rqt = cnx.prepareStatement(SELECT_VENDEUR);
			rqt.setString(1, vendeur);
			ResultSet rs = rqt.executeQuery();
			rs.next();
	//Attribution des donnée récupérées avec le SELECT à notre user
			utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			utilisateur.setPseudo(rs.getString("pseudo"));
			utilisateur.setNom(rs.getString("nom"));
			utilisateur.setPrenom(rs.getString("prenom"));
			utilisateur.setEmail(rs.getString("email"));
			utilisateur.setTelephone(rs.getString("telephone"));
			utilisateur.setRue(rs.getString("rue"));
			utilisateur.setCodePostal(rs.getInt("code_postal"));
			utilisateur.setVille(rs.getString("ville"));
			utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
			utilisateur.setCredit(rs.getInt("credit"));
			cnx.close();
			System.out.println("vendeur dal" + utilisateur.toString());
			return utilisateur;
		}
	
//	public static User selectUserByNoUtilisateur (int noUtilisateur) throws SQLException {
//		
//		User user = new User ();
//		
//		Connection cnx = ConnectionProvider.getConnection();
//		PreparedStatement rqt = cnx.prepareStatement(SELECT_USER_BY_NO_UTILISATEUR);
//		
//		rqt.setInt(1, noUtilisateur);
//		
//		ResultSet rs = rqt.executeQuery();
//		rs.next();
//		
//		user.setRue(rs.getString("rue"));
//		user.setCodePostal(rs.getInt("code_postal"));
//		user.setVille(rs.getString("ville"));
//		user.setPseudo(rs.getString("pseudo"));
//		
//		return user;
//	}
//	
	
}

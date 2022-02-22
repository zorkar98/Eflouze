package eFlouz.bll;

import eFlouz.bo.User;
import eFlouz.dal.UserDAOJDBCImpl;

public class UserManager {
	
// Création d'un compte utilisateur	
	UserDAOJDBCImpl userOkDao = new UserDAOJDBCImpl();
	String pseudo;
	String nom;
	String prenom;
	String email;
	String telephone;
	String rue;
	int code_postal;
	String ville;
	String mot_de_passe;
	User user = new User(pseudo, nom, prenom, email, telephone, rue, code_postal,ville, mot_de_passe);
	
//Fonction
	public boolean seConnecter(String email,String mdp) throws Exception{

	boolean userOk = false;
	User user = new User(email, mdp);
	try {
		userOk = userOkDao.selectUser(user);
	} catch (Exception e) {
	
		e.printStackTrace();
	}
	System.out.println("UserManager" + userOk);
	System.out.println(user.getEmail());
	System.out.println(user.getMot_de_passe());
	return (boolean) userOk;
	}	
	
	
//Fonction d'enregistrement d'un nouvel utilisateur

	public int ajouterUser (String pseudo, String nom, String prenom, String email, String telephone, String rue, int codePostal,
			String ville, String motDePasse) {

		int presenceEnBase = 12;
//R�ccup�r�ration de l'info si le pseudo ou l'email sont d�j� inscrit en base
		try {
			presenceEnBase = userOkDao.selectUserByPseudoAndEmail (pseudo,email);
			System.out.println(presenceEnBase);
		
		//Si il ne sont pas pr�sents l'inscription peut continuer
			if (presenceEnBase == 0) {
		

			User user = new User(pseudo, nom, prenom, email, telephone, rue, codePostal,ville, motDePasse);
			
			UserDAOJDBCImpl userDAOJDBCImpl = new UserDAOJDBCImpl();
				userDAOJDBCImpl.insertUser(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
//Return de l'info si le pseudo ou l'email sont d�j� pris pour l'IHM
		System.out.println("UserManager : " + presenceEnBase);
		return presenceEnBase ;
		
	}
	//Fonction permettant l'appel de la fonction deleteByEmailAndMdp de la dal
	public String supprimerCompte (String email, String mdp) throws Exception {
		System.out.println("manager " + email);
		System.out.println("manager " + mdp);

	String confirmation;
	confirmation = userOkDao.deleteUserByEmailAndMdp(email,mdp);
	return confirmation;
	}
	
	//Fonction de comparaison entre le l'actuel profil de l'utilisateur et de sont profil avec modification afin d'un UPDATE en bdd
	public static void miseAJourProfil (User userSession, User userAvecModif) throws Exception {
		
	
		if (userSession != userAvecModif) {
			userAvecModif.setNo_utilisateur(userSession.getNo_utilisateur());
			UserDAOJDBCImpl.updateUserByNoUtilisateur(userAvecModif);
		}
		
		
	}
}

package eFlouz.bll;

import eFlouz.bo.User;
import eFlouz.dal.UserDAOJDBCImpl;

public class UserManager {
	
// Création d'un compte utilisateur	
	UserDAOJDBCImpl userOkDao = new UserDAOJDBCImpl();
	
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
	public int ajouterUser (String pseudo, String nom, String prenom, String email, String telephone, String rue, int code_postal,
			String ville, String mot_de_passe) {
		int presenceEnBase = 12;
//R�ccup�r�ration de l'info si le pseudo ou l'email sont d�j� inscrit en base
		try {
			presenceEnBase = userOkDao.selectUserByPseudoAndEmail (pseudo,email);
			System.out.println(presenceEnBase);
		
		//Si il ne sont pas pr�sents l'inscription peut continuer
			if (presenceEnBase == 0) {
		
			User user = new User(pseudo, nom, prenom, email, telephone, rue, code_postal,ville, mot_de_passe);
			
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
	public static String supprimerCompte (String email, String mdp) throws Exception {

	String confirmation;
	confirmation = UserDAOJDBCImpl.deleteUserByEmailAndMdp(email,mdp);
	return confirmation;
	}
}

package eFlouz.bll;

import eFlouz.bo.User;
import eFlouz.dal.userDAOJDBCImpl;

public class User_manager {
	
// Création d'un compte utilisateur	
	userDAOJDBCImpl userOkDao = new userDAOJDBCImpl();
	public boolean interrogerBase(String email,String mdp) throws Exception{

	boolean userOk = false;
	User user = new User(email, mdp);
	try {
		userOk = userOkDao.seConnecter(user);
	} catch (Exception e) {
	
		e.printStackTrace();
	}
	System.out.println("User_manager" + userOk);
	System.out.println(user.getEmail());
	System.out.println(user.getMot_de_passe());
	return (boolean) userOk;
	}	
	
	
//Fonction d'enregistrement d'un nouvel utilisateur
	public int ajouterUser (String pseudo, String nom, String prenom, String email, float telephone, String rue, int code_postal,
			String ville, String mot_de_passe) {
		int presenceEnBase = 12;
//R�ccup�r�ration de l'info si le pseudo ou l'email sont d�j� inscrit en base
		try {
			presenceEnBase = userOkDao.selectByPseudoAndEmail (pseudo,email);
			System.out.println(presenceEnBase);
		
		//Si il ne sont pas pr�sents l'inscription peut continuer
			if (presenceEnBase == 0) {
		
			User user = new User(pseudo, nom, prenom, email, telephone, rue, code_postal,ville, mot_de_passe);
			
			userDAOJDBCImpl userDAOJDBCImpl = new userDAOJDBCImpl();
				userDAOJDBCImpl.ajouterUser(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
//Return de l'info si le pseudo ou l'email sont d�j� pris pour l'IHM
		System.out.println("User_Manager : " + presenceEnBase);
		return presenceEnBase ;
		
	}
}

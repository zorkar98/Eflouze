package eFlouz.bll;

import eFlouz.bo.User;
import eFlouz.dal.userDAOJDBCImpl;

public class User_manager {
	
	public boolean interrogerBase(String email,String mdp) throws Exception{
	userDAOJDBCImpl userOkDao = new userDAOJDBCImpl();
	boolean userOk = false;
	User user = new User(email, mdp);
	try {
		userOk = userOkDao.seConnecter(user);
	} catch (Exception e) {
		// TODO Auto-generated catch block
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
	
		//Réccupérération de l'info si le pseudo ou l'email sont déjà inscrit en base
		int presenceEnBase = selectByPseudoAndEmail (pseudo,email);
		
		//Si il ne sont pas présents l'inscription peut continuer
		if (presenceEnBase == 0) {
		
			User user = new User(pseudo, nom, prenom, email, telephone, rue, code_postal,ville, mot_de_passe);
			
			userDAOJDBCImpl userDAOJDBCImpl = new userDAOJDBCImpl();
			try {
				userDAOJDBCImpl.ajouterUser(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//Return de l'info si le pseudo ou l'email sont déjà pris pour l'IHM
		return presenceEnBase ;
		
	}
}

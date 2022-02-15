package eFlouz.bll;

import java.util.ArrayList;
import java.util.List;

import eFlouz.bo.User;
import eFlouz.dal.userDAOJDBCImpl;

public class User_manager extends userDAOJDBCImpl{
	
	private List<User> listeUser;

	
	public User_manager() {
		super();
	}

	public List<User> listerUser(){
		listeUser = new ArrayList<User>();
		try {
			listeUser = selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeUser;
	}
		
	public boolean verifierUser(String email, String mot_de_passe) {
		boolean validation = false;
			
		for (User utilisateur : listeUser) {
			String courriel = utilisateur.getEmail();
			String mdp = utilisateur.getMot_de_passe();
			
			if (courriel == email && mdp == mot_de_passe) {
				validation = true;
			}
		}
		return validation;
	}
	
	public void ajouterUser(String email, String mot_de_passe) {
		User user = new User(email, mot_de_passe);
		
	}

}

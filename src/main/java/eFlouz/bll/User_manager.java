package eFlouz.bll;

import eFlouz.bo.User;
import eFlouz.dal.userDAOJDBCImpl;

public class User_manager {
	
	public boolean interrogerBase(String email,String mdp) throws Exception{
	userDAOJDBCImpl userOkDao = new userDAOJDBCImpl();
	boolean userOk = false;
	User user = new User(email, mdp);
	try {
		userOk = userOkDao.selectAll(user);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("User_manager" + userOk);
	System.out.println(user.getEmail());
	System.out.println(user.getMot_de_passe());
	return (boolean) userOk;
	}	
}

package eFlouz.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eFlouz.bo.User;



public class userDAOJDBCImpl  {
	private static final String SELECTALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM [UTILISATEURS]";
	public List<User> selectAll() throws Exception
	{
			
		List<User> UserExtraits = new ArrayList<User>();
		Connection cnx = ConnectionProvider.getConnection();
		Statement rqt = cnx.createStatement();
		ResultSet rs = rqt.executeQuery(SELECTALL);
		int idCurrentUser = 0;
		User UserCourant = null;
		try {while(rs.next())
		{
			if (idCurrentUser != rs.getInt("no_utilisateur")) {
				UserCourant = new User("email","mot_de_passe");
				UserCourant.setEmail(rs.getString("email"));
				UserCourant.setMot_de_passe(rs.getString("mot_de_passe"));
				UserExtraits.add(UserCourant);
			}
		}
		}catch(Exception e) {
			e.printStackTrace();}
		System.out.println(UserExtraits.toString());
		return UserExtraits;
		
	}
	
}
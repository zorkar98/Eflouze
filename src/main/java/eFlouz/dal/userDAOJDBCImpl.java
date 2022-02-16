package eFlouz.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import eFlouz.bo.User;



public class userDAOJDBCImpl  {
	boolean flag = false;
	private static final String SELECT = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM [UTILISATEURS] WHERE (email = ? and mot_de_passe = ?)";
	public boolean selectAll(User user) throws Exception
	{
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement rqt = cnx.prepareStatement(SELECT);
		rqt.setString(1,user.getEmail());
		rqt.setString(2,user.getMot_de_passe());
		ResultSet rs = rqt.executeQuery();
		rs.next();
		
		try {
			
			if (rs.getString(5).trim().equals( user.getEmail().trim()));
			{
				flag = true;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();}
		System.out.println("JDBC" + flag );
		System.out.println(user.getEmail());
		System.out.println(user.getMot_de_passe());
		System.out.println(rs.getString(5));
		
		return flag;
		
	}
	
}
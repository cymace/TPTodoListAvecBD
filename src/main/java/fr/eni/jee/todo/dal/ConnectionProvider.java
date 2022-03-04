package fr.eni.jee.todo.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * S'occupe de garder une réference vers le dataSource et de renvoyer des connexions au besoin (pour la couche DAL)
 */
public class ConnectionProvider {
	// réference vers le datasource
	private static DataSource dataSource;
	
	// code statique qui s'execute pour mettre à jour l'attribut datasource
	static {
		Context context;
		try {
			context = new InitialContext();		
			ConnectionProvider.dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * S'occupe de renvoyer une connexion à partir du datasource
	 */
	public static Connection getConnection() {
		// TODO Auto-generated method stub
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

package fr.eni.jee.todo.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.jee.todo.bo.Todo;

public class TodoDAOJdbcImpl implements TodoDAO {

	private final String SELECT_TODO = "Select id, libelle from Todo;";
	private final String GET_TODO = "Select id, libelle from Todo where id = ?;";
	private final String UPDATE_TODO = "Update Todo set libelle = ? where id = ?;";
	private final String INSERT_TODO = "Insert into Todo(libelle) values(?);";
	private final String DELETE_TODO = "Delete from Todo where id = ?;";

	@Override
	public List<Todo> getAll() {
		// on utilise un bloc try-with-resources pour libérer automatiquement les ressources connection / statements
		try (Connection cnx = ConnectionProvider.getConnection(); Statement stmt = cnx.createStatement();) {
			// 1 - on récupère dans un ResultSet le resultat de ma reqête SQL
			ResultSet rs = stmt.executeQuery(SELECT_TODO);

			// 2 - on initialise la liste de Todo qu'on va renvoyer
			List<Todo> listeTodo = new ArrayList<Todo>();

			// 3 - on va parcourir le ResultSet afin de remplir cette List<Todo>
			while (rs.next()) {
				Todo todo = new Todo(rs.getInt("id"), rs.getString("libelle"));
				listeTodo.add(todo);
			}

			// 4 - on retourne notre liste
			return listeTodo;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void add(Todo todo) {
		// on utilise un bloc try-with-resources pour libérer automatiquement les ressources connection / statements
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(INSERT_TODO);) {
			// on change le ? de notre requête avec le libelle du todo passé en argument
			pStmt.setString(1, todo.getLibelle());
			// on envoie la requête
			pStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(int idASupprimer) {
		// on utilise un bloc try-with-resources pour libérer automatiquement les ressources connection / statements
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(DELETE_TODO);) {
			// on change le ? de notre requête avec l'id du todo passé en argument
			pStmt.setInt(1, idASupprimer);
			// on envoie la requête
			pStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Todo getById(int id) {
		// on utilise un bloc try-with-resources pour libérer automatiquement les ressources connection / statements
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(GET_TODO);) {
			// on change le ? de notre requête avec l'id du todo passé en argument
			pStmt.setInt(1, id);
			// on récupère dans un ResultSet le resultat de la reqête SQL
			ResultSet rs = pStmt.executeQuery();

			// on renvoie le premier élement de notre ResultSet (le seul normalement)
			if (rs.next()) {
				Todo todo = new Todo(rs.getInt("id"), rs.getString("libelle"));
				return todo;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateTodo(int id, Todo todo) {
		// on utilise un bloc try-with-resources pour libérer automatiquement les ressources connection / statements
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(UPDATE_TODO);) {
			// on change les ? de notre requête 
			pStmt.setString(1, todo.getLibelle());
			pStmt.setInt(2, id);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

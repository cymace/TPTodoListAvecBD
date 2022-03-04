package fr.eni.jee.todo.dal;

public class DAOFactory {

	public static TodoDAO getTodoDAO() {
		return new TodoDAOJdbcImpl();
	}

}

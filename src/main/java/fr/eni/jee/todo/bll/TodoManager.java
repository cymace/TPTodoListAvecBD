package fr.eni.jee.todo.bll;

import java.util.List;

import fr.eni.jee.todo.bo.Todo;
import fr.eni.jee.todo.dal.DAOFactory;
import fr.eni.jee.todo.dal.TodoDAO;

/**
 * Couche BLL/Métier/Service : s'occupe de valider les données et de faire le traitement Métier
 */
public class TodoManager {
	
	// todoDAO : référence notre couche DAL qui va s'interfacer avec la base de données
	private TodoDAO todoDAO;
	
	
	/**
	 * On affecte la bonne implémentation de DAO à l'attribut TodoDAO
	 */
	public TodoManager() {
		// this.todoDAO = new TodoDAOJdbcImpl(); : pas terrible car ca crée un couplage / dépendance trop fort entre TodoManager et TodoDaoJdbcImpl
		this.todoDAO = DAOFactory.getTodoDAO(); // on utilise plutôt une injection de dépendance pour avoir un couplage faible : https://www.geeksforgeeks.org/coupling-in-java/
	}

	/**
	 * Renvoie la liste des todos
	 */
	public List<Todo> getListTodo() {
		return todoDAO.getAll();
	}

	/**
	 * Supprime le to avec l'id passé en argument
	 */
	public void deleteTodoWithId(int idASupprimer) {
		todoDAO.deleteById(idASupprimer);		
	}
	
	/**
	 * Recupère le Todo avec l'id en paramètre
	 */
	public Todo getById(int id) {
		return todoDAO.getById(id);		
	}

	/**
	 * Ajoute le todo
	 * @throws ValidationException 
	 */
	public void add(Todo todo) throws ValidationException {
		// On va faire une validation "côté serveur" avant ajout (car le <input required/> garantit pas à 100% que la tâche ne sera pas nulle)
		if (validationOk(todo)) {
			todoDAO.add(todo);
		}		
	}

	/**
	 * Méthode interne de validation du todo
	 * @throws ValidationException 
	 */
	private boolean validationOk(Todo todo) throws ValidationException {
		//si la valiation n'est pas OK, on lève un exception
		if (todo.getLibelle() == null || todo.getLibelle().isBlank() ) {
			throw new ValidationException("le libelle doit être rempli");
		} 
		return true;
		
	}

	/**
	 * Modifie le todo d'id en paramètre avce le nouveau
	 */
	public void updateTodo(int id, Todo todo) {
		todoDAO.updateTodo(id, todo);
		
	}
	
}

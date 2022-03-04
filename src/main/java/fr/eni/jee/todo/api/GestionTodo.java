package fr.eni.jee.todo.api;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import fr.eni.jee.todo.bll.TodoManager;
import fr.eni.jee.todo.bll.ValidationException;
import fr.eni.jee.todo.bo.Todo;

// 
/**
 * Classe :  correspond à la ressource
 */
//@Path("/todo") => la ressouces est accessible à l'url : URL_BASE + "/todo" : http://localhost:8080/TPTodoList/api/todo
@Path("/todo") 
public class GestionTodo {
	
	// réference vers notre classe qui s'occupe du traitement "métier"
	private TodoManager todoManager = new TodoManager();
	
	/*********************************************
	 * Chaque méthode correspond à une opération 
	 *********************************************/
	
	
	@GET // Lecture : code appelé lorsqu'on fait une requête HTTP GET sur l'url de la ressource
	public List<Todo> getListTodo(){
		return todoManager.getListTodo();
	}
	
	@GET  // Lecture : code appelé lorsqu'on fait une requête HTTP GET sur l'url de la ressource + /{id}
	// Path sans validation @Path("/{id}") 
	@Path("/{id : \\d+}") // Path avec validation regex : \\d+ => on ne va pouvoir mettre QUE des entiers pour l'id, sinon la méthode ne sera pas appelée
	public Todo getTodo(@PathParam("id") int id){
		return todoManager.getById(id);
	}
	
	@POST // Ajout : code appelé lorsqu'on fait une requête HTTP POST sur l'url de la ressource + /{id}
	public void addTodo(Todo todo) throws ValidationException {
		todoManager.add(todo);
	}
	
	@DELETE  // Suppression :code appelé lorsqu'on fait une requête HTTP DELETE sur l'url de la ressource + /{id}
	// Path sans validation @Path("/{id}") 
	@Path("/{id : \\d+}") // Path avec validation regex : \\d+ => on ne va pouvoir mettre QUE des entiers pour l'id, sinon la méthode ne sera pas appelée
	public void deleteTodo(@PathParam("id") int id){
		todoManager.deleteTodoWithId(id);
	}
	
	@PUT  // Modification : code appelé lorsqu'on fait une requête HTTP DELETE sur l'url de la ressource + /{id}
	// Path sans validation @Path("/{id}") 
	@Path("/{id : \\d+}") // Path avec validation regex : \\d+ => on ne va pouvoir mettre QUE des entiers pour l'id, sinon la méthode ne sera pas appelée
	public void deleteTodo(@PathParam("id") int id, Todo todo){
		todoManager.updateTodo(id, todo);
	}
}

package fr.eni.jee.todo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.todo.bll.TodoManager;
import fr.eni.jee.todo.bll.ValidationException;
import fr.eni.jee.todo.bo.Todo;

@WebServlet("") // Servlet accessible à la racine de mon Appli (http://localhost:8080/TPTodoList)
public class TodoServlet extends HttpServlet {

	// réference vers notre classe qui s'occupe du traitement "métier"
	private TodoManager todoManager = new TodoManager();

	/**
	 * doGet : redirige vers la JSP en ajoutant un attribut de requête avec la liste
	 * des tâches
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("listeTodo", todoManager.getListTodo());
		request.getRequestDispatcher("/WEB-INF/todo.jsp").forward(request, response);
	}

	/**
	 * doPost : ajouter la tache en paramètre à la liste des tâches
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1 - Si on a le paramètre "delete", on supprime la tâche correspondante
		if (request.getParameter("delete") != null) {
			int idASupprimer = Integer.parseInt(request.getParameter("delete"));
			todoManager.deleteTodoWithId(idASupprimer);
		}

		// 2 - Sinon, on ajoute la tâche qui correspond au paramètre "tache" de notre requête
		else {
			// 2.1 - on crée la tâche qui correspondant au paramètre "tache" de notre requête
			Todo todo = new Todo(request.getParameter("tache"));

			// 2.2 - on ajoute la tâche à notre liste
			try {
				todoManager.add(todo);
			} 
			// si jamais y'a une erreur de validation
			catch (ValidationException validationException) {
				// 1 - on la met en attribut de requête de façon à l'afficher dans la jsp
				request.setAttribute("erreur", validationException.getMessage());
				request.setAttribute("listeTodo", todoManager.getListTodo()); // on remet en attribut la liste de todo également
				// 2 - on forward la requête à la JSP de façon à afficher l'erreur
				request.getRequestDispatcher("/WEB-INF/todo.jsp").forward(request, response);
				// 3 - on sort de la méthode pour éviter d'appeler :  response.sendRedirect("./");
				return;
			}
		}
		

		// Dans tous les cas, on recharge la page en GET pour éviter les resoumissionde
		// formulaire lorsquon rafraichit la page
		response.sendRedirect("./");
	}	
}

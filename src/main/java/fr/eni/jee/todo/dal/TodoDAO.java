package fr.eni.jee.todo.dal;

import java.util.List;

import fr.eni.jee.todo.bo.Todo;

public interface TodoDAO {

	List<Todo> getAll();

	void deleteById(int idASupprimer);

	void add(Todo todo);

	Todo getById(int id);

	void updateTodo(int id, Todo todo);

}

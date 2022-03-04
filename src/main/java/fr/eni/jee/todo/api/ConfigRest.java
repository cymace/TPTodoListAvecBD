package fr.eni.jee.todo.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// @ApplicationPath("/api") : toutes les ressources de mon API vont être accessibles 
// avec l'url de base : http://localhost:8080/TPTodoList/api 

@ApplicationPath("/api")
public class ConfigRest extends Application {

}

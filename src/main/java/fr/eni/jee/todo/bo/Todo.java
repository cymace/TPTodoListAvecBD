
package fr.eni.jee.todo.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*****************************************
 * On utilise les annotations Lombok
 * va automatiquement créer à la compilation les constructeurs / getters / setters selon nos annotations
 * Ca fait gagner bcp de temps!
 * **************************************/

@Getter @Setter 	// on crée automatiquement les getters / setters
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor 	// on crée automatiquement les 3 constructeurs
public class Todo {
	public int id; // sert à identifier  de manière unique une tâche
	private @NonNull String libelle;
	
	
	/***
	 * Plus besoin du code suivant : on a les annotations Lombok !
	 
	
	public Todo(String libelle) {				
		this.libelle = libelle;
	}
	
	public Todo(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	
	public Todo() {
		super();
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public String getLibelle() {
		return libelle;
	}

	public int getId() {
		return id;
	}
	**/
}

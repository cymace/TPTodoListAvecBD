<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste de tâches</title>
<link rel="stylesheet" href="./style.css" />
</head>
<body>
	<!-- on affiche ici les éventuelles erreurs -->
	<c:if test="${erreur != null}">
		<p class="error">
			Erreur : ${erreur}
		</p>
		
	</c:if>
	
	<form method="POST">
		<label for="tache">Nouvelle tâche </label>
		<input type="text" id="tache" name="tache" required/>
		<button>Ajouter</button>
	</form>
	
	<h2>Mes tâches en cours</h2>
	<ul>
		<!-- Pour chaque todo dans mon attribut de requête "listeTodo" -->
		<c:forEach var="todo" items="${listeTodo}">
			<li>
				<form method="POST">
					<span>${todo.libelle}</span>				
					<button class="button-delete" name="delete" value="${todo.id}">X</button>
				</form>
			</li>
		</c:forEach>
	</ul>
</body>
</html>
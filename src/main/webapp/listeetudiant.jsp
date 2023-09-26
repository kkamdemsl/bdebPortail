<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Portail Étudiants Bois de Boulogne</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id = "wrapper">
		<div id = "header">
			<h1>Portail Étudiants Bois de Boulogne</h1>
		</div>	
	</div>
	<div id = "container">
		<div id = "content">
		<!-- input de type bouton pour ajouter étudiant dans la BD-->
		<input type="button" value="Ajouter un étudiant" id="AddButton" 
		onclick="window.location.href='ajoutEtudiant.jsp'">
		
			<table >			
				<tr> 
					<th>First Name</th>
					<th>Last Name</th>
					<th>E-mail</th>
				</tr>
				<c:forEach var="etudiant" items="${liste_etudiants}">
					<tr>
						<td>${etudiant.getFirstName()}</td>
						<td>${etudiant.getLastName()}</td>
						<td>${etudiant.getEmail()}</td>
					</tr>
					
				</c:forEach>
			</table>
		</div>
	
	</div>

</body>
</html>
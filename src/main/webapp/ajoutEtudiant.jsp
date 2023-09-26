<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter un étudiant</title>
<link type="text/css" rel="ajoutetudiantsheet" href="css/ajoutetudiant.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h1>Collège Bois de Bologne</h1>
		</div>
	</div>
	<div id="container">
		<h3>Ajouter un étudiant</h3>
		<form method="GET" action="StudentController">
		<input type="hidden" name="command" value="ADD"/>
			<table>
				<tr>
					<td><label>First Name</label></td>
					<td><input type="text" name="firstName"/></td>					
				</tr>
				<tr>
					<td><label>Last Name</label></td>
					<td><input type="text" name="lastName"/></td>					
				</tr>
				<tr>
					<td><label>Email</label></td>
					<td><input type="email" name="email"/></td>					
				</tr>
				<tr>					
					<td><input type="submit" value="Save"/></td>					
				</tr>
			</table>
		</form>
	
	</div>

</body>
</html>
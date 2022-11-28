<%@page import ="beans.Utilisateur"%>
<%@page import ="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="entete.jsp" %>
	<main>
	<h1>Liste des Utilisateurs</h1>
	
	<c:choose>
		<c:when test="${empty listUser}">
			<p>La liste est vide</p>
		</c:when>
		<c:otherwise>
			<table>
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Login</th>
				<th>Password</th>
				<th colspan="2">Actions</th> 
			</tr>
			<c:forEach var="utilisateur" items="${ listUser }">
				<tr>
			
			<td><c:out value="${utilisateur.id}"/></td>
			<td><c:out value="${utilisateur.nom}"/></td>
			<td><c:out value="${utilisateur.prenom}"/></td>
			<td><c:out value="${utilisateur.login}"/></td>
			<td><c:out value="${utilisateur.password}"/></td>
			<td><a href="update?id=${utilisateur.id}">Modifier</a> </td>
			<td><a href="delete?id=${utilisateur.id}" onclick="return confirm('Voulez vous supprimer!')">Supprimer</a> </td>
		</tr>
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	</main>
</body>
</html>
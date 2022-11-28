<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="<c:url value="/css/style.css"/>">
<script type="text/javascript" src="<c:url value="/js/script.js"/>"></script>
<title>Liste des utilisateurs</title>
</head>
<body>
	<header>
		<nav>
			<ul>
				<li>
					<a href="<c:url value="/"/>">Accueil</a>
				</li>				
				<li>
					<a href="<c:url value="/list"/>">
						liste Utilisateurs
					</a>
				</li>
				
				<li>
					<a href="<c:url value="/add"/>">Ajouter</a>
				</li>
			</ul>
		</nav>
	</header>
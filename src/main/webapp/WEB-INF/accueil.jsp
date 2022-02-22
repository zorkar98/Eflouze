<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil eFlouz</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSSaccueil/style.css">
</head>
<body class="body">
	<c:choose>
	<c:when test="${empty sessionScope}">
	<jsp:include page="/WEB-INF/fragment/header.jspf"></jsp:include>
	</c:when>
	<c:when test="${not empty sessionScope}">
	<jsp:include page="/WEB-INF/fragment/headerConnecte.jspf"></jsp:include>
	</c:when>
	</c:choose>
		<div class="sub_header">
			<h1>Liste des enchères</h1>
		</div>
		<p>Filtres</p>
		<div class="container">
			<form action="${pageContext.request.contextPath}/home" method="get">
				<div class="search">
					<input type="search" placeholder="Le nom de l'article contient" name="rechercher">
				</div>
				<div class="submit">
					<input type="submit" value="Rechercher">
				</div>
			</form>
		</div>
		<div class="container_enchere">
			<div class="list_enchere">
				<img alt="" src="">
				<a>Nom de l'article :</a>
				<p>Prix :</p>
				<p>Fin de l'enchère :</p>
					<p>Vendeur : <a href="${pageContext.request.contextPath}/profil?titre = name" method="get">JOJO44</a></p>
			</div>
			<div class="mes_enchere">
				<img alt="" src="">
				<a>Nom de l'article :</a>
				<p>Prix :</p>
				<p>Fin de l'enchère :</p>
				<p>Vendeur :</p>
			</div>
		</div>
		<p>${requestScope.confirmation}</p>
</body>
</html>

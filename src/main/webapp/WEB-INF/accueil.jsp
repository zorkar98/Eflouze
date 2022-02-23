<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
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
					<input type="search" placeholder="Le nom de l'article contient" name="rechercher" required="required">
				</div>
				<div class="submit">
					<input type="submit" value="Rechercher">
				</div>
			</form>
		</div>
		<div class="container_enchere">
			
				<img alt="" src="">
			<c:forEach items="${listeArticles}" var="article">
					<div class="list_enchere">
						<c:out value="Nom article : ${article.nomArticle}"/><br>
						<c:out value="Prix : ${article.prixInitial}"/><br>
						<c:out value="Fin de l'enchere : "/><br>
						<c:out value="Vendeur : ${article.noUtilisateur}"/><br>
					</div>
			</c:forEach>
			
			
			<!-- <div class="mes_enchere">
				<img alt="" src="">
				<a>Nom de l'article :</a>
				<p>Prix : </p>
				<p>Fin de l'enchère :</p>
				<p>Vendeur :</p>
			</div> -->
		</div>
		<p>${requestScope.confirmation}</p>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/CSSaccueil/style.css">
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
				<input type="search" placeholder="Le nom de l'article contient"
					name="rechercher" required="required">
			</div>
			<div class="submit">
				<input type="submit" value="Rechercher">
			</div>
		</form>
	</div>
	<c:if test="${not empty sessionScope}">
			<form action="" class="container_mes_listes">
			<div>
			<ul>
				<li><input type="radio" name="Achats" value="Achats"><a>Achats</a></li>
				<li class="container_mes_listes_li"><input type="checkbox" name="Enchères ouvertes"><a>Enchères ouvertes</a></li>
				<li class="container_mes_listes_li"><input type="checkbox" name="Mes enchères" ><a>Mes enchères</a></li>
				<li class="container_mes_listes_li"><input type="checkbox" name="Mes enchères remportées" ><a>Mes enchères remportées</a></li>
			</ul>
			</div>
			<div>
			<ul>
				<li><input type="radio" name="Mes Ventes" value="Mes Ventes"><a>Mes ventes</a></li>
				<li class="container_mes_listes_li"><input type="checkbox" name="Mes ventes en cours"><a>Mes ventes en cours</a></li>
				<li class="container_mes_listes_li"><input type="checkbox" name="Ventes non débutées" ><a>Ventes non débutées</a></li>
				<li class="container_mes_listes_li"><input type="checkbox" name="Ventes terminées" ><a>Ventes terminées</a></li>
			</ul>
			</div>
			</form>
		</c:if>
	<div class="container_enchere">
		
			<img alt="" src=""> 
			<c:forEach items="${liste}" var="article">
			<div class="list_enchere">
				<c:out value="Nom de l'article : ${article.nomArticle}"/><br>
				<c:out value="Prix : ${article.prixInitial}"/><br>
				<c:out value="Fin de l'enchère :${article.dateFinEnchere}"/><br>
				<c:out value="Vendeur : ${article.pseudo}" /><br>
				<a href="${pageContext.request.contextPath}/profil?titre = name" >${requestScope.noUtilisateur}</a>
			</div>
			</c:forEach>
					
		
		<div class="mes_enchere">
			<img alt="" src=""> <a>Nom de l'article :</a>
			<p>Prix :</p>
			<p>Fin de l'enchère :</p>
			<p>Vendeur :</p>
		</div>
	</div>
	<p>${requestScope.confirmation}</p>
</body>
</html>

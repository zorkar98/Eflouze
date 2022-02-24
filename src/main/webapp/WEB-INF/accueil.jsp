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
			<jsp:include page="/WEB-INF/fragment/header.jsp"></jsp:include>
		</c:when>
		<c:when test="${not empty sessionScope}">
			<jsp:include page="/WEB-INF/fragment/headerConnecte.jsp"></jsp:include>
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
				<li><input type="radio" name="choix" id="choix"value="Achats" checked><label for="choix" >Achats</label></li>
				<li class="container_mes_listes_li"><input type="checkbox" name="choix2" checked><a>Enchères ouvertes</a></li>
				<li class="container_mes_listes_li"><input type="checkbox" name="choix2" ><a>Mes enchères</a></li>
				<li class="container_mes_listes_li"><input type="checkbox" name="choix2" ><a>Mes enchères remportées</a></li>
			</ul>
			</div>
			<div>
			<ul>
				<li><input type="radio" name="choix"  id="choix" value="MesVentes" ><label for="choix">Mes Ventes</label></li>
				<li class="container_mes_listes_li"><input type="checkbox" name="choix2"><a>Mes ventes en cours</a></li>
				<li class="container_mes_listes_li"><input type="checkbox" name="choix2" ><a>Ventes non débutées</a></li>
				<li class="container_mes_listes_li"><input type="checkbox" name="choix2" ><a>Ventes terminées</a></li>
			</ul>
			</div>
			</form>
	</c:if>
	<div class="container_enchere">
		
			<img alt="" src=""> 
			<c:forEach items="${liste}" var="article">
			 <div class="list_enchere">
				<c:out value="Nom de l'article : "/>
				<a href="${pageContext.request.contextPath}/AfficherArticleServlet?titre=${article.noArticle}" >${article.nomArticle}</a><br>
				<c:out value="Prix : ${article.prixInitial}"/><br>
				<c:out value="Fin de l'enchère :${article.dateFinEnchere}"/><br>
				<c:out value="Vendeur :" />
				<a href="${pageContext.request.contextPath}/profil?vendeur=${article.pseudo}" >${article.pseudo}</a><br>
			</div>
			</c:forEach>
		
	</div>
	<p>${requestScope.confirmation}</p>
</body>
</html>

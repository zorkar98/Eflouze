<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSSarticle/style.css">
</head>
<body>
<c:choose>
	<c:when test="${empty sessionScope}">
	<jsp:include page="/WEB-INF/fragment/header.jsp"></jsp:include>
	</c:when>
	<c:when test="${not empty sessionScope}">
	<jsp:include page="/WEB-INF/fragment/headerConnecte.jsp"></jsp:include>
	</c:when>
	</c:choose>

	<h1>Détails de l'offre !</h1>
	
	<div>
		<p>Nom article : ${nomArticle}</p>
		<p>Description : ${description}</p>
		<p>Catégorie :
					 <c:if test= "${categorie == 1}">Maison</c:if>
					 <c:if test= "${categorie == 2}">High-tech</c:if>
					 <c:if test= "${categorie == 3}">Voiture</c:if></p>
		<p>Meilleur offre : ${montantMeilleureEnchere}</p>
		<p>Mise à prix : ${prixInitial}</p>
		<p>Fin de l'enchère : ${dateFinEnchere}</p>
		<p>Retrait :</p>
		<ul>
			<li>${rue}</li>
			<li>${codePostal}</li>
			<li>${ville}</li>
		</ul>
		<p>Vendeur : ${pseudoVendeur} </p>
			
		<p>Ma proposition :</p>
	
			<form action="${pageContext.request.contextPath}/AfficherArticleServlet" method="post">
			<input class="" type="number" min="${montantMeilleureEnchere + 1}" name="proposition" placeholder="Proposition">
			<input class="" type="submit"  value="encherir" name="Enchérir">
		</form>
	
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>eFlouz</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSSafficherProfil/style.css">
</head>
<body class="body">
	
	<c:choose>
	<c:when test="${empty sessionScope} }">
	<jsp:include page="/WEB-INF/fragment/header.jspf"></jsp:include>
	</c:when>
	<c:when test="${not empty sessionScope}">
	<jsp:include page="/WEB-INF/fragment/headerConnecte.jspf"></jsp:include>
	</c:when>
	</c:choose>
	<div class="container2">
		<div class="info_profil">
	<c:if test="${not empty sessionScope && empty requestScope.vendeur}">
		
			<p>Pseudo : ${sessionScope.user.pseudo}</p>
			<p>Nom : ${sessionScope.user.nom}</p>
			<p>Prénom : ${sessionScope.user.prenom}</p>
			<p>Email : ${sessionScope.user.email}</p>
			<p>Téléphone : ${sessionScope.user.telephone}</p>
			<p>Rue : ${sessionScope.user.rue}</p>
			<p>Code postal : ${sessionScope.user.codePostal}</p>
			<p>Ville : ${sessionScope.user.ville}</p>
			<p>Credit : ${sessionScope.user.credit}</p>
			<a href="${pageContext.request.contextPath }/monProfil" ><input class="form_item_submit" type="button" value="Modifier" name="Annuler"></a>
	 </c:if>

	<c:if test="${not empty requestScope.vendeur}">
		<p>Pseudo : ${requestScope.vendeur.pseudo}</p>
			<p>Nom : ${requestScope.vendeur.nom}</p>
			<p>Prénom : ${requestScope.vendeur.prenom}</p>
			<p>Email : ${requestScope.vendeur.email}</p>
			<p>Téléphone : ${requestScope.vendeur.telephone}</p>
			<p>Rue : ${requestScope.vendeur.rue}</p>
			<p>Code postal : ${requestScope.vendeur.codePostal}</p>
			<p>Ville : ${requestScope.vendeur.ville}</p>
		</c:if> 
		</div>
	</div>
</body>
</html>
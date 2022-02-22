<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>eFlouze - Profil</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/CSSconnexion/style.css">
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

	<h1 class="title">Création de compte eFlouz</h1>
	<br>
		<p class="message_creation"> ${requestScope.Succes}${requestScope.Pseudo}${requestScope.Email}${requestScope.Both}</p>
	<br>
	<form  action="./monProfil" method="post" class="form">
		<div class="div">
			<input class="form_item" type="text" name="Pseudo" placeholder="Pseudo"> 
			<input class="form_item" type="text" name="Nom" placeholder="Nom">
		</div>
		<div class="div">
			<input class="form_item" type="text" name="Prenom" placeholder="Prenom">
			<input class="form_item" type="text" name="Email" placeholder="Email">
		</div>
		<div class="div">
			<input class="form_item" type="text" name="Telephone" placeholder="Telephone">
			<input class="form_item" type="text" name="Rue" placeholder="Rue">
		</div>
		<div class="div">
			<input class="form_item" type="text" name="CodePostal" placeholder="Code Postal">
			<input class="form_item" type="text" name="Ville" placeholder="Ville">
		</div>
		<div class="div">
			<input class="form_item" type="text" name="MotDePasse" placeholder="Mot de passe">
			<input class="form_item" type="text" name="Confirmation" placeholder="Confirmation">
		</div>
		<br>
		<div class="div_submit">
			<input class="form_item_submit" type="submit" value="Créer" name="Creer" > 
			<a href="${pageContext.request.contextPath }/home" ><input class="form_item_submit" type="button" value="Annuler" name="Annuler"></a>
		</div>
	</form>
	<br>
</body>
</html>
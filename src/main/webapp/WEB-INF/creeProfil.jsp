<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>eFlouze - Page de création de compte</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/CSSconnexion/style.css">
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
	<h1 class="title">Création de compte eFlouz</h1>
	
	<br>
	<div class="title">
	<c:if test="${not empty sessionScope.user.pseudo}">
		<p>Credit : ${sessionScope.user.credit} points</p>
	</c:if>
	</div>
	<form action="./monProfil" method="post" class="form">
		<div class="div">
			<input class="form_item" type="text" name="Pseudo"
				placeholder="Pseudo"> <input class="form_item" type="text"
				name="Nom" placeholder="Nom" required="required">
		</div>
		<div class="div">
			<input class="form_item" type="text" name="Prenom"
				placeholder="Prenom"> <input class="form_item" type="email"
				name="Email" placeholder="Email" required="required">
		</div>
		<div class="div">
			<input class="form_item" type="tel" name="Telephone"
				placeholder="Telephone"> <input class="form_item"
				type="text" name="Rue" placeholder="Rue" required="required">
		</div>
		<div class="div">
			<input class="form_item" type="text" name="CodePostal"
				placeholder="Code Postal"> <input class="form_item"
				type="text" name="Ville" placeholder="Ville" required="required">
		</div>
		<div class="div">
			<c:if test="${not empty sessionScope.user.pseudo}">
				<input class="form_item" type="password" name="AncienMotDePasse"

					placeholder="Ancien Mot de passe" required="required">

			</c:if>
		</div>
		<div class="div">

			<input class="form_item" type="password"" name="MotDePasse"

				placeholder="Mot de passe"> <input class="form_item"

				type="password" name="Confirmation" placeholder="Confirmation" required="required">

		</div>
		<div class="div">
			<br>
			<c:if test="${not empty sessionScope.user.pseudo}">
				<div class="div_submit">
					<a href="${pageContext.request.contextPath }/modifierProfil"><input class="form_item_submit" type="submit" value="Enregistrer" name="Enregistrer"></a> 
					<a href="${pageContext.request.contextPath }/supprimerCompte"><input class="form_item_submit" type="button" value="Supprimer" name="Supprimer"></a>
				</div>
			</c:if>
		</div>
		<br>
		<c:choose>
			<c:when test="${empty sessionScope.user.pseudo}">
				<div class="div_submit">
					<input class="form_item_submit" type="submit" value="Créer"
						name="Creer"> 
						<a href="${pageContext.request.contextPath }/home"><input
						class="form_item_submit" type="button" value="Annuler"
						name="Annuler"></a>

				</div>
			</c:when>
		</c:choose>
	</form>
	<p class="message_creation">${requestScope.Succes}${requestScope.Pseudo}${requestScope.Email}${requestScope.Both}</p>
</body>
</html>
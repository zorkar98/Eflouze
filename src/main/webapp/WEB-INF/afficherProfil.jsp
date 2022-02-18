<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSSafficherProfil/style.css">
</head>
<body>
	
	<div class="header">
		<h1>eFlouz</h1>
	</div>
	<div class="container2">
		<div class="info_profil">
			<p>Pseudo : ${requestScope.userSession.pseudo}</p>
			<p>Nom : ${requestScope.userSession.nom}</p>
			<p>Prénom : ${requestScope.userSession.prenom}</p>
			<p>Email : ${requestScope.userSession.email}</p>
			<p>Téléphone : ${requestScope.userSession.no_telephone}</p>
			<p>Rue : ${requestScope.userSession.rue}</p>
			<p>Code postal : ${requestScope.userSession.code_postal}</p>
			<p>Ville : ${requestScope.userSession.ville}</p>
		</div>
	</div>
</body>
</html>
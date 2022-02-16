<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>eFlouze - Page de création de compte</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/CSSConnexion/style.css">
</head>
<body class="body">
	<h1 class="title">Bonjour</h1>
	<%
	if (request.getAttribute("couleur") != "red") {
	%>
	<span style="color: black" class="message">Création de compte
		eFlouz</span>
	<%
	} else {
	%>
	<span style="color: red" class="message">!!! Indentifiants de
		connexion non valides !!!</span>
	<%
	}
	%>
	<br>
	<form  action="" <%=request.getContextPath()%>"/Profil" method="post" class="form">
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
			<a href="./seConnecter.jsp"><button class="form_item_submit">Annuler</button></a>
		</div>



	</form>

</body>
</html>
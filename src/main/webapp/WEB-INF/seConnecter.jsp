<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html class="html">
<head>
<meta charset="utf-8">
<title>eFlouze - Page de connexion au compte</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSSConnexion/style.css">
<script src="script.js"></script>
</head>
<body class="body">




<h1 class="title">Bonjour</h1>

  
  <h3 style="color:black" class="message">Se connecter à eFlouz</h3>
  <c:if test="${couleur != red}">
    <p style="color: red" class="message">!!! Indentifiants de connexion non valides !!! </p>
  	</c:if>
<br>
<form action="${pageContext.request.contextPath }/SeConnecterServlet" method="post" class="form">
<input class="form_item" type="text" name="email" placeholder="Adresse e-mail">
<input class="form_item" type="password" name="mot_de_passe" placeholder="Mot de passe">
<input class="form_item" type="submit" value="Connexion">
<div class="form_div">
<input type="checkbox" id="memoriser" name="memoriser">
<label for="memoriser">Se souvenir</label>
</div>
</form>
<br>

<a href="./Profil" ><button class="button" >Créer un compte</button></a>






</body>
</html>
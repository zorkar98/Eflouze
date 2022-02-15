<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<span class="message">Se connecter à eFlouz</span>
	<br>
	<form action="<%=request.getContextPath()%>/se_connecterServlet" method="post">
		<input type="text" name="email" placeholder="Adresse e-mail">
		<input type="password" name="mot_de_passe" placeholder="Mot de passe">
		<input type="submit" value="Connexion">
		<input type="checkbox" id="memoriser" name="memoriser">
		<label for="memoriser">Se souvenir</label>
	</form>
	<br>
	<button>créer un compte</button>

</body>
</html>
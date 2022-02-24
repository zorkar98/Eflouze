
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>eFlouz</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSSaccueil/style.css">
</head>
<body class="body">
	<div class="header">
		<a href="./home"><img src="https://user-images.githubusercontent.com/99649916/155147937-d27eea57-bb81-4460-8bc5-a7fc9b606ee4.jpg"></a>
		<div class="headerAncres"><a href="./vendre">Encheres</a>
		<a href="./vendre">Vendre</a>
		<a href="./profil?vendeur=${sessionScope.pseudo}" >Mon_profil</a>
		<a href="./deconnexion" id="session">Deconnexion</a>
		</div>
	</div>
</body>
</html>
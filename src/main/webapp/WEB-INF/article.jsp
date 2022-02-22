<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSSarticle/style.css">
</head>
<body>
	<div class="container">
		<div>
			<h1>eFlouz</h1>
			<img alt="" src=""><p>PHOTO ARTICLE</p>
		</div>
		<div>
			<h1>Nouvelle vente</h1>
			<form action="${pageContext.request.contextPath}/vendre" method="post">
				<p>Article : <input type="text" name="nomArticle"></p>
				<p class="description_taille">Description :  <input type="text" name="description"></p>
				<p>Catégorie
					<select name="categorie">
						<option>--Choisissez la catégorie</option>
						<option value="1">Maison</option>
						<option value="2">High-Tech</option>
						<option value="3">Voiture</option>
					</select>
				</p>
				<p>Photo <input type="submit" name="uploader" value="UPLOADER"></p>
				<p>Mise à prix : <input type="number" name="prix"></p>
				<p>Debut enchere <input type="date" name="debutDate"></p>
				<p>Fin enchere : <input type="date" name="finDate"></p>
				<div>
					<p>Rue : <input type="text"></p>
					<p>Code postal : <input type="text"></p>
					<p>Ville : <input type="text"></p>
					
				</div>
				<input type="submit" value="Enregistrer">
				<input type="submit" value="Annuler">
				<%-- <c:if test="${flag == true}">
					<p style="color: vert">Article créé avec succès</p>
				</c:if> --%>
			</form>		
		</div>
	</div>	
</body>
</html>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CSSremporterVente/style.css">
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
	
	<div class="container">
		<h1><c:out value="${sessionScope.nomArticle }"/></h1>
		<p class="widthDescri">Description :<c:out value="${sessionScope.description }"/></p>
		<p><c:out value="Meilleur offre : ${sessionScope.proposition }"/></p>
		<p><c:out value="Mise à prix : ${sessionScope.prixInitial}"/></p>
		<p><c:out value="Vendeur : ${sessionScope.vendeur }"/></p>
		<a href="${pageContext.request.contextPath}/home"><input type="submit" value="Retour"></a>
		<p class="remporte"><c:out value="Enchère remportée"/></p>
	</div>
</body>
</html>
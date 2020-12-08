<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<c:forEach var="article" items="${articles}">
		<div class="card m-3">
			<form action="${pageContext.request.contextPath}/detailVente" method="POST">
					<input type="hidden" value="<c:out value="${article.noArticle}"/>" name="noArticle" />
				<c:choose>
						<c:when test="${empty sessionScope.sessionUser }">
							<button type="submit" class="btn btn-link" disabled><h3>${article.nomArticle}</h3></button>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn btn-link"><h3>${article.nomArticle}</h3></button>
						</c:otherwise>
					</c:choose>
			</form>
			<svg xmlns="http://www.w3.org/2000/svg"
				class="d-block user-select-none" width="100%" height="200"
				aria-label="Placeholder: Image cap" focusable="false" role="img"
				preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180"
				style="font-size: 1.125rem; text-anchor: middle">
    <rect width="100%" height="100%" fill="#868e96"></rect>
    <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Photo de l'article</text>
  </svg>
			<div class="card-body">
				<p class="card-text">${article.description}.</p>
			</div>
			<ul class="list-group list-group-flush">
				<li class="list-group-item">Prix : ${article.miseAPrix}</li>
				<li class="list-group-item">Fin de l'enchère : ${article.dateFinEncheres}</li>
			</ul>
			<div class="card-body">
				<form action="${pageContext.request.contextPath}/afficherProfil"
					method="POST">
					<input type="hidden"
						value="<c:out value="${article.vendeur.noUtilisateur}"/>"
						name="noUtilisateur" /> Vendeur :
					<c:choose>
						<c:when test="${empty sessionScope.sessionUser }">
							<button type="submit" class="btn btn-link" disabled>${article.vendeur.pseudo}</button>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn btn-link">${article.vendeur.pseudo}</button>
						</c:otherwise>
					</c:choose>
				</form>
			</div>
		</div>
	</c:forEach>
</div>


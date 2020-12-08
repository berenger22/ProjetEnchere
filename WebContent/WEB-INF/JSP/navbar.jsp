<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<div
		class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-primary border-bottom shadow-sm">
		<h5 class="my-0 mr-md-auto font-weight-normal"><a class="p-2 text-dark" href="${pageContext.request.contextPath}/">ENI-Enchères</a></h5>
		
		<c:choose>
			<c:when test="${empty sessionScope.sessionUser }">
				<nav class="my-2 my-md-0 mr-md-3">
					<a class="p-2 text-dark" href="${pageContext.request.contextPath}/inscription">S'inscrire</a> 
					<a class="p-2 text-dark" href="${pageContext.request.contextPath}/connexion">Se connecter</a>
				</nav>
			</c:when>
			<c:otherwise>
				<nav class="my-2 my-md-0 mr-md-3">
         			<a class="p-2 text-dark" href="#">Enchères</a>
          			<a class="p-2 text-dark" href="${pageContext.request.contextPath}/nouvelleVente">Vendre un article</a>
          			<a class="p-2 text-dark" href="${pageContext.request.contextPath}/ProfilUtilisateur">Mon profil</a>
          			<a class="p-2 text-dark" href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a>
        		</nav>
			</c:otherwise>
		</c:choose>
	</div>
</header>
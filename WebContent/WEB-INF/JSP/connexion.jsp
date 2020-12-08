<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://bootswatch.com/4/minty/bootstrap.min.css">
<title>Connexion troc Enchere</title>
</head>
<body>
	<header>
		<div
			class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-primary border-bottom shadow-sm">
			<h5 class="my-0 mr-md-auto font-weight-normal"><a class="p-2 text-dark" href="${pageContext.request.contextPath}/">ENI-Enchères</a></h5>
		</div>
		<c:if test="${!empty erreurs}">
			<div class="alert alert-danger" role="alert">
			  <strong>Erreur!</strong>
			  <ul>
			  	<c:forEach var="erreur" items="${erreurs}">
			  		<li>${erreur}</li>
			  	</c:forEach>
			  </ul>
			</div>
		</c:if>
	</header>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-md-auto">
				<form action="${pageContext.request.contextPath}/connexion" method="POST">
					<div class="row mt-5">
						<div class="col">
							<label for="identifiant">Identifiant : </label>
						</div>
						<div class="col">
							<input type="text" id="identifiant" name="identifiant" class="form-control" placeholder="toto35" required autofocus>
						</div>
					</div>
					<div class="row mt-3">
						<div class="col">
							<label for="inputPassword">Mot de passe : </label>
						</div>
						<div class="col">
							<input type="password" id="password" name="password" class="form-control" placeholder="******" required>
						</div>
					</div>
					<div class="row mt-3">
						<div class="col">
							<button class="btn btn-lg btn-outline-primary btn-block" type="submit">Connexion</button>
						</div>
						<div class="col">
							<input type="checkbox" value="remember-me"> Se souvenir de moi
							<div class="mt-2">
								<a href="" target="_blank">Mot de passe oublié</a>
							</div>
						</div>
					</div>
				</form>
					<div class="row m-3">
						<a href="${pageContext.request.contextPath}/inscription" class="btn btn-lg btn-outline-info btn-block" role="button">Inscription</a>
					</div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
		crossorigin="anonymous"></script>
</body>
</html>
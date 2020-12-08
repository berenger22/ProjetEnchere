<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://bootswatch.com/4/minty/bootstrap.min.css">
<title>Mon profil troc Enchere</title>
</head>
<body>
	<header>
		<div
			class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-primary border-bottom shadow-sm">
			<h5 class="my-0 mr-md-auto font-weight-normal">
				<a class="p-2 text-dark" href="${pageContext.request.contextPath}/">ENI-Enchères</a>
			</h5>
		</div>
	</header>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-6 col-md-4">
				<div class=" pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
					<h2><c:out value="${utilisateur.pseudo}"/></h2>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h4>Nom :</h4>
					</div>
					<div class="col-md-6">
						<p><c:out value="${utilisateur.nom}"/></p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h4>Prénom :<h4>
					</div>
					<div class="col-md-6">
						<p><c:out value="${utilisateur.prenom}"/></p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h4>Email :</h4>
					</div>
					<div class="col-md-6">
						<p><c:out value="${utilisateur.email}"/></p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h4>Téléphone :</h4>
					</div>
					<div class="col-md-6">
						<p><c:out value="${utilisateur.telephone}"/></p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h4>Rue :</h4>
					</div>
					<div class="col-md-6">
						<p><c:out value="${utilisateur.rue}"/></p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h4>Code postal :</h4>
					</div>
					<div class="col-md-6">
						<p><c:out value="${utilisateur.codePostal}"/></p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h4>Ville :</h4>
					</div>
					<div class="col-md-6">
						<p><c:out value="${utilisateur.ville}"/></p>
					</div>
				</div>
				<c:if test="${utilisateur.noUtilisateur == sessionScope.utilisateur.noUtilisateur }">
				<div class="row">
					<div class="col-md-6">
						<h4>Mon crédit :</h4>
					</div>
					<div class="col-md-6">
						<p><c:out value="${utilisateur.credit}"/></p>
					</div>
				</div>
				</c:if>
				<c:if test="${utilisateur.noUtilisateur == sessionScope.utilisateur.noUtilisateur }">
					<div class="row justify-content-md-center">
					<div class="form-group col-md-6">
					<a href="${pageContext.request.contextPath}/modification" class="btn btn-lg btn-outline-info btn-block" role="submit">Modifier</a>
					</div>
				</div>
				</c:if>
				<div class="row justify-content-md-center">
					<div class="form-group col-md-6">
						<a class="btn btn-lg btn-outline-secondary btn-block" href="${pageContext.request.contextPath}/" role="button">Accueil</a>
					</div>
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
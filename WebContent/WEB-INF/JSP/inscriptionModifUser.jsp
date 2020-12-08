<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://bootswatch.com/4/minty/bootstrap.min.css">
<title>Inscription troc Enchere</title>
</head>
<body>
	<header>
		<div
			class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-primary border-bottom shadow-sm">
			<h5 class="my-0 mr-md-auto font-weight-normal">
				<a class="p-2 text-dark" href="${pageContext.request.contextPath}/">ENI-Enchères</a>
			</h5>
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
				<c:choose>
					<c:when test="${empty utilisateur.noUtilisateur }">
						<form action="${pageContext.request.contextPath}/inscription"
							method="POST">
					</c:when>
					<c:otherwise>
						<form action="${pageContext.request.contextPath}/modification"
							method="POST">
							<input type="hidden"
								value="<c:out value="${utilisateur.noUtilisateur}"/>"
								name="noUtilisateur" />
					</c:otherwise>
				</c:choose>
				<div
					class=" pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
					<h2>Mon profil</h2>
				</div>
				<div class="row mt-5">
					<div class="form-group col-md-2">
						<label for="pseudo">Pseudo :</label>
					</div>
					<div class="form-group col-md-4">
						<input type="text" id="pseudo" name="pseudo"
							value="<c:out value="${utilisateur.pseudo}"/>"
							class="form-control" maxlength="30" required autofocus>
					</div>
					<div class="form-group col-md-2">
						<label for="nom">Nom :</label>
					</div>
					<div class="form-group col-md-4">
						<input type="text" id="nom" name="nom"
							value="<c:out value="${utilisateur.nom}"/>" class="form-control"
							maxlength="30" required>
					</div>
				</div>
				<div class="row mt-3">
					<div class="form-group col-md-2">
						<label for="prenom">Prénom :</label>
					</div>
					<div class="form-group col-md-4">
						<input type="text" id="prenom" name="prenom"
							value="<c:out value="${utilisateur.prenom}"/>"
							class="form-control" maxlength="30" required>
					</div>
					<div class="form-group col-md-2">
						<label for="email">Email :</label>
					</div>
					<div class="form-group col-md-4">
						<input type="email" id="email" name="email"
							value="<c:out value="${utilisateur.email}"/>"
							class="form-control" maxlength="30" required>
					</div>
				</div>
				<div class="row mt-3">
					<div class="form-group col-md-2">
						<label for="tel">Téléphone:</label>
					</div>
					<div class="form-group col-md-4">
						<input type="text" id="tel" name="tel"
							value="<c:out value="${utilisateur.telephone}"/>"
							class="form-control"
							pattern="[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}">
					</div>
					<div class="form-group col-md-2">
						<label for="rue">Rue :</label>
					</div>
					<div class="form-group col-md-4">
						<input type="text" id="rue" name="rue"
							value="<c:out value="${utilisateur.rue}"/>" class="form-control"
							maxlength="30" required>
					</div>
				</div>
				<div class="row mt-3">
					<div class="form-group col-md-2">
						<label for="cp">Code postal:</label>
					</div>
					<div class="form-group col-md-4">
						<input type="text" id="cp" name="cp"
							value="<c:out value="${utilisateur.codePostal}"/>"
							class="form-control" max="99999" pattern="[0-9]{5}" required>
					</div>
					<div class="form-group col-md-2">
						<label for="rue">Ville :</label>
					</div>
					<div class="form-group col-md-4">
						<input type="text" id="ville" name="ville"
							value="<c:out value="${utilisateur.ville}"/>"
							class="form-control" maxlength="30" required>
					</div>
				</div>
				<div class="row mt-3">
					<div class="form-group col-md-2">
						<label for="mdp">Mot de passe:</label>
					</div>
					<div class="form-group col-md-4">
						<input type="password" id="mdp" name="mdp" class="form-control"
							required>
					</div>
					<div class="form-group col-md-2">
						<label for="comfirmeMdp">Confirmation:</label>
					</div>
					<div class="form-group col-md-4">
						<input type="password" id="comfirmeMdp" name="comfirmeMdp"
							class="form-control" required>
					</div>
				</div>
				<div class="row justify-content-md-center">
					<div class="form-group col-md-6">
						<button class="btn btn-lg btn-outline-primary btn-block" type="submit">${empty utilisateur.noUtilisateur ? "Créer" : "Modifier"}</button>
					</div>
				</div>
				</form>
				<div class="row justify-content-md-center">
					<div class="form-group col-md-6">
						<a class="btn btn-lg btn-outline-secondary btn-block" href="${pageContext.request.contextPath}/" role="button">Annuler</a>
					</div>
				</div>
				<c:if test="${!empty utilisateur.noUtilisateur}">
					<div class="row justify-content-md-center">
					<div class="form-group col-md-6">
							<button type="button" class="btn btn-lg btn-outline-warning btn-block" data-toggle="modal"
								data-target="#staticBackdrop">Supprimer profil</button>
					</div>
				</div>
				</c:if>

				<!-- Modal -->
				<div class="modal fade" id="staticBackdrop" data-backdrop="static"
					data-keyboard="false" tabindex="-1"
					aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="staticBackdropLabel">Suppression du profil</h5>
							</div>
							<div class="modal-body">Etes-vous sur de vouloir supprimer votre profil de Troc Enchère ?</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Annuler</button>
								<a role="button" class="btn btn-outline-primary" href="${pageContext.request.contextPath}/suppressionUtilisateur">Comfirmer</a>
							</div>
						</div>
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
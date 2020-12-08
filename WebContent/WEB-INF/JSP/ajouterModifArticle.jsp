<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://bootswatch.com/4/minty/bootstrap.min.css">
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
					<c:when test="${empty article.noArticle }">
						<form action="${pageContext.request.contextPath}/nouvelleVente" method="POST">
					</c:when>
					<c:otherwise>
						<form action="${pageContext.request.contextPath}/modificationVente" method="POST">
							<input type="hidden" value="<c:out value="${article.noArticle}"/>" name="noArticle" />
					</c:otherwise>
				</c:choose>
				<div
					class=" pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
					<h2>${empty article.noArticle ? 'Nouvelle Vente' :'Ma vente' }</h2>
				</div>
				<div class="row mt-5">
					<div class="form-group col-md-6 text-center">
						<label for="nomArticle">Article :</label>
					</div>
					<div class="form-group col-md-6">
						<input type="text" id="nomArticle" name="nomArticle"
							value="<c:out value="${article.nomArticle}"/>"
							class="form-control" maxlength="30" required autofocus>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6 text-center">
						<label for="description">Description :</label>
					</div>
					<div class="form-group col-md-6">
						<textarea class="form-control" id="description" name="description"
							rows="3" value="<c:out value="${article.description}"/>"
							maxlength="30" required></textarea>
					</div>
				</div>
				<div class="row mt-3">
					<div class="form-group col-md-6 text-center">
						<label for="categorie">Catégorie :</label>
					</div>
					<div class="form-group col-md-6">
						<select class="form-control" id="catégorie" name="categorie">
							<c:forEach var="categorie" items="${sessionScope.categories}">
								<option value="<c:out value="${categorie.noCategorie}"/>"
								${categorie.noCategorie == article.categorie.noCategorie? 'selected' : '' }>${categorie.libelle}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row mt-3">
					<div class="form-group col-md-6 text-center">
						<label for="image">Photo de l'article :</label>
					</div>
					<div class="form-group col-md-6">
						<input type="file" class="form-control-file" id="image"
							name="image" aria-describedby="fileHelp">
					</div>
				</div>
				<div class="row mt-3">
					<div class="form-group col-md-6 text-center">
						<label for="miseAPrix">mise à prix :</label>
					</div>
					<div class="form-group col-md-6">
						<input type="number" id="miseAPrix" name="miseAPrix"
							value="<c:out value="${article.miseAPrix}"/>"
							class="form-control">
					</div>
				</div>
				<div class="row mt-3">
					<div class="form-group col-md-6 text-center">
						<label for="dateDebutEncheres">Début de l'enchère :</label>
					</div>
					<div class="form-group col-md-6">
						<input type="date" id="dateDebutEncheres" name="dateDebutEncheres"
							value="<c:out value="${article.dateDebutEncheres}"/>"
							class="form-control" required> <i class="icon-date"></i>
					</div>
				</div>
				<div class="row mt-3">
					<div class="form-group col-md-6 text-center">
						<label for="dateFinEncheres">Fin de l'enchère :</label>
					</div>
					<div class="form-group col-md-6">
						<input type="date" id="dateFinEncheres" name="dateFinEncheres"
							value="<c:out value="${article.dateFinEncheres}"/>"
							class="form-control" required> <i class="icon-date"></i>
					</div>
				</div>
				<fieldset class="border p-2">
					<legend class="w-auto">Retrait</legend>
					<div class="row mt-3">
						<div class="form-group col-md-6 text-center">
							<label for="rue">Rue:</label>
						</div>
						<div class="form-group col-md-6">
							<input type="text" id="rue" name="rue"
								value="<c:out value="${utilisateur.rue}"/>"
								class="form-control" required>
						</div>
					</div>
					<div class="row mt-3">
						<div class="form-group col-md-6 text-center">
							<label for="cp">Code postal:</label>
						</div>
						<div class="form-group col-md-6">
							<input type="text" id="cp" name="cp"
								value="<c:out value="${utilisateur.codePostal}"/>"
								class="form-control" max="99999" pattern="[0-9]{5}" required>
						</div>
					</div>
					<div class="row mt-3">
						<div class="form-group col-md-6 text-center">
							<label for="rue">Ville :</label>
						</div>
						<div class="form-group col-md-6">
							<input type="text" id="ville" name="ville"
								value="<c:out value="${utilisateur.ville}"/>"
								class="form-control" maxlength="30" required>
						</div>
					</div>
				</fieldset>

				<div class="row justify-content-md-center mt-3">
					<div class="form-group col-md-6">
						<button class="btn btn-lg btn-outline-primary btn-block"
							type="submit">${empty article.noArticle ? "Enregistrer" : "Modifier"}</button>
					</div>
				</div>
				</form>
				<div class="row justify-content-md-center">
					<div class="form-group col-md-6">
						<a class="btn btn-lg btn-outline-secondary btn-block"
							href="${pageContext.request.contextPath}/" role="button">Annuler</a>
					</div>
				</div>
				<c:if test="${!empty article.noArticle}">
					<div class="row justify-content-md-center">
						<div class="form-group col-md-6">
							<button type="button"
								class="btn btn-lg btn-outline-warning btn-block"
								data-toggle="modal" data-target="#staticBackdrop">Supprimer
								article</button>
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
								<h5 class="modal-title" id="staticBackdropLabel">Suppression
									de l'article</h5>
							</div>
							<div class="modal-body">Etes-vous sur de vouloir supprimer
								votre article de Troc Enchère ?</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-outline-secondary"
									data-dismiss="modal">Annuler</button>
								<a role="button" class="btn btn-outline-primary"
									href="${pageContext.request.contextPath}/suppressionArticle">Comfirmer</a>
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
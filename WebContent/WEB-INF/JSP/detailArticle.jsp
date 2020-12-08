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
<title>Détail vente troc Enchere</title>
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
			<div class="col-12 col-md-6">
				<div
					class=" pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
					<h2>Détail vente</h2>
				</div>
				<div class="row">
					<div class="col-md-6 text-center">
						<h2 class="text-info"><u>${article.nomArticle}</u></h2>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h4>
							Description :
							<h4>
					</div>
					<div class="col-md-6">
						<p>
							<c:out value="${article.description}" />
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h4>Catégorie :</h4>
					</div>
					<div class="col-md-6">
						<p>
							<c:out value="${article.categorie.libelle}" />
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h4>Meilleur offre :</h4>
					</div>
					<div class="col-md-6">
						<p>
							<c:out value="${article.miseAPrix}" />
							pts par "nom acheteur"
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h4>Mise à prix :</h4>
					</div>
					<div class="col-md-6">
						<p>
							<c:out value="${article.miseAPrix}" />
							points
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h4>Retrait :</h4>
					</div>
					<div class="col-md-6">
						<p><c:out value="${article.vendeur.rue} "/><c:out value="${article.vendeur.codePostal} "/>
						<c:out value="${article.vendeur.ville}"/></p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h4>Vendeur :</h4>
					</div>
					<div class="col-md-6">
						<p>
							<c:out value="${article.vendeur.pseudo}" />
						</p>
					</div>
				</div>
				<div class="row">
				<form class="form-inline">
  					<div class="form-group ml-3">
    					<h4>Ma proposition :</h4>
  					</div>
  					<div class="form-group mx-sm-3 mb-2">
					    <input type="number" class="form-control" id="proposition" name="proposition" value="<c:out value="${article.miseAPrix}"/>">
  					</div>
  						<button class="btn btn-outline-info " type="submit">Enchérir</button>
					</form>
				</div>
				<c:if test="${article.vendeur.noUtilisateur == sessionScope.utilisateur.noUtilisateur}">
					<div class="row justify-content-md-center">
						<div class="col-md-6">
							<a href="${pageContext.request.contextPath}/modificationVente"
								class="btn btn-lg btn-outline-info btn-block" role="submit">Modifier article</a>
						</div>
					</div>
				</c:if>
				<div class="row justify-content-md-center">
					<div class="col-md-6 m-3">
						<a class="btn btn-lg btn-outline-secondary btn-block"
							href="${pageContext.request.contextPath}/" role="button">Accueil</a>
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
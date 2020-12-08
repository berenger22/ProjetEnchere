<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form method="post" action="${pageContext.request.contextPath}/rechercheArticle">
	<div class="row">
		<div class="col-md-4 order-md-2 mb-4">
			<div class="text-center">
				<button type="submit" class="btn btn-lg btn-outline-secondary">Rechercher</button>
			</div>
		</div>
		<div class="col-md-8 order-md-1">
			<h4 class="mb-3">Filtres</h4>
			<div class="mb-3">
				<input type="text" class="form-control" id="nomArticle"
					name="nomArticle" value="<c:out value="${sessionScope.filtreArticle}"/>"placeholder="Le nom de l'article">
			</div>
			<div class="row">
				<label for="categorie" class="col-sm-2 col-form-label">Catégorie
				</label>
				<div class="col-sm-10">
					<select class="custom-select d-block w-100" id="categorie" name="categorie">
						<c:forEach var="categorie" items="${categories}">
							<option value="<c:out value="${categorie.noCategorie}"/>"
								${categorie.noCategorie == sessionScope.noCategorie? 'selected' : '' }>${categorie.libelle}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<c:if test="${!empty sessionScope.sessionUser}">
				<div class="row">
					<div class="col-md-5 mb-3">
						<div class="form-check">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" name="optionsRadios"
								id="optionsRadios1" value="option1" checked=""> Mes achats
							</label>
						</div>
						<div class="form-check ml-5">
							<label class="form-check-label"> <input
								class="form-check-input" type="checkbox" value="">enchères ouvertes
							</label>
						</div>
						<div class="form-check ml-5">
							<label class="form-check-label"> <input
								class="form-check-input" type="checkbox" value=""> mes enchères en cours
							</label>
						</div>
						<div class="form-check ml-5">
							<label class="form-check-label"> <input
								class="form-check-input" type="checkbox" value=""> mes enchères remportées
							</label>
						</div>
					</div>
					<div class="col-md-5 mb-3">
						<div class="form-check">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" name="optionsRadios"
								id="optionsRadios1" value="option1"> Mes ventes
							</label>
						</div>
						<div class="form-check ml-5">
							<label class="form-check-label"> <input
								class="form-check-input" type="checkbox" value=""> mes
								ventes en cours
							</label>
						</div>
						<div class="form-check ml-5">
							<label class="form-check-label"> <input
								class="form-check-input" type="checkbox" value="">
								ventes non débutées
							</label>
						</div>
						<div class="form-check ml-5">
							<label class="form-check-label"> <input
								class="form-check-input" type="checkbox" value=""> vente
								terminées
							</label>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</form>
package fr.byoim.encheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.byoim.encheres.bo.ArticleVendu;
import fr.byoim.encheres.bo.EtatVente;
import fr.byoim.encheres.dal.DALException;
import fr.byoim.encheres.dal.DAOFactory;
import fr.byoim.encheres.dal.dao.ArticleVenduDAO;

public class ArticleVenduManager {
	private ArticleVenduDAO articleDAO;

	public ArticleVenduManager() {
		this.articleDAO = DAOFactory.getArticleVenduDAO();
	}

	public List<ArticleVendu> selectionnerTousLesArticles() throws DALException {
		return this.articleDAO.selectAllArticle();
	}

	public List<ArticleVendu> selectionnerTousLesArticlesParCategorie(int noCategorie) throws DALException {
		return this.articleDAO.selectAllArticleByCategorie(noCategorie);
	}

	public List<ArticleVendu> selectionnerTousLesArticlesParCategorieEtParNom(int noCategorie, String filtreArticle)
			throws DALException {
		return this.articleDAO.selectAllArticleByCategorieAndFiltre(noCategorie, filtreArticle);
	}

	public ArticleVendu detailUnArticle(int noArticle) throws DALException {
		return this.articleDAO.selectByIdArticle(noArticle);
	}

	public List<ArticleVendu> selectionnerTousLesArticlesEnCours() throws DALException {
		return this.articleDAO.selectAllArticleEnCours();
	}

	public void ajouterUnArticle(ArticleVendu article) throws DALException {
		this.articleDAO.insertArticle(article);
	}

	public void modifierArticle(ArticleVendu article) throws DALException {
		this.articleDAO.updateArticle(article);
	}

	public void supprimerArticle(int noArticle) throws DALException {
		this.articleDAO.deleteUser(noArticle);
	}

	// Méthode pour retourner que les produits en cours
	public List<ArticleVendu> articleVenduEnCours(List<ArticleVendu> listeArticles) throws BLLException {
		List<ArticleVendu> listeArticlesEnCours = new ArrayList<ArticleVendu>();
		for (ArticleVendu a : listeArticles) {
			if (a.getEtatVente() == EtatVente.EN_COURS) {
				listeArticlesEnCours.add(a);
			}
		}
		return listeArticlesEnCours;

	}

	/* Verification du nom de l'article */
	public void validationNomArticle(String nomArticle) throws BLLException {
		if (nomArticle != null && nomArticle.length() >= 30) {
			throw new BLLException("Veuillez saisir un nom d'article qui contient moins de 30 caractères.");
		}
	}

	/* Verification de la description de l'article */
	public void validationDescription(String description) throws BLLException {
		if (description == null) {
			throw new BLLException("Veuillez saisir une description de l'article en vente.");
		}
	}

	/* Verification que la date de début ne soit pas antérieur à la date du jour */
	public void validerDateDebut(LocalDate dateDebut) throws BLLException {
		if (dateDebut == null || dateDebut.isBefore(LocalDate.now())) {
			throw new BLLException("Veuillez saisir une date de début supérieur à la date d'aujourd'hui.");
		}
	}

	/*
	 * Verification que la date de fin soit supérieur à la date de debut de la vente
	 */
	public void validerFinDebut(LocalDate dateDebut, LocalDate dateFin) throws BLLException {
		if (dateFin == null || dateFin.isBefore(dateDebut)) {
			throw new BLLException("Veuillez saisir une date de fin supérieur à la date de début de la vente.");
		}
	}
}

package fr.byoim.encheres.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleVendu implements Serializable {

	private static final long serialVersionUID = 1L;

	private int noArticle, miseAPrix, prixVente, noUtilisateur, noCategorie;
	private String nomArticle, description, image;
	private LocalDate dateDebutEncheres, dateFinEncheres;
	private Utilisateur acheteur;
	private Utilisateur vendeur;
	private Categorie categorie;
	private EtatVente etatVente;
	List<Enchere> listeEnrechisseur = new ArrayList<Enchere>();
	List<Retrait> listePointRetrait = new ArrayList<Retrait>();

	/**
	 * Constructeur à vide
	 */
	public ArticleVendu() {

	}

	/*
	 * Constructeur avec toutes les variables.
	 */
	public ArticleVendu(int noArticle, int miseAPrix, int prixVente, String nomArticle, String description,
			LocalDate dateDebutEncheres, LocalDate dateFinEncheres, Utilisateur acheteur, Utilisateur vendeur,
			Categorie categorie) {
		super();
		this.noArticle = noArticle;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.acheteur = acheteur;
		this.vendeur = vendeur;
		this.categorie = categorie;
	}

	/*
	 * Constructeur sans l'id de l'aticle.
	 */
	public ArticleVendu(int miseAPrix, int prixVente, String nomArticle, String description,
			LocalDate dateDebutEncheres, LocalDate dateFinEncheres, Utilisateur acheteur, Utilisateur vendeur,
			Categorie categorie) {
		super();
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.acheteur = acheteur;
		this.vendeur = vendeur;
		this.categorie = categorie;
	}

	public ArticleVendu(int miseAPrix, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int noUtilisateur, int noCategorie) {
		super();
		this.miseAPrix = miseAPrix;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public Utilisateur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public EtatVente getEtatVente() {
		EtatVente etat = EtatVente.EN_ATTENTE;
		if (dateDebutEncheres != null) {
			if (LocalDate.now().isBefore(dateDebutEncheres)) {
				etat = EtatVente.EN_ATTENTE;
			} else if (dateFinEncheres != null) {
				if (LocalDate.now().isBefore(dateFinEncheres)) {
					etat = EtatVente.EN_COURS;
				} else {
					etat = EtatVente.TERMINEE;
				}
			}
		}
		return etat;
	}

	public void setEtatVente(EtatVente etatVente) {
		this.etatVente = etatVente;
	}

	public List<Enchere> getListeEnrechisseur() {
		return listeEnrechisseur;
	}

	public void setListeEnrechisseur(List<Enchere> listeEnrechisseur) {
		this.listeEnrechisseur = listeEnrechisseur;
	}

	public List<Retrait> getListePointRetrait() {
		return listePointRetrait;
	}

	public void setListePointRetrait(List<Retrait> listePointRetrait) {
		this.listePointRetrait = listePointRetrait;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", miseAPrix=" + miseAPrix + ", prixVente=" + prixVente
				+ ", noUtilisateur=" + noUtilisateur + ", noCategorie=" + noCategorie + ", nomArticle=" + nomArticle
				+ ", description=" + description + ", image=" + image + ", dateDebutEncheres=" + dateDebutEncheres
				+ ", dateFinEncheres=" + dateFinEncheres + ", acheteur=" + acheteur + ", vendeur=" + vendeur
				+ ", categorie=" + categorie + ", etatVente=" + etatVente + ", listeEnrechisseur=" + listeEnrechisseur
				+ ", listePointRetrait=" + listePointRetrait + "]";
	}

}

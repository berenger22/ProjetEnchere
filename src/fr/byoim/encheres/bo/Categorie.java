package fr.byoim.encheres.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Categorie implements Serializable {

	private static final long serialVersionUID = 1L;
	private int noCategorie;
	private String libelle;
	private List<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();

	public Categorie() {

	}

	/**
	 * Constructeur avec tout les champs
	 * 
	 * @param noCategorie
	 * @param libelle
	 */
	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}

	/**
	 * Constructeur seulement avec le libelle
	 * 
	 * @param libelle
	 */
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<ArticleVendu> getListeArticle() {
		return listeArticle;
	}

	public void setListeArticle(List<ArticleVendu> listeArticle) {
		this.listeArticle = listeArticle;
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", listeArticle=" + listeArticle
				+ "]";
	}

}

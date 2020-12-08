package fr.byoim.encheres.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Retrait implements Serializable {

	private static final long serialVersionUID = 1L;
	private String rue, codePostal, ville;
	private List<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();

	public Retrait() {

	}

	public Retrait(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<ArticleVendu> getListeArticle() {
		return listeArticle;
	}

	public void setListeArticle(List<ArticleVendu> listeArticle) {
		this.listeArticle = listeArticle;
	}

	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + ", listeArticle="
				+ listeArticle + "]";
	}
}

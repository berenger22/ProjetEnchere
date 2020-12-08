package fr.byoim.encheres.dal.dao;

import java.util.List;

import fr.byoim.encheres.bo.ArticleVendu;
import fr.byoim.encheres.dal.DALException;

public interface ArticleVenduDAO {

	public ArticleVendu selectByIdArticle(int noArticle) throws DALException;

	public List<ArticleVendu> selectAllArticle() throws DALException;

	public List<ArticleVendu> selectAllArticleEnCours() throws DALException;

	public void insertArticle(ArticleVendu article) throws DALException;

	public void updateArticle(ArticleVendu article) throws DALException;

	public void deleteUser(int noArticle) throws DALException;

	public List<ArticleVendu> selectAllArticleByCategorie(int noCategorie) throws DALException;

	public List<ArticleVendu> selectAllArticleByCategorieAndFiltre(int noCategorie, String filtreArticle)
			throws DALException;

}

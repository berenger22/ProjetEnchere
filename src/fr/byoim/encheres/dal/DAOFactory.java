package fr.byoim.encheres.dal;

import fr.byoim.encheres.dal.dao.ArticleVenduDAO;
import fr.byoim.encheres.dal.dao.CategorieDAO;
import fr.byoim.encheres.dal.dao.UtilisateurDAO;
import fr.byoim.encheres.dal.jdbc.ArticleVenduDAOJdbcImpl;
import fr.byoim.encheres.dal.jdbc.CategorieDAOJdbcImpl;
import fr.byoim.encheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public abstract class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}

	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOJdbcImpl();
	}
}

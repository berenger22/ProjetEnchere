package fr.byoim.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.byoim.encheres.bo.ArticleVendu;
import fr.byoim.encheres.bo.Categorie;
import fr.byoim.encheres.bo.Utilisateur;
import fr.byoim.encheres.dal.ConnectionProvider;
import fr.byoim.encheres.dal.DALException;
import fr.byoim.encheres.dal.dao.ArticleVenduDAO;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private final String SELECT_ALL_ARTICLE = "select a.no_article, a.nom_article, a.description, a.image, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, u.no_utilisateur, u.pseudo from ARTICLES_VENDUS a"
			+ " INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur;";

	private final String SELECT_ALL_ARTICLE_BY_CATEGORIE = "select a.no_article, a.nom_article, a.description, a.image, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial,"
			+ " u.no_utilisateur, u.pseudo, u.rue, u.code_postal, u.ville, c.no_categorie, c.libelle from UTILISATEURS u  inner join ARTICLES_VENDUS a on a.no_utilisateur = u.no_utilisateur"
			+ " inner join CATEGORIES c on c.no_categorie = a.no_categorie where c.no_categorie = ?;";

	private final String SELECT_ALL_ARTICLE_BY_CATEGORIE_AND_FILTRE = "select a.no_article, a.nom_article, a.description, a.image, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial,"
			+ " u.no_utilisateur, u.pseudo, u.rue, u.code_postal, u.ville, c.no_categorie, c.libelle from UTILISATEURS u  inner join ARTICLES_VENDUS a on a.no_utilisateur = u.no_utilisateur"
			+ " inner join CATEGORIES c on c.no_categorie = a.no_categorie where c.no_categorie = ? and a.nom_article like ?;";

	private final String SELECT_BY_ID_ARTICLE = "select a.no_article, a.nom_article, a.description, a.image, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial,"
			+ " u.no_utilisateur, u.pseudo, u.rue, u.code_postal, u.ville, c.no_categorie, c.libelle from UTILISATEURS u  inner join ARTICLES_VENDUS a on a.no_utilisateur = u.no_utilisateur"
			+ " inner join CATEGORIES c on c.no_categorie = a.no_categorie where no_article = ?;";

	private final String INSERT_ARTICLE = "insert into ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie)"
			+ "values (?,?,?,?,?,?,?)";

	private final String SELECT_ALL_ARTICLE_EN_COURS = "select a.no_article, a.nom_article, a.description, a.image, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, u.no_utilisateur, u.pseudo from ARTICLES_VENDUS a"
			+ " INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur where GETDATE() >= a.date_debut_encheres ;";

	private final String UPDATE_ARTICLE = "update ARTICLES_VENDUS set nom_article = ?, description = ?, date_debut_encheres = ?,"
			+ " date_fin_encheres = ?, prix_initial = ?, no_utilisateur = ?, no_categorie = ? where no_article = ?;";

	private final String DELETE_ARTICLE = "delete from ARTICLES_VENDUS where no_article = ?";

	@Override
	public ArticleVendu selectByIdArticle(int noArticle) throws DALException {
		ArticleVendu article = new ArticleVendu();
		Categorie categorie = new Categorie();
		Utilisateur user = new Utilisateur();
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ID_ARTICLE);
			pstmt.setInt(1, noArticle);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setImage(rs.getString("image"));
				article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setMiseAPrix(rs.getInt("prix_initial"));
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				article.setVendeur(user);
				article.setCategorie(categorie);
			}
		} catch (SQLException e) {
			throw new DALException("Problème de recherche de l'article id n° : " + noArticle + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Problème de fermeture de la connexion BDD : " + e.getMessage());
			}
		}

		return article;
	}

	@Override
	public List<ArticleVendu> selectAllArticle() throws DALException {
		List<ArticleVendu> ListeArticles = new ArrayList<ArticleVendu>();
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_ARTICLE);
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu();
				Utilisateur user = new Utilisateur();
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setImage(rs.getString("image"));
				article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setMiseAPrix(rs.getInt("prix_initial"));
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				article.setVendeur(user);
				ListeArticles.add(article);
			}
		} catch (SQLException e) {
			throw new DALException("Problème de recherche de tous les articles : " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Problème de fermeture de la connexion BDD : " + e.getMessage());
			}
		}
		return ListeArticles;
	}

	@Override
	public List<ArticleVendu> selectAllArticleEnCours() throws DALException {
		List<ArticleVendu> ListeArticlesEnCours = new ArrayList<ArticleVendu>();
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_ARTICLE_EN_COURS);
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu();
				Utilisateur user = new Utilisateur();
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setImage(rs.getString("image"));
				article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setMiseAPrix(rs.getInt("prix_initial"));
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				article.setVendeur(user);
				ListeArticlesEnCours.add(article);
			}
		} catch (SQLException e) {
			throw new DALException("Problème de recherche de tous les articles en cours de vente : " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Problème de fermeture de la connexion BDD : " + e.getMessage());
			}
		}
		return ListeArticlesEnCours;
	}

	@Override
	public void insertArticle(ArticleVendu article) throws DALException {
		if (article == null) {
			throw new DALException("Problème d'insertion, l'article est NULL");
		}
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if (article.getNoArticle() == 0) {
			try {
				cnx = ConnectionProvider.getConnection();
				pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, article.getNomArticle());
				pstmt.setString(2, article.getDescription());
				pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
				pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
				pstmt.setInt(5, article.getMiseAPrix());
				pstmt.setInt(6, article.getNoUtilisateur());
				pstmt.setInt(7, article.getNoCategorie());
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					article.setNoArticle(rs.getInt(1));
				}
			} catch (SQLException e) {
				throw new DALException("Problème d'insertion de l'article : " + e.getMessage());
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (cnx != null)
						cnx.close();
				} catch (SQLException e) {
					throw new DALException("Problème de fermeture de la connexion BDD : " + e.getMessage());
				}
			}
		}

	}

	@Override
	public void updateArticle(ArticleVendu article) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_ARTICLE);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
			pstmt.setInt(5, article.getMiseAPrix());
			pstmt.setInt(6, article.getNoUtilisateur());
			pstmt.setInt(7, article.getNoCategorie());
			pstmt.setInt(8, article.getNoArticle());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Problème de modification de l'article : " + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Problème de fermeture de la connexion BDD : " + e.getMessage());
			}
		}
	}

	@Override
	public void deleteUser(int noArticle) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(DELETE_ARTICLE);
			pstmt.setInt(1, noArticle);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Problème de suppression de l'article : " + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Problème de fermeture de la connexion BDD : " + e.getMessage());
			}
		}
	}

	@Override
	public List<ArticleVendu> selectAllArticleByCategorie(int noCategorie) throws DALException {
		List<ArticleVendu> ListeArticles = new ArrayList<ArticleVendu>();
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLE_BY_CATEGORIE);
			pstmt.setInt(1, noCategorie);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu();
				Categorie categorie = new Categorie();
				Utilisateur user = new Utilisateur();
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setImage(rs.getString("image"));
				article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setMiseAPrix(rs.getInt("prix_initial"));
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				article.setVendeur(user);
				article.setCategorie(categorie);
				ListeArticles.add(article);
			}
		} catch (SQLException e) {
			throw new DALException("Problème de recherche des articles par categorie" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Problème de fermeture de la connexion BDD : " + e.getMessage());
			}
		}

		return ListeArticles;
	}

	@Override
	public List<ArticleVendu> selectAllArticleByCategorieAndFiltre(int noCategorie, String filtreArticle)
			throws DALException {
		List<ArticleVendu> ListeArticles = new ArrayList<ArticleVendu>();
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLE_BY_CATEGORIE_AND_FILTRE);
			pstmt.setInt(1, noCategorie);
			pstmt.setString(2, filtreArticle);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu();
				Categorie categorie = new Categorie();
				Utilisateur user = new Utilisateur();
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setImage(rs.getString("image"));
				article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setMiseAPrix(rs.getInt("prix_initial"));
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				article.setVendeur(user);
				article.setCategorie(categorie);
				ListeArticles.add(article);
			}
		} catch (SQLException e) {
			throw new DALException("Problème de recherche des articles par categorie et par filtre " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Problème de fermeture de la connexion BDD : " + e.getMessage());
			}
		}

		return ListeArticles;
	}

}

package fr.byoim.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.byoim.encheres.bo.Categorie;
import fr.byoim.encheres.dal.ConnectionProvider;
import fr.byoim.encheres.dal.DALException;
import fr.byoim.encheres.dal.dao.CategorieDAO;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	private final String SELECT_ALL = "select * from CATEGORIES";
	private final String SELECT_BY_ID = "select * from CATEGORIES where no_categorie = ?";

	@Override
	public Categorie selectByIdCat(int noCategorie) throws DALException {
		Categorie categorie = new Categorie();
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, noCategorie);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
			}
		} catch (Exception e) {
			throw new DALException("Problème de recherche de la catégorie : " + e.getMessage());
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
		return categorie;
	}

	@Override
	public List<Categorie> selectAllCat() throws DALException {
		List<Categorie> ListeCategorie = new ArrayList<Categorie>();
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				ListeCategorie.add(categorie);
			}
		} catch (Exception e) {
			throw new DALException("Problème de recherche de toutes les catégories : " + e.getMessage());
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
		return ListeCategorie;
	}

}

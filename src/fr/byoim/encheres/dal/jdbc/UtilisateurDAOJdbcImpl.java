package fr.byoim.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.byoim.encheres.bo.Utilisateur;
import fr.byoim.encheres.dal.ConnectionProvider;
import fr.byoim.encheres.dal.DALException;
import fr.byoim.encheres.dal.dao.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private final String SELECT_ALL_USER = "select * from UTILISATEURS";
	private final String SELECT_BY_ID_USER = "select * from UTILISATEURS where no_utilisateur = ?";
	private final String INSERT_USER = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe)"
			+ "VALUES(?,?,?,?,?,?,?,?,?)";
	private final String UPDATE_USER = "update UTILISATEURS  set pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?";
	private final String DELETE_USER = "delete from UTILISATEURS where no_utilisateur = ?";
	private final String CONNEXION_USER = "select * from UTILISATEURS where pseudo = ? or email = ? and mot_de_passe = ?";
	private final String VERIF_PSEUDO = "select pseudo from UTILISATEURS where pseudo = ?";
	private final String VERIF_EMAIL = "select email from UTILISATEURS where email = ?";

	@Override
	public Utilisateur selectByIdUser(int noUtilisateur) throws DALException {
		Utilisateur user = new Utilisateur();
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ID_USER);
			pstmt.setInt(1, noUtilisateur);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				user.setAdministrateur(rs.getBoolean("administrateur"));
			}
		} catch (SQLException e) {
			throw new DALException("Problème de recherche de l'utilisateur : " + e.getMessage());
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
		return user;
	}

	@Override
	public List<Utilisateur> selectAllUser() throws DALException {
		List<Utilisateur> ListeUsers = new ArrayList<Utilisateur>();
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_USER);
			while (rs.next()) {
				Utilisateur user = new Utilisateur();
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				user.setAdministrateur(rs.getBoolean("administrateur"));
				ListeUsers.add(user);
			}
		} catch (Exception e) {
			throw new DALException("Problème de recherche de tout les utilisateurs : " + e.getMessage());
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
		return ListeUsers;
	}

	@Override
	public void insertUser(Utilisateur user) throws DALException {
		if (user == null) {
			throw new DALException("Problème d'insertion, l'utilisateur est NULL");
		}
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if (user.getNoUtilisateur() == 0) {
			try {
				cnx = ConnectionProvider.getConnection();
				pstmt = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, user.getPseudo());
				pstmt.setString(2, user.getNom());
				pstmt.setString(3, user.getPrenom());
				pstmt.setString(4, user.getEmail());
				pstmt.setString(5, user.getTelephone());
				pstmt.setString(6, user.getRue());
				pstmt.setString(7, user.getCodePostal());
				pstmt.setString(8, user.getVille());
				pstmt.setString(9, user.getMotDePasse());
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					user.setNoUtilisateur(rs.getInt(1));
				}
			} catch (SQLException e) {
				throw new DALException("Problème d'insertion de l'utilisateur " + e.getMessage());
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
	public void updateUser(Utilisateur user) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_USER);
			pstmt.setString(1, user.getPseudo());
			pstmt.setString(2, user.getNom());
			pstmt.setString(3, user.getPrenom());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getTelephone());
			pstmt.setString(6, user.getRue());
			pstmt.setString(7, user.getCodePostal());
			pstmt.setString(8, user.getVille());
			pstmt.setString(9, user.getMotDePasse());
			pstmt.setInt(10, user.getNoUtilisateur());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Problème de modification de l'utilisateur : " + e.getMessage());
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
	public Utilisateur connexionUser(String identifiant, String password) throws DALException {
		Utilisateur user = new Utilisateur();
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(CONNEXION_USER);
			pstmt.setString(1, identifiant);
			pstmt.setString(2, identifiant);
			pstmt.setString(3, password);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				user.setAdministrateur(rs.getBoolean("administrateur"));
			}
		} catch (SQLException e) {
			throw new DALException(
					"Problème de recherche de l'utilisateur avec son identifiant et son Mdp : " + e.getMessage());
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
		return user;
	}

	@Override
	public void deleteUser(int noUtilisateur) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(DELETE_USER);
			pstmt.setInt(1, noUtilisateur);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Problème de suppression d'un utilisateur : " + e.getMessage());
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
	public String verifPseudo(String pseudo) throws DALException {
		String pseudoVerifier = null;
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(VERIF_PSEUDO);
			pstmt.setString(1, pseudo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pseudoVerifier = rs.getString("pseudo");
			}
		} catch (SQLException e) {
			throw new DALException(
					"Problème de recherche de vérification de recherche d'un pseudo : " + e.getMessage());
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
		return pseudoVerifier;
	}

	@Override
	public String verifEmail(String email) throws DALException {
		String emailVerifier = null;
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(VERIF_EMAIL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				emailVerifier = rs.getString("email");
			}
		} catch (SQLException e) {
			throw new DALException("Problème de recherche de vérification de recherche d'un email : " + e.getMessage());
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
		return emailVerifier;
	}
}

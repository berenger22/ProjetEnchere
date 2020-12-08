package fr.byoim.encheres.dal.dao;

import java.util.List;

import fr.byoim.encheres.bo.Utilisateur;
import fr.byoim.encheres.dal.DALException;

public interface UtilisateurDAO {

	public Utilisateur selectByIdUser(int noUtilisateur) throws DALException;

	public List<Utilisateur> selectAllUser() throws DALException;

	public void insertUser(Utilisateur user) throws DALException;

	public void updateUser(Utilisateur user) throws DALException;

	public Utilisateur connexionUser(String identifiant, String password) throws DALException;

	public void deleteUser(int noUtilisateur) throws DALException;

	public String verifPseudo(String pseudo) throws DALException;

	public String verifEmail(String email) throws DALException;

}

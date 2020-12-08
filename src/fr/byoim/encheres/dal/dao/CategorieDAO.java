package fr.byoim.encheres.dal.dao;

import java.util.List;

import fr.byoim.encheres.bo.Categorie;
import fr.byoim.encheres.dal.DALException;

public interface CategorieDAO {

	public Categorie selectByIdCat(int noCategorie) throws DALException;

	public List<Categorie> selectAllCat() throws DALException;
}

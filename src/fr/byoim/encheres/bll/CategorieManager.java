package fr.byoim.encheres.bll;

import java.util.List;

import fr.byoim.encheres.bo.Categorie;
import fr.byoim.encheres.dal.DALException;
import fr.byoim.encheres.dal.DAOFactory;
import fr.byoim.encheres.dal.dao.CategorieDAO;

public class CategorieManager {
	private CategorieDAO categorieDAO;

	public CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}

	public List<Categorie> selectionnerToutesLesCategories() throws DALException {
		return this.categorieDAO.selectAllCat();
	}
}

package fr.byoim.encheres.bll;

import java.util.List;
import fr.byoim.encheres.bo.Utilisateur;
import fr.byoim.encheres.dal.DALException;
import fr.byoim.encheres.dal.DAOFactory;
import fr.byoim.encheres.dal.dao.UtilisateurDAO;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public List<Utilisateur> selectionnerTousLesUtilisateurs() throws DALException {
		return this.utilisateurDAO.selectAllUser();
	}

	public Utilisateur afficherUnUtilisateur(int noUtilisateur) throws DALException {
		return this.utilisateurDAO.selectByIdUser(noUtilisateur);
	}

	public Utilisateur connexionUtilisateur(String identifiant, String mdp) throws DALException {
		return this.utilisateurDAO.connexionUser(identifiant, mdp);
	}

	public void ajouterUtilisateur(Utilisateur user) throws DALException {
		this.utilisateurDAO.insertUser(user);
	}

	public void modifierUtilisateur(Utilisateur user) throws DALException {
		this.utilisateurDAO.updateUser(user);
	}

	public void supprimerUtilisateur(int noUtilisateur) throws DALException {
		this.utilisateurDAO.deleteUser(noUtilisateur);
	}

	/* Vérification que le mot soit bien en alphanumérique */
	public void alphanumeric(String pseudo) throws BLLException {
		char[] charArray = pseudo.toCharArray();
		for (char c : charArray) {
			if (!Character.isLetterOrDigit(c)) {
				throw new BLLException("Veuillez saisir un pseudo, sans caractères spéciaux.");
			}
		}
	}

	/* Vérification du pseudo */
	public void validationPseudo(String pseudo) throws BLLException {
		if (pseudo != null && pseudo.trim().length() <= 30) {
			String pseudoVerifier;
			try {
				pseudoVerifier = this.utilisateurDAO.verifPseudo(pseudo);
				if (pseudoVerifier != null && pseudoVerifier.equals(pseudo)) {
					throw new BLLException("Veuillez saisir un autre pseudo, celui-ci est déjà pris.");
				}
			} catch (DALException e) {
				e.printStackTrace();
			}
		} else {
			throw new BLLException("Veuillez saisir un pseudo valide.");
		}

	}

	/* Verification du nom */
	public void validationNom(String nom) throws BLLException {
		if (nom != null && nom.length() >= 30) {
			throw new BLLException("Veuillez saisir un nom qui contient moins de 30 caractères.");
		}
	}

	/* Verification du prenom */
	public void validationPrenom(String prenom) throws BLLException {
		if (prenom != null && prenom.length() >= 30) {
			throw new BLLException("Veuillez saisir un prénom qui contient moins de 30 caractères.");
		}
	}

	/* Vérification de l'adresse mail */
	public void validationEmail(String email) throws BLLException {
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new BLLException("Merci de saisir une adresse mail valide.");
			}
			String emailVerifier;
			try {
				emailVerifier = this.utilisateurDAO.verifPseudo(email);
				if (emailVerifier != null && emailVerifier.equals(email)) {
					throw new BLLException("Veuillez saisir un autre email, celle-ci est déjà prise.");
				}
			} catch (DALException e) {
				e.printStackTrace();
			}
		} else {
			throw new BLLException("Merci de saisir une adresse mail.");
		}
	}

	/* Vérification du téléphone */
	public void validationTelephone(String telephone) throws BLLException {
		if (telephone != null && !telephone.isEmpty()) {
			if (!telephone.matches("^\\d+$")) {
				throw new BLLException("Le numéro de téléphone doit uniquement contenir des chiffres.");
			} else if (telephone.length() != 10) {
				throw new BLLException("Le numéro de téléphone doit contenir 10 chiffres.");
			}
		}
	}

	/* Verification de la rue */
	public void validationRue(String rue) throws BLLException {
		if (rue != null && rue.length() >= 30) {
			throw new BLLException("Veuillez saisir une rue qui contient moins de 30 caractères.");
		}
	}

	/* Vérification du code postal */
	public void validationCP(String codePostal) throws BLLException {
		if (codePostal != null) {
			if (!codePostal.matches("^\\d+$")) {
				throw new BLLException("Le code postal doit uniquement contenir des chiffres.");
			} else if (codePostal.length() != 5) {
				throw new BLLException("Le code postal doit contenir 5 chiffres.");
			}
		} else {
			throw new BLLException("Merci d'entrer un code postal.");
		}
	}

	/* Verification de la ville */
	public void validationVille(String ville) throws BLLException {
		if (ville != null && ville.length() >= 30) {
			throw new BLLException("Veuillez saisir une ville qui contient moins de 30 caractères.");
		}
	}

	/* Verification que les mots de passe soit identique */
	public void validationMotDePasse(String motDePasse, String confirmation) throws BLLException {
		if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null
				&& confirmation.trim().length() != 0) {
			if (!motDePasse.equals(confirmation)) {
				throw new BLLException("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
			}
		} else {
			throw new BLLException("Merci de saisir et confirmer votre mot de passe.");
		}
	}

	/* Verification de l'identifiant */
	public void validationLogin(String identifiant) throws BLLException {
		if (identifiant == null) {
			throw new BLLException("Veuillez saisir un identifiant valide.");
		}
	}

	/* Vérification que le mot soit bien en alphanumérique */
	public boolean isAlphanumeric(String str) {
		char[] charArray = str.toCharArray();
		for (char c : charArray) {
			if (!Character.isLetterOrDigit(c))
				return false;
		}
		return true;
	}
}

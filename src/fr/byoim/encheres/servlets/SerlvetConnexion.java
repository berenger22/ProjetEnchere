package fr.byoim.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.byoim.encheres.bll.UtilisateurManager;
import fr.byoim.encheres.bo.Utilisateur;
import fr.byoim.encheres.dal.DALException;

/**
 * Servlet implementation class SerlvetConnexion
 */
@WebServlet("/connexion")
public class SerlvetConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// J'appel mon manager et je récupère les sessions
		UtilisateurManager cm = new UtilisateurManager();
		HttpSession session = request.getSession();
		List<String> erreurs = new ArrayList<String>();
		// Je récupère mes valeurs venant du formulaire de connexion
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("password");
		// Je vérifie dans un premier temps si le'identifiant contient un caractere
		// special selon la reponse, je verifie soit le pseudo ou le mail
		if (cm.isAlphanumeric(identifiant)) {
			try {
				cm.validationLogin(identifiant);
			} catch (Exception e) {
				erreurs.add(e.getMessage());
			}
		} else {
			try {
				cm.validationEmail(identifiant);
			} catch (Exception e) {
				erreurs.add(e.getMessage());
			}
		}
		// Si il n'y aucune erreur je fais ma requete de connexion sion je renvoie les
		// erreurs sur la page de connexion
		if (erreurs.isEmpty()) {
			try {
				Utilisateur utilisateur = cm.connexionUtilisateur(identifiant, mdp);
				// une fois la requete exécutée, je vérifie que le pseudo ou le mail et le mot
				// de passe ne soit pas vide ou correspond
				// à ce que l'utilisateur à saisi. Si c'est ok, je renvoie l'utilisateur dans
				// une session vers la page d'accueil
				if (utilisateur.getPseudo() != null && utilisateur.getPseudo().equals(identifiant)
						&& utilisateur.getMotDePasse() != null && utilisateur.getMotDePasse().equals(mdp)
						|| utilisateur.getEmail() != null && utilisateur.getEmail().equals(identifiant)
								&& utilisateur.getMotDePasse() != null && utilisateur.getMotDePasse().equals(mdp)) {
					session.setAttribute("sessionUser", utilisateur);
					request.setAttribute("utilisateur", utilisateur);
					RequestDispatcher rd = request.getRequestDispatcher("/");
					rd.forward(request, response);
				} else {
					erreurs.add("Identifiant ou mot de passe incorrect !!");
					request.setAttribute("erreurs", erreurs);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/connexion.jsp");
					rd.forward(request, response);
				}
			} catch (DALException e) {
				erreurs.add(e.getMessage());
				session.setAttribute("sessionUser", null);
			}
		} else {
			request.setAttribute("erreurs", erreurs);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/connexion.jsp");
			rd.forward(request, response);
		}
	}

}

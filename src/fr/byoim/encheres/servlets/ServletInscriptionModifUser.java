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
 * Servlet implementation class ServletInscriptionModifUser
 */
@WebServlet(urlPatterns = { "/inscription", "/modification" })
public class ServletInscriptionModifUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Si l'url appel "inscription" ça sera renvoyée vers un formulaire vierge
		// sinon je récupere l'id de l'utilisateur à modifier et je renvoie les infos afin
		// de les afficher sur le formulaire
		if (request.getServletPath().equals("/inscription")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/inscriptionModifUser.jsp");
			rd.forward(request, response);
		} else if (request.getServletPath().equals("/modification")) {
			HttpSession session = request.getSession();
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUser");
			request.setAttribute("utilisateur", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/inscriptionModifUser.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UtilisateurManager cm = new UtilisateurManager();
		List<String> erreurs = new ArrayList<String>();
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUser");

		// Vérification des differents champs avant de créer un objet utilisateur
		//Si l'url est inscription, je verifie le pseudo et le mail parce qu'ils doivent être unique
		
		if (request.getServletPath().equals("/inscription")) {
			try {
				cm.alphanumeric(request.getParameter("pseudo"));
			} catch (Exception e) {
				erreurs.add(e.getMessage());
			}
			try {
				cm.validationPseudo(request.getParameter("pseudo"));
			} catch (Exception e) {
				erreurs.add(e.getMessage());
			}
		}
		try {
			cm.validationNom(request.getParameter("nom"));
		} catch (Exception e) {
			erreurs.add(e.getMessage());
		}
		try {
			cm.validationPrenom(request.getParameter("prenom"));
		} catch (Exception e) {
			erreurs.add(e.getMessage());
		}
		if(request.getServletPath().equals("/inscription")) {
			try {
				cm.validationEmail(request.getParameter("email"));
			} catch (Exception e) {
				erreurs.add(e.getMessage());
			}
		}
		
		try {
			cm.validationTelephone(request.getParameter("tel"));
		} catch (Exception e) {
			erreurs.add(e.getMessage());
		}
		try {
			cm.validationRue(request.getParameter("rue"));
		} catch (Exception e) {
			erreurs.add(e.getMessage());
		}
		try {
			cm.validationCP(request.getParameter("cp"));
		} catch (Exception e) {
			erreurs.add(e.getMessage());
		}
		try {
			cm.validationVille(request.getParameter("ville"));
		} catch (Exception e) {
			erreurs.add(e.getMessage());
		}
		try {
			cm.validationMotDePasse(request.getParameter("mdp"), request.getParameter("comfirmeMdp"));
		} catch (Exception e) {
			erreurs.add(e.getMessage());
		}
		//Si il n'y à pas d'erreur et que l'id de l'utilisateur est null, je crée un nouveau utilisateur sion je modifie celui-ci
		if (erreurs.isEmpty()) {
			if (request.getParameter("noUtilisateur")!=null) {
				try {
					utilisateur.setPseudo(request.getParameter("pseudo"));
					utilisateur.setNom(request.getParameter("nom"));
					utilisateur.setPrenom(request.getParameter("prenom"));
					utilisateur.setEmail(request.getParameter("email"));
					utilisateur.setTelephone(request.getParameter("tel"));
					utilisateur.setRue(request.getParameter("rue"));
					utilisateur.setCodePostal(request.getParameter("cp"));
					utilisateur.setVille(request.getParameter("ville"));
					utilisateur.setMotDePasse(request.getParameter("mdp"));
					cm.modifierUtilisateur(utilisateur);
					session.setAttribute("sessionUser", utilisateur);
					request.setAttribute("utilisateur", utilisateur);
					response.sendRedirect(request.getContextPath());
				} catch (DALException e) {
					erreurs.add(e.getMessage());
				}
			}else {
				Utilisateur newUtilisateur = new Utilisateur(request.getParameter("pseudo"), request.getParameter("nom"),
					request.getParameter("prenom"), request.getParameter("email"), request.getParameter("tel"),
					request.getParameter("rue"), request.getParameter("cp"), request.getParameter("ville"),
					request.getParameter("mdp"));
				try {
					cm.ajouterUtilisateur(newUtilisateur);
					session.setAttribute("sessionUser", newUtilisateur);
					request.setAttribute("utilisateur", newUtilisateur);
					response.sendRedirect(request.getContextPath());
				} catch (DALException e) {
					erreurs.add(e.getMessage());
					session.setAttribute("sessionUser", null);
					request.setAttribute("erreurs", erreurs);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/inscriptionModifUser.jsp");
					rd.forward(request, response);
				}
			}
		} else {
			request.setAttribute("erreurs", erreurs);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/inscriptionModifUser.jsp");
			rd.forward(request, response);
		}
	}

}

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

import fr.byoim.encheres.bll.UtilisateurManager;
import fr.byoim.encheres.bo.Utilisateur;
import fr.byoim.encheres.dal.DALException;

/**
 * Servlet implementation class ServletAfficherUnProfil
 */
@WebServlet("/afficherProfil")
public class ServletAfficherUnProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UtilisateurManager cm = new UtilisateurManager();
		List<String> erreurs = new ArrayList<String>();
		// je récupère l'id du vendeur à afficher via le formulaire dans la cards de
		// l'article
		int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
		if (noUtilisateur > 0) {
			try {
				// je récupère les infos du vendeur et je les renvoie sur la page profil
				Utilisateur vendeur = cm.afficherUnUtilisateur(noUtilisateur);
				if (vendeur.getPseudo() != null && vendeur.getNoUtilisateur() == noUtilisateur) {
					request.setAttribute("utilisateur", vendeur);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/profil.jsp");
					rd.forward(request, response);
				} else {
					erreurs.add("Imposssible d'afficher les informations du vendeur pour le moment.");
					request.setAttribute("erreurs", erreurs);
					response.sendRedirect(request.getContextPath());
				}
			} catch (DALException e) {
				e.printStackTrace();
				erreurs.add("Imposssible d'afficher les informations du vendeur pour le moment.");
				request.setAttribute("erreurs", erreurs);
				response.sendRedirect(request.getContextPath());
			}
		} else {
			erreurs.add("Imposssible d'afficher les informations du vendeur pour le moment.");
			request.setAttribute("erreurs", erreurs);
			response.sendRedirect(request.getContextPath());
		}
	}

}

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
 * Servlet implementation class ServletSuppressionUser
 */
@WebServlet("/suppressionUtilisateur")
public class ServletSuppressionUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> erreurs = new ArrayList<String>();
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNoUtilisateur(-1);
		utilisateur = (Utilisateur) session.getAttribute("sessionUser");
		//j'initialise l'id de l'utilisateur à -1 puis je le récupère via la session et si il est supérieur à 0
		//alors la suppression peux avoir lieu et je vide la session.
		if(utilisateur.getNoUtilisateur() > 0) {
			UtilisateurManager cm = new UtilisateurManager();
			try {
				cm.supprimerUtilisateur(utilisateur.getNoUtilisateur());
				session.invalidate();
			} catch (DALException e) {
				erreurs.add("Impossible de supprimer l'utilisateur pour le moment !!");
			}
		}else {
			erreurs.add("Erreur identifiant de l'utilisateur, impossible de supprimer l'utilisateur !!");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/connexion.jsp");
			rd.forward(request, response);
		}
		request.setAttribute("erreurs", erreurs);
		RequestDispatcher rd = request.getRequestDispatcher("/");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

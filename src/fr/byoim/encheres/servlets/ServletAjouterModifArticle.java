package fr.byoim.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.byoim.encheres.bll.ArticleVenduManager;
import fr.byoim.encheres.bo.ArticleVendu;
import fr.byoim.encheres.bo.Utilisateur;
import fr.byoim.encheres.dal.DALException;

/**
 * Servlet implementation class ServletAjouterModifArticle
 */
@WebServlet(urlPatterns = { "/nouvelleVente", "/modificationVente" })
public class ServletAjouterModifArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAjouterModifArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUser");
		request.setAttribute("utilisateur", utilisateur);
		// Si l'url appel "nouvelleVente" ça sera renvoyée vers un formulaire vierge
		// sinon je récupere l'id de l'article à modifier et je renvoie les infos afin
		// de les afficher dans le formulaire
		if (request.getServletPath().equals("/nouvelleVente")) {
			session.removeAttribute("article");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ajouterModifArticle.jsp");
			rd.forward(request, response);
		} else if (request.getServletPath().equals("/modificationVente")) {
			ArticleVendu article = (ArticleVendu) session.getAttribute("article");
			request.setAttribute("article", article);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ajouterModifArticle.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArticleVenduManager cm = new ArticleVenduManager();
		List<String> erreurs = new ArrayList<String>();
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUser");
		ArticleVendu article = (ArticleVendu) session.getAttribute("article");
		LocalDate dateDebut = null;
		LocalDate dateFin = null;

		// Modifcication du format de la date au format localDate
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dateDebut = LocalDate.parse(request.getParameter("dateDebutEncheres"), dtf);
			dateFin = LocalDate.parse(request.getParameter("dateFinEncheres"), dtf);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			erreurs.add("Problème de transformation de date");
		}

		// Vérification des differents champs avant de créer un objet articleVendu
		try {
			cm.validationNomArticle(request.getParameter("nomArticle"));
		} catch (Exception e) {
			erreurs.add(e.getMessage());
		}
		try {
			cm.validationDescription(request.getParameter("description"));
		} catch (Exception e) {
			erreurs.add(e.getMessage());
		}
		try {
			cm.validerDateDebut(dateDebut);
		} catch (Exception e) {
			erreurs.add(e.getMessage());
		}
		try {
			cm.validerFinDebut(dateDebut, dateFin);
		} catch (Exception e) {
			erreurs.add(e.getMessage());
		}

		if (erreurs.isEmpty()) {
			// Si il n'y ap as d'erreur, si l'article éxiste déja et la vente n'est pas en
			// cours alors je peux modifier mon article
			if (request.getParameter("noArticle") != null) {
				if (dateDebut.isAfter(LocalDate.now()) && dateFin.isBefore(LocalDate.now())) {
					try {
						article.setNomArticle(request.getParameter("nomArticle"));
						article.setDescription(request.getParameter("description"));
						article.setMiseAPrix(Integer.parseInt(request.getParameter("miseAPrix")));
						article.setDateDebutEncheres(dateDebut);
						article.setDateFinEncheres(dateFin);
						article.setNoUtilisateur(utilisateur.getNoUtilisateur());
						article.setNoCategorie(Integer.parseInt(request.getParameter("categorie")));
						cm.modifierArticle(article);
						request.setAttribute("article", article);
						response.sendRedirect(request.getContextPath());
					} catch (DALException e) {
						erreurs.add(e.getMessage());
						request.setAttribute("erreurs", erreurs);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ajouterModifArticle.jsp");
						rd.forward(request, response);
					}
				} else {
					erreurs.add("Vous ne pouvez plus modifier l'article, la vente est en cours !");
					request.setAttribute("erreurs", erreurs);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ajouterModifArticle.jsp");
					rd.forward(request, response);
				}
				// Si l'id de l'article et null alors je crée un nouvelle article et je le
				// rajoute à la bdd
			} else {
				ArticleVendu newArticle = new ArticleVendu(Integer.parseInt(request.getParameter("miseAPrix")),
						request.getParameter("nomArticle"), request.getParameter("description"), dateDebut, dateFin,
						utilisateur.getNoUtilisateur(), Integer.parseInt(request.getParameter("categorie")));
				try {
					cm.ajouterUnArticle(newArticle);
					response.sendRedirect(request.getContextPath());
				} catch (DALException e) {
					erreurs.add(e.getMessage());
					request.setAttribute("utilisateur", utilisateur);
					request.setAttribute("erreurs", erreurs);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ajouterModifArticle.jsp");
					rd.forward(request, response);
				}
			}
		} else {
			request.setAttribute("utilisateur", utilisateur);
			request.setAttribute("erreurs", erreurs);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ajouterModifArticle.jsp");
			rd.forward(request, response);
		}

	}

}

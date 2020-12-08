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

import fr.byoim.encheres.bll.ArticleVenduManager;
import fr.byoim.encheres.bll.BLLException;
import fr.byoim.encheres.bo.ArticleVendu;
import fr.byoim.encheres.dal.DALException;

/**
 * Servlet implementation class ServletFiltreRechercheArticle
 */
@WebServlet("/rechercheArticle")
public class ServletFiltreRechercheArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletFiltreRechercheArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	// Filtre de recherche en mode déconnecté A FINIR !!!
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ArticleVenduManager cm = new ArticleVenduManager();
		List<String> erreurs = new ArrayList<String>();
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		// je récupère l'id de la catégorie
		int noCategorie = Integer.parseInt(request.getParameter("categorie"));
		String filtreArticle = request.getParameter("nomArticle");
		// Si il n'y a pas de mot clé saisie dans la recherche alors il fait une
		// recherche que par catégorie
		if (filtreArticle == "") {
			try {
				// Je récupère tout les articles de la catégorie
				listeArticles = cm.selectionnerTousLesArticlesParCategorie(noCategorie);
				try {
					// Je filtre cette liste pour afficher que les articles en cours d'enchère
					listeArticles = cm.articleVenduEnCours(listeArticles);
				} catch (BLLException e) {
					e.printStackTrace();
				}
				session.setAttribute("noCategorie", noCategorie);
				request.setAttribute("listeArticles", listeArticles);
				RequestDispatcher rd = request.getRequestDispatcher("/");
				rd.forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				erreurs.add("Impossible de récupérer les articles par la catégorie pour le moment.");
				request.setAttribute("erreurs", erreurs);
				response.sendRedirect(request.getContextPath());
			}
		} else {
			try {
				// Je récupère tout les articles de la catégorie et par mot clé
				listeArticles = cm.selectionnerTousLesArticlesParCategorieEtParNom(noCategorie, filtreArticle);
				try {
					// Je filtre cette liste pour afficher que les articles en cours d'enchère
					listeArticles = cm.articleVenduEnCours(listeArticles);
				} catch (BLLException e) {
					e.printStackTrace();
				}
				session.setAttribute("noCategorie", noCategorie);
				request.setAttribute("listeArticles", listeArticles);
				RequestDispatcher rd = request.getRequestDispatcher("/");
				rd.forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				erreurs.add("Impossible de récupérer les articles par la catégorie pour le moment.");
				request.setAttribute("erreurs", erreurs);
				response.sendRedirect(request.getContextPath());
			}

		}
	}
}

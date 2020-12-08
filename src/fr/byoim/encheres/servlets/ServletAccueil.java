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
import fr.byoim.encheres.bll.CategorieManager;
import fr.byoim.encheres.bo.ArticleVendu;
import fr.byoim.encheres.bo.Categorie;
import fr.byoim.encheres.bo.Utilisateur;
import fr.byoim.encheres.dal.DALException;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// Je récupère l'utilisateur de la session
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUser");
		
		// Je crée une liste d'erreurs, d'articles et de catégories et j'appel mon
		// manager des articles et des catégories
		List<String> erreurs = new ArrayList<String>();
		CategorieManager cm = new CategorieManager();
		ArticleVenduManager am = new ArticleVenduManager();
		List<Categorie> listeCategories = new ArrayList<Categorie>();
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		listeArticles = (List<ArticleVendu>) request.getAttribute("listeArticles");
		
		// Je récupère toute les catégories via mon manager qui fait appel à ma DAO
		try {
			listeCategories = cm.selectionnerToutesLesCategories();
		} catch (DALException e) {
			e.printStackTrace();
			erreurs.add("Impossible de récupérer toutes les catégories pour le moment.");
		}
		
		// Si ma liste d'articles est vide je récupère tout les articles en cours
		if (listeArticles == null) {
			try {
				listeArticles = am.selectionnerTousLesArticlesEnCours();
			} catch (DALException e) {
				e.printStackTrace();
				erreurs.add("Impossible de récupérer tous les articles pour le moment.");
			}
		}
		
		// Si ma liste est vide et que je ne suis pas connecté alors je suis redirigé
		// vers la page de connexion des la première connexion
		// sinon j'affcihe la page d'accueil avec les articles en cours de vente.
		if (listeArticles.isEmpty() && utilisateur == null) {
			session.setAttribute("categories", listeCategories);
			request.setAttribute("erreurs", erreurs);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/connexion.jsp");
			rd.forward(request, response);
		} else {
			session.setAttribute("categories", listeCategories);
			request.setAttribute("erreurs", erreurs);
			request.setAttribute("articles", listeArticles);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/index.jsp");
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

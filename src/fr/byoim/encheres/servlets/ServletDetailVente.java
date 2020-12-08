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
import fr.byoim.encheres.bo.ArticleVendu;
import fr.byoim.encheres.bo.Utilisateur;
import fr.byoim.encheres.dal.DALException;

/**
 * Servlet implementation class ServletDetailVente
 */
@WebServlet("/detailVente")
public class ServletDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletDetailVente() {
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
		session.setAttribute("utilisateur", utilisateur);
		// Je récupère l'id de l'article, avant je fait appel à mon manager et je
		// récupère mon utilisateur connecté
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		if (noArticle > 0) {
			try {
				// Je récupère les infos de l'aticle en bdd et si il n'est pas null et l'id
				// correspond bien je l'envoie sur la page jsp détaillant un article
				ArticleVendu article = cm.detailUnArticle(noArticle);
				if (article.getNomArticle() != null && article.getNoArticle() == noArticle) {
					request.setAttribute("article", article);
					session.setAttribute("article", article);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/detailArticle.jsp");
					rd.forward(request, response);
				} else {
					erreurs.add("Imposssible d'afficher les informations du vendeur pour le moment.");
					request.setAttribute("erreurs", erreurs);
					RequestDispatcher rd = request.getRequestDispatcher("/");
					rd.forward(request, response);
				}
			} catch (DALException e) {
				e.printStackTrace();
				erreurs.add("Imposssible d'afficher le détail de l'article pour le moment.");

			}
			// Je n'est pas pu récupérer l'id de l'article
		} else {
			erreurs.add("Imposssible d'afficher le détail de l'article pour le moment.");
			request.setAttribute("erreurs", erreurs);
			RequestDispatcher rd1 = request.getRequestDispatcher("/");
			rd1.forward(request, response);
		}
	}
}

package fr.byoim.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
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
import fr.byoim.encheres.dal.DALException;

/**
 * Servlet implementation class ServletSuppressionArticle
 */
@WebServlet("/suppressionArticle")
public class ServletSuppressionArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> erreurs = new ArrayList<String>();
		ArticleVendu article = new ArticleVendu();
		article.setNoUtilisateur(-1);
		article = (ArticleVendu) session.getAttribute("article");
		//j'initialise l'id de l'article à -1 puis je le récupère via la session et si il est supérieur à 0
		//et que la vente n'est pas en cours, la suppression peux avoir lieu.
		if (article.getNoArticle() > 0) {
			if (article.getDateDebutEncheres().isAfter(LocalDate.now())
					&& article.getDateFinEncheres().isBefore(LocalDate.now())) {
				ArticleVenduManager cm = new ArticleVenduManager();
				try {
					cm.supprimerArticle(article.getNoArticle());
				} catch (DALException e) {
					erreurs.add("Impossible de supprimer l'article pour le moment !!");
					request.setAttribute("erreurs", erreurs);
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/JSP/ajouterModifArticle.jsp");
					rd.forward(request, response);
				}
			} else {
				erreurs.add("Vous ne pouvez pas supprimer l'article, la vente est en cours !");
				request.setAttribute("erreurs", erreurs);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ajouterModifArticle.jsp");
				rd.forward(request, response);
			}
		} else {
			erreurs.add("Erreur identifiant de l'article, impossible de supprimer l'article !!");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ajouterModifArticle.jsp");
			rd.forward(request, response);
		}
		request.setAttribute("erreurs", erreurs);
		RequestDispatcher rd = request.getRequestDispatcher("/");
		rd.forward(request, response);
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

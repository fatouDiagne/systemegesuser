package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Utilisateur;
import dao.UtilisateurDao;


/**
 * Servlet implementation class ModifierUser
 */
@WebServlet("/update")
public class ModifierUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_UPDATE_USER = "/WEB-INF/updateUser.jsp";
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		if( id != null && id.matches("[0-9]+")) {
			Utilisateur utilisateur = UtilisateurDao.getById(Integer.parseInt(id));
			request.setAttribute("utilisateur", utilisateur);
		}
		
		getServletContext().getRequestDispatcher(VUE_UPDATE_USER).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id= request.getParameter("id");
		String nom =request.getParameter("nom"); 
		String prenom =request.getParameter("prenom");
		String login =request.getParameter("login");
		String password =request.getParameter("mdp");
		
		Utilisateur utilisateur = new Utilisateur(Integer.parseInt(id), nom, prenom, login, password);
		try {
			
			UtilisateurDao.modifierUser(utilisateur);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("list");
	}

}

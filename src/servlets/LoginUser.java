package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import dao.UtilisateurDao;

/**
 * Servlet implementation class LoginUser
 */
@WebServlet("/login")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_LOGIN_UTILISATEUR = "/WEB-INF/loginUser.jsp";
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(VUE_LOGIN_UTILISATEUR).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
        String password = request.getParameter("mdp");
        
        Utilisateur user = new Utilisateur(login, password);
       

        try {
            if (UtilisateurDao.loginUser(user)) {
                HttpSession session = request.getSession();
                session.setAttribute("login",login);
                response.sendRedirect("list");
            } else {
                
                response.sendRedirect("login");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}

}

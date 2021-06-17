/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.LoginDao;
import DAO.UserDao;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huygrogbro
 */
@WebServlet(urlPatterns = {"/LoginProcess"})
public class LoginProcess extends HttpServlet {

	private LoginDao loginDAO = new LoginDao();
	private UserDao userDAO = new UserDao();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname= request.getParameter("uname");
		String pass = request.getParameter("pass");
		HttpSession sessionLog= request.getSession();
		if(loginDAO.check(uname, pass) != true) {
			sessionLog.setAttribute("login", "failed");
			response.sendRedirect("Login");
			
		} else {
			if("SITE_USER".equals(loginDAO.checkRole(uname))) { 
				sessionLog.setAttribute("username", uname);
				response.sendRedirect("index.jsp");
			} else {
				sessionLog.setAttribute("role", "ADMIN");
				sessionLog.setAttribute("username", uname);
				response.sendRedirect("Admin");
			}
		}
	}
	

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}

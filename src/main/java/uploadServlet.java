/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author huygrogbro
 */
@WebServlet(urlPatterns = {"/uploadServlet"})
@MultipartConfig(maxFileSize = 16177215) //Upload file's size up to 16mb

public class uploadServlet extends HttpServlet {
	
	private UserDao userDAO = new UserDao();
	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Part filePart = request.getPart("photo");
		String userName = request.getParameter("username");
		HttpSession sessionLog= request.getSession();
		if(userDAO.sendImageOfUserToDB(userName, filePart) == 1) {
			sessionLog.setAttribute("imageUpload", "success");
			response.sendRedirect("index.jsp");
		} else {
			sessionLog.setAttribute("imageUpload", "failed");
			response.sendRedirect("index.jsp");
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

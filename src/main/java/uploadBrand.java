/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.AdminDao;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/uploadBrand"})
public class uploadBrand extends HttpServlet {
	private AdminDao adminDAO = new AdminDao();
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
		String brandName = request.getParameter("BrandName");
		if(adminDAO.uploadBrandName(brandName) == 1) {
			HttpSession sessionReg = request.getSession();
			sessionReg.setAttribute("uploadBrand", "success");
			response.sendRedirect("BrandAdminPage");
		} else {
			HttpSession sessionReg = request.getSession();
			sessionReg.setAttribute("uploadBrand", "failed");
			response.sendRedirect("BrandAdminPage");
		}
	}

	

}

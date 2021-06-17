/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.AdminDao;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huygrogbro
 */
@WebServlet(urlPatterns = {"/updateProduct"})
public class updateProduct extends HttpServlet {
	private AdminDao adminDao = new AdminDao();

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
		String productShoeId = request.getParameter("shoeId");
		String productShoeName = request.getParameter("shoeName");
		String productShoeBrand = request.getParameter("brand");
		String productShoeColor = request.getParameter("shoeColor");
		String productShoeType = request.getParameter("type");
		String productShoeSize = request.getParameter("shoeSize");
		String productQuantity = request.getParameter("quantity");
		String productPrice = request.getParameter("price");
		String productDescription = request.getParameter("description");
		
		Product productSendChild = new Product();
		productSendChild.setProductShoeId(productShoeId);
		productSendChild.setProductShoeName(productShoeName);
		productSendChild.setProductShoeBrand(productShoeBrand);
		productSendChild.setProductShoeColor(productShoeColor);
		productSendChild.setProductShoeType(productShoeType);
		productSendChild.setProductShoeSize(productShoeSize);
		productSendChild.setProductShoeQuantity(productQuantity);
		productSendChild.setProductShoePrice(productPrice);
		productSendChild.setProductDescription(productDescription);
		
		if(adminDao.updateTheProduct(productSendChild) == 1) {
			response.sendRedirect("ManageProduct");
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

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
import javax.servlet.http.Part;

/**
 *
 * @author huygrogbro
 */
@WebServlet(urlPatterns = {"/uploadProduct"})
public class uploadProduct extends HttpServlet {
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
		String shoeName = request.getParameter("shoeName");
		String brand = request.getParameter("brand");
		String shoeColor = request.getParameter("shoeColor");
		String shoeType = request.getParameter("type");
		String shoeSize = request.getParameter("shoeSize");
		String shoeQuantity = request.getParameter("quantity");
		String shoePrice = request.getParameter("price");
		String shoeDescription = request.getParameter("description");
	
		
		Product addToDB = new Product();
		addToDB.setProductShoeName(shoeName);
		addToDB.setProductShoeBrand(brand);
		addToDB.setProductShoeColor(shoeColor);
		addToDB.setProductShoeType(shoeType);
		addToDB.setProductShoeSize(shoeSize);
		addToDB.setProductShoeQuantity(shoeQuantity);
		addToDB.setProductShoePrice(shoePrice);
		addToDB.setProductDescription(shoeDescription);
		System.out.println(addToDB.getProductDescription());
		if(adminDAO.insertProduct(addToDB) == 1) {
			response.sendRedirect("ManageProduct");
		} else {
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

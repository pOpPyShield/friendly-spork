/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.servlet.http.Part;

/**
 *
 * @author huygrogbro
 */
public class Product {
	private String productShoeId;
	private String productShoeName;
	private String productShoeBrand;
	private String productShoeColor;
	private String productShoeType;
	private String productShoeSize;
	private String productShoeQuantity;
	private String productShoePrice;
	private String productDescription;
	private String productImage;

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	
	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	
	public String getProductShoeId() {
		return productShoeId;
	}

	public void setProductShoeId(String productShoeId) {
		this.productShoeId = productShoeId;
	}

	public String getProductShoeName() {
		return productShoeName;
	}

	public void setProductShoeName(String productShoeName) {
		this.productShoeName = productShoeName;
	}

	public String getProductShoeBrand() {
		return productShoeBrand;
	}

	public void setProductShoeBrand(String productShoeBrand) {
		this.productShoeBrand = productShoeBrand;
	}

	public String getProductShoeColor() {
		return productShoeColor;
	}

	public void setProductShoeColor(String productShoeColor) {
		this.productShoeColor = productShoeColor;
	}

	public String getProductShoeType() {
		return productShoeType;
	}

	public void setProductShoeType(String productShoeType) {
		this.productShoeType = productShoeType;
	}

	public String getProductShoeSize() {
		return productShoeSize;
	}

	public void setProductShoeSize(String productShoeSize) {
		this.productShoeSize = productShoeSize;
	}

	public String getProductShoeQuantity() {
		return productShoeQuantity;
	}

	public void setProductShoeQuantity(String productShoeQuantity) {
		this.productShoeQuantity = productShoeQuantity;
	}

	public String getProductShoePrice() {
		return productShoePrice;
	}

	public void setProductShoePrice(String productShoePrice) {
		this.productShoePrice = productShoePrice;
	}
	
}

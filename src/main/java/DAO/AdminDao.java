/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Product;
import Model.ProductBrand;
import Model.UserAdminManage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 *
 * @author huygrogbro
 */
public class AdminDao {
	Connection conn = null;
	
	public int uploadBrandName(String brandName) {
		String INSERT_PRODUCT_BRAND_SQL = "INSERT INTO ProductShoeBrand(ProductShoeBrandName) VALUES (?)";
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeapp", "shoeapp","makeshoeapp22");
			PreparedStatement preparedStatement = conn.prepareStatement(INSERT_PRODUCT_BRAND_SQL);
			preparedStatement.setString(1, brandName);
			result = preparedStatement.executeUpdate();
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public ArrayList<ProductBrand> displayProductBrand() {
		ArrayList<ProductBrand> productBrandArr = new ArrayList<ProductBrand>();
		String GET_FROM_PRODUCT_BRAND_SQL = "SELECT * FROM ProductShoeBrand";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeapp", "shoeapp","makeshoeapp22");
			PreparedStatement st = conn.prepareStatement(GET_FROM_PRODUCT_BRAND_SQL);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				ProductBrand productBrand = new ProductBrand();
				productBrand.setId(rs.getString("ProductShoeBrandID"));
				productBrand.setBrandName(rs.getString("ProductShoeBrandName"));
				productBrandArr.add(productBrand);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
			
		return productBrandArr;
	}
	
	public ArrayList<UserAdminManage> displayUserManage() {
		ArrayList<UserAdminManage> usrArr = new ArrayList<UserAdminManage>();
		String SQL_TO_USER_MANAGER = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeapp", "shoeapp","makeshoeapp22");
			
			//Get the userId, userName and roleName
			SQL_TO_USER_MANAGER = "SELECT u.UserId, u.UserName, ar.role_name FROM Users u "
				+ "JOIN auth_user_role aur "
				+ "ON u.UserId = aur.auth_user_id "
				+ "JOIN auth_role ar "
				+ "ON ar.auth_role_id = aur.auth_role_id";
			PreparedStatement st = conn.prepareStatement(SQL_TO_USER_MANAGER);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				
					UserAdminManage usrAdminManage = new UserAdminManage();
					usrAdminManage.setUserId(rs.getString("UserId"));
					usrAdminManage.setUserName(rs.getString("UserName"));
					usrAdminManage.setRoleName(rs.getString("role_name"));
					SQL_TO_USER_MANAGER = "SELECT Photo FROM image_user WHERE UserId = ?";
					st = conn.prepareStatement(SQL_TO_USER_MANAGER);
					st.setString(1, rs.getString("UserId"));
					ResultSet photoInside = st.executeQuery();
					if(photoInside.next()) {
						Blob image = photoInside.getBlob("Photo");
						InputStream inputStream = image.getBinaryStream();
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						byte[] buffer = new byte[4096];
						int bytesRead = -1;

						while ((bytesRead = inputStream.read(buffer)) != -1) {
							outputStream.write(buffer, 0, bytesRead);
						}

						byte[] imageBytes = outputStream.toByteArray();
						String base64Image = Base64.getEncoder().encodeToString(imageBytes);

						inputStream.close();
						outputStream.close();
						usrAdminManage.setBase64Image(base64Image);
					} 
					usrArr.add(usrAdminManage);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return usrArr;
	}
	
	public int insertProduct(Product pInsert) {
		String SQL_TO_PRODUCT = "";
		try {
			//1. Insert into the ProductShoes
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeapp", "shoeapp","makeshoeapp22");
			SQL_TO_PRODUCT = "INSERT INTO ProductShoes(ProductShoeName, ProductShoeBrandIdRef) VALUES (?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(SQL_TO_PRODUCT);
			preparedStatement.setString(1, pInsert.getProductShoeName());
			preparedStatement.setString(2, pInsert.getProductShoeBrand());
			preparedStatement.executeUpdate();
			
			//Query the productShoeId, this will use to insert through method
			String shoeId = "";
			SQL_TO_PRODUCT = "SELECT ProductShoeId FROM ProductShoes WHERE ProductShoeName=?";
			preparedStatement = conn.prepareStatement(SQL_TO_PRODUCT);
			preparedStatement.setString(1, pInsert.getProductShoeName());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				shoeId = rs.getString("ProductShoeId");
			}
			//2. Insert into color
			SQL_TO_PRODUCT = "INSERT INTO ProductShoeColor(ProductShoeIdRef, ColorName) VALUES (?, ?)";
			preparedStatement = conn.prepareStatement(SQL_TO_PRODUCT);
			preparedStatement.setString(1, shoeId);
			preparedStatement.setString(2, pInsert.getProductShoeColor());
			preparedStatement.execute();
			
			//3. Insert into productShoeType
			SQL_TO_PRODUCT = "INSERT INTO ProductShoeType(ProductShoeIdRef, ProductShoeType) VALUES (?,?)";
			preparedStatement = conn.prepareStatement(SQL_TO_PRODUCT);
			preparedStatement.setString(1, shoeId);
			preparedStatement.setString(2, pInsert.getProductShoeType());
			preparedStatement.execute();
			
			//4. Insert into ProductShoesSize
			SQL_TO_PRODUCT = "INSERT INTO ProductShoesSize(ProductShoeSize, ProductShoeSizeQuantity, Price, ProductShoeIdRef) VALUES (?, ?, ?, ?)";
			preparedStatement = conn.prepareStatement(SQL_TO_PRODUCT);
			preparedStatement.setString(1, pInsert.getProductShoeSize());
			preparedStatement.setString(2, pInsert.getProductShoeQuantity());
			preparedStatement.setString(3, pInsert.getProductShoePrice());
			preparedStatement.setString(4, shoeId);
			preparedStatement.execute();
			
			//5. Insert into Description
			SQL_TO_PRODUCT = "INSERT INTO Description(ProductShoeIdRef, DescriptionContent) VALUES (?, ?)";
			preparedStatement = conn.prepareStatement(SQL_TO_PRODUCT);
			preparedStatement.setString(1, shoeId);
			preparedStatement.setString(2, pInsert.getProductDescription());
			preparedStatement.execute();
			
			/**
			 6. Image insert
			SQL_TO_PRODUCT = "INSERT INTO ProductImage(ProductShoeIdRef, Photo) VALUES (?, ?)";
			preparedStatement = conn.prepareStatement(SQL_TO_PRODUCT);
			preparedStatement.setString(1, shoeId);
			InputStream inputStream = pInsert.getImagePart().getInputStream();
			preparedStatement.setBlob(2, inputStream);
			
			preparedStatement.execute();
			inputStream.close();
			* */
			return 1;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return 0;
	}
	
	public ArrayList<Product> displayProduct() {
		ArrayList<Product> arrProduct = new ArrayList<Product>();
		String SQL_TO_DISPLAY_PRODUCT =   "SELECT ps.ProductShoeId, "
							+ "ps.ProductShoeName, "
							+ "pb.ProductShoeBrandName, "
							+ "pc.ColorName, "
							+ "pt.ProductShoeType, "
							+ "pz.ProductShoeSize, "
							+ "pz.ProductShoeSizeQuantity, "
							+ "pz.Price, "
							+ "pd.DescriptionContent "
							+ "FROM ProductShoes ps "
							+ "JOIN ProductShoeBrand pb "
							+ "ON ps.ProductShoeBrandIdRef = pb.ProductShoeBrandID "
							+ "JOIN ProductShoeColor pc "
							+ "ON ps.ProductShoeId = pc.ProductShoeIdRef "
							+ "JOIN ProductShoeType pt "
							+ "ON ps.ProductShoeId = pt.ProductShoeIdRef "
							+ "JOIN ProductShoesSize pz "
							+ "ON ps.ProductShoeId = pz.ProductShoeIdRef "
							+ "JOIN Description pd "
							+ "ON ps.ProductShoeId = pd.ProductShoeIdRef";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeapp", "shoeapp","makeshoeapp22");
			PreparedStatement st = conn.prepareStatement(SQL_TO_DISPLAY_PRODUCT);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Product productChild = new Product();
				productChild.setProductShoeId(rs.getString("ProductShoeId"));
				productChild.setProductShoeName(rs.getString("ProductShoeName"));
				productChild.setProductShoeBrand(rs.getString("ProductShoeBrandName"));
				productChild.setProductShoeColor(rs.getString("ColorName"));
				productChild.setProductShoeType(rs.getString("ProductShoeType"));
				productChild.setProductShoeSize(rs.getString("ProductShoeSize"));
				productChild.setProductShoeQuantity(rs.getString("ProductShoeSizeQuantity"));
				productChild.setProductShoePrice(rs.getString("Price"));
				productChild.setProductDescription(rs.getString("DescriptionContent"));
				
				SQL_TO_DISPLAY_PRODUCT = "SELECT Photo FROM ProductImage WHERE ProductShoeIdRef=?";
				st = conn.prepareStatement(SQL_TO_DISPLAY_PRODUCT);
				st.setString(1, productChild.getProductShoeId());
				ResultSet photoProductInside = st.executeQuery();
				if(photoProductInside.next()) {
						Blob image = photoProductInside.getBlob("Photo");
						InputStream inputStream = image.getBinaryStream();
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						byte[] buffer = new byte[4096];
						int bytesRead = -1;

						while ((bytesRead = inputStream.read(buffer)) != -1) {
							outputStream.write(buffer, 0, bytesRead);
						}

						byte[] imageBytes = outputStream.toByteArray();
						String base64Image = Base64.getEncoder().encodeToString(imageBytes);

						inputStream.close();
						outputStream.close();
						productChild.setProductImage(base64Image);
				}
				arrProduct.add(productChild);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return arrProduct;
	}
	
	public int insertImageToProduct(String productId, Part filePart) {
		InputStream inputStream = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeapp", "shoeapp", "makeshoeapp22");
			PreparedStatement st = conn.prepareStatement("INSERT INTO ProductImage(ProductShoeIdRef, Photo) VALUES (?, ?)");
			if(filePart != null) {
				//Prints out some information to debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());
				//obtains input stream of the upload file
				inputStream = filePart.getInputStream(); 
			}
			st.setString(1, productId);
			if(inputStream != null) {
				st.setBlob(2, inputStream);
			}
			int row = st.executeUpdate();
			if(row>	0) {
				return 1;
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch(SQLException ex) {
					ex.printStackTrace();	
				}
			}
		}
		return 0;
	}
	
	public int updateTheProduct(Product producUpdate) {
		String SQL_UPDATE_PRODUCT = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeapp", "shoeapp", "makeshoeapp22");
			
			//1. Update ProductShoes
			SQL_UPDATE_PRODUCT = "UPDATE ProductShoes SET ProductShoeName = ? WHERE ProductShoeId = ?";
			PreparedStatement st = conn.prepareStatement(SQL_UPDATE_PRODUCT);
			st.setString(1, producUpdate.getProductShoeName());
			st.setString(2, producUpdate.getProductShoeId());
			st.execute();
			
			//2. Update brand
			SQL_UPDATE_PRODUCT = "UPDATE ProductShoes SET ProductShoeBrandIdRef = ? WHERE ProductShoeId = ?";
			st = conn.prepareStatement(SQL_UPDATE_PRODUCT);
			st.setString(1, producUpdate.getProductShoeBrand());
			st.setString(2, producUpdate.getProductShoeId());
			st.execute();
			
			//3. Update color
			SQL_UPDATE_PRODUCT = "UPDATE ProductShoeColor SET ColorName = ? WHERE ProductShoeIdRef = ?";
			st = conn.prepareStatement(SQL_UPDATE_PRODUCT);
			st.setString(1, producUpdate.getProductShoeColor());
			st.setString(2, producUpdate.getProductShoeId());
			st.execute();
			
			//4. Update type
			SQL_UPDATE_PRODUCT = "UPDATE ProductShoeType SET ProductShoeType = ? WHERE ProductShoeIdRef = ?";
			st = conn.prepareStatement(SQL_UPDATE_PRODUCT);
			st.setString(1, producUpdate.getProductShoeType());
			st.setString(2, producUpdate.getProductShoeId());
			st.execute();
			
			//5. Update size
			SQL_UPDATE_PRODUCT = "UPDATE ProductShoesSize SET ProductShoeSize = ? WHERE ProductShoeIdRef = ?";
			st = conn.prepareStatement(SQL_UPDATE_PRODUCT);
			st.setString(1, producUpdate.getProductShoeSize());
			st.setString(2, producUpdate.getProductShoeId());
			st.execute();
			
			//6. Update quantity 
			SQL_UPDATE_PRODUCT = "UPDATE ProductShoesSize SET ProductShoeSizeQuantity = ? WHERE ProductShoeIdRef = ?";
			st = conn.prepareStatement(SQL_UPDATE_PRODUCT);
			st.setString(1, producUpdate.getProductShoeQuantity());
			st.setString(2, producUpdate.getProductShoeId());
			st.execute();
			
			//7. Update price 
			SQL_UPDATE_PRODUCT = "UPDATE ProductShoesSize SET Price = ? WHERE ProductShoeIdRef = ?";
			st = conn.prepareStatement(SQL_UPDATE_PRODUCT);
			st.setString(1, producUpdate.getProductShoePrice());
			st.setString(2, producUpdate.getProductShoeId());
			st.execute();
			
			//8. Update description
			SQL_UPDATE_PRODUCT = "UPDATE Description SET DescriptionContent = ? WHERE ProductShoeIdRef = ?";
			st = conn.prepareStatement(SQL_UPDATE_PRODUCT);
			st.setString(1, producUpdate.getProductDescription());
			st.setString(2, producUpdate.getProductShoeId());
			st.execute();
			
			return 1;
			
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return 0;
	}
	
	public int deleteProduct(String productId) {
		String SQL_DELETE_PRODUCT = "DELETE FROM ProductShoes WHERE ProductShoeId = ?";
		System.out.println(productId);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeapp", "shoeapp", "makeshoeapp22");
			PreparedStatement st = conn.prepareStatement(SQL_DELETE_PRODUCT);
			st.setString(1, productId);
			st.execute();
			return 1;
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return 0;
	}
}

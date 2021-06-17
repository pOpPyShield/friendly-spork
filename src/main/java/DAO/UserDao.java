/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.User;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 *
 * @author huygrogbro
 */
public class UserDao {
	Connection conn = null;
	public int registerUser(User user) {
		String INSERT_USERS_SQL = "INSERT INTO Users " 
					+ "(UserName, Password) VALUES "
					+ "(?,?)";
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeapp", "shoeapp","makeshoeapp22");
			PreparedStatement preparedStatement = conn.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassWord());
			System.out.println(preparedStatement);
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
	
	public int writeToAuthUserRole(User user) {
		String GET_USERID_SQL = "SELECT UserId "
				+	"FROM Users "
				+	"WHERE UserName=?";
		String id = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeapp", "shoeapp", "makeshoeapp22");
			PreparedStatement st = conn.prepareStatement(GET_USERID_SQL);
			st.setString(1, user.getUserName());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				id = rs.getString(1);
				String INSERT_AUTH_USER_ROLE_SQL = "INSERT INTO auth_user_role (auth_user_id, auth_role_id) VALUES (?,3)";
				st = conn.prepareStatement(INSERT_AUTH_USER_ROLE_SQL);
				st.setString(1, id);
				st.execute();
				return 1;
			}  
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
		return 0;
	}
	
	public int sendImageOfUserToDB(String userName, Part filePart) {
		String id = null;
		InputStream inputStream = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeapp", "shoeapp", "makeshoeapp22");
			PreparedStatement st = conn.prepareStatement("SELECT UserId FROM Users WHERE UserName = ?");
			st.setString(1, userName);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				id = rs.getString("UserId");
				String INSERT_TO_IMAGE_USER = "INSERT INTO image_user (UserId, Photo) VALUES (?, ?)";
				st = conn.prepareStatement(INSERT_TO_IMAGE_USER);
				if(filePart != null) {
					//Prints out some information to debugging
					System.out.println(filePart.getName());
					System.out.println(filePart.getSize());
					System.out.println(filePart.getContentType());
					//obtains input stream of the upload file
					inputStream = filePart.getInputStream(); 
				}
				st.setString(1, id);
				if(inputStream != null) {
					st.setBlob(2, inputStream);
				}
				int row = st.executeUpdate();
				if(row>	0) {
					return 1;
				}
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
	
	public User displayImageBlob(String userName) {
		String QUERY_IMAGE_USER = "SELECT Photo FROM Users u INNER JOIN image_user ON u.UserId = image_user.UserId WHERE UserName = ?";
		User usr = new User();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeapp", "shoeapp", "makeshoeapp22");
			PreparedStatement st = conn.prepareStatement(QUERY_IMAGE_USER);
			st.setString(1, userName);
			ResultSet rs = st.executeQuery();
			if(rs.next()) { 
				Blob image = rs.getBlob("Photo");
				
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
				
				usr.setBase64Image(base64Image);
				
			} 
		} catch (Exception ex) {
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
		return usr;
	}
}

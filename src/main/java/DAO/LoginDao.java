/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huygrogbro
 */
public class LoginDao {
	String sql ="SELECT u.UserName, u.Password, ar.role_name FROM Users u "
		+ "JOIN auth_user_role aur "
		+ "ON u.UserId = aur.auth_user_id "
		+ "JOIN auth_role ar "
		+ "ON ar.auth_role_id = aur.auth_role_id WHERE UserName = ? AND Password = ?"; 
	Connection con = null;
	public boolean check(String uname, String password) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeapp", "shoeapp","makeshoeapp22");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, uname);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public String checkRole(String uname) {
		String roleName = null;
		String SQL_SELECT_ROLE = "SELECT ar.role_name FROM Users u "
		+ "JOIN auth_user_role aur "
		+ "ON u.UserId = aur.auth_user_id "
		+ "JOIN auth_role ar "
		+ "ON ar.auth_role_id = aur.auth_role_id WHERE UserName = ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeapp", "shoeapp","makeshoeapp22");
			PreparedStatement st = con.prepareStatement(SQL_SELECT_ROLE);
			st.setString(1, uname);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				roleName = rs.getString(1);
				return roleName;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return roleName;
	}
}

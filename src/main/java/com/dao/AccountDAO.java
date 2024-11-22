package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.model.Account;
import com.util.DBUtil;




public class AccountDAO {
	private Connection connection;
	
	public AccountDAO()
	{
		connection = DBUtil.getConnection();
	}
	public void addAccount(Account acc) {
        try {
            String query = "INSERT INTO account (USERNAME, PASSWORD, ADDRESS, EMAIL, PHONE, birthday, gender, ROLE, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, acc.getUsername());
            pstmt.setString(2, acc.getPassword());
            pstmt.setString(3, acc.getAddress());
            pstmt.setString(4, acc.getEmail());
            pstmt.setString(5, acc.getPhone());
            pstmt.setString(6, acc.getBirthday());
            pstmt.setString(7, acc.getGender());
            pstmt.setString(8, acc.getRole());
            pstmt.setString(9, acc.getImage());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public List<Account> getAllAccounts() {
        List<Account> accs = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM account");
            while (rs.next()) {
            	Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setAddress(rs.getString("username"));
                a.setEmail(rs.getString("email"));
                a.setPhone(rs.getString("phone"));
                a.setPhone(rs.getString("birthday"));
                a.setPhone(rs.getString("gender"));
                a.setRole(rs.getString("role"));
                a.setImage(rs.getString("image"));
                accs.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accs;
    }
	
	public void updateAccount(Account acc) {
	    try {
	        String query = "UPDATE account SET USERNAME=?, ADDRESS=?, EMAIL=?, PHONE=?, birthday=?, gender=?, image=? WHERE ID=?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, acc.getUsername());
	        pstmt.setString(2, acc.getAddress());
	        pstmt.setString(3, acc.getEmail());
	        pstmt.setString(4, acc.getPhone());
	        pstmt.setString(5, acc.getBirthday());
	        pstmt.setString(6, acc.getGender());
	        pstmt.setString(7, acc.getImage());
	        pstmt.setInt(8, acc.getId());

	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public Account getAccountByUsernameAndPassword(String username, String password) {
		Account acc = null;
        try {
            String query = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	acc = new Account();
            	acc.setId(rs.getInt("id"));
            	acc.setUsername(rs.getString("username"));
            	acc.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acc;
    }
	
	
	
	public Account getAccountById(int id) {
		Account acc = null;
        try {
            String query = "SELECT * FROM account WHERE ID = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	acc = new Account();
            	acc.setId(rs.getInt("ID"));
            	acc.setUsername(rs.getString("USERNAME"));
            	acc.setPassword(rs.getString("PASSWORD"));
            	acc.setAddress(rs.getString("ADDRESS"));
            	acc.setEmail(rs.getString("EMAIL"));
            	acc.setPhone(rs.getString("PHONE"));
            	acc.setBirthday(rs.getString("birthday"));
            	acc.setGender(rs.getString("gender"));
            	acc.setRole(rs.getString("ROLE"));
            	acc.setImage(rs.getString("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acc;
    }
}

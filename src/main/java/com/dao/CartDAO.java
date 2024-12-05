package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.model.Account;
import com.util.DBUtil;

public class CartDAO 
{
	
	private Connection connection;
	
	public CartDAO()
	{
		connection = DBUtil.getConnection();
	}
	
	public void addAccount(String account, String product) {
        try {
            String query = "INSERT INTO FAVORITE (ACCOUNT_ID, PRODUCT_ID) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, account);
            pstmt.setString(2, product);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

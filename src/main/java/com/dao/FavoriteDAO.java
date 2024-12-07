package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.model.Favorite;
import com.util.DBUtil;

public class FavoriteDAO {
	private Connection connection;
	
	public FavoriteDAO()
	{
		connection = DBUtil.getConnection();
	}
	
	public void addFavo(Favorite fav) {
        try {
            String query = "INSERT INTO favorite (ACCOUNT_ID, PRODUCT_ID, BUY) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, fav.getAccountId());
            stmt.setInt(2, fav.getProductId());
            stmt.setInt(3, fav.getBuy());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public void deleteFavo(int accountId, int productId) {
        try {
            String query = "DELETE FROM favorite WHERE ACCOUNT_ID = ? AND PRODUCT_ID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            	stmt.setInt(1, accountId);
            	stmt.setInt(2, productId);
            	stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public List<Favorite> getFavoritesByAccount(int accountId) {
        List<Favorite> favorites = new ArrayList<>();
        String query = "SELECT * FROM favorite WHERE ACCOUNT_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("PRODUCT_ID");
                favorites.add(new Favorite(accountId, productId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favorites;
    }
	public List<Favorite> getAllProductByAccount(int accountId) {
        List<Favorite> favorites = new ArrayList<>();
        String query = "select * from favorite inner join product on favorite.product_id = product.id where account_id=?;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("PRODUCT_ID");
                favorites.add(new Favorite(accountId, productId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favorites;
    }
	public List<Favorite> getAllCartByStatus(int accountId) {
	    List<Favorite> favorites = new ArrayList<>();
	    String query = "SELECT favorite.account_id, favorite.product_id, favorite.status, favorite.buy " +
	                   "FROM favorite " +
	                   "INNER JOIN product ON favorite.product_id = product.id " +
	                   "WHERE favorite.account_id = ? AND favorite.status = 'added';";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setInt(1, accountId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            int productId = rs.getInt("product_id"); // Lấy cột product_id
	            int buy = rs.getInt("buy"); // Lấy cột buy
	            Favorite favorite = new Favorite(accountId, productId);
	            favorite.setBuy(buy); // Gán giá trị buy
	            favorites.add(favorite);
	        }
	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace(); // In lỗi chi tiết
	    }
	    return favorites;
	}
	public List<Favorite> getAllPay(int accountId) {
	    List<Favorite> favorites = new ArrayList<>();
	    String query = "SELECT favorite.account_id, favorite.product_id, favorite.status, favorite.buy " +
	                   "FROM favorite " +
	                   "INNER JOIN product ON favorite.product_id = product.id " +
	                   "WHERE favorite.account_id = ?';";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setInt(1, accountId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            int productId = rs.getInt("product_id"); // Lấy cột product_id
	            int buy = rs.getInt("buy"); // Lấy cột buy
	            Favorite favorite = new Favorite(accountId, productId);
	            favorite.setBuy(buy); // Gán giá trị buy
	            favorites.add(favorite);
	        }
	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace(); // In lỗi chi tiết
	    }
	    return favorites;
	}


	public void updateFavoriteStatus(String status, int buy, int accountId) {
        String query = "UPDATE favorite SET STATUS = ?, BUY = ? WHERE account_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, buy);
            preparedStatement.setInt(3, accountId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void updateStatusProduct(String status, int accountId, int productId) {
        String query = "UPDATE favorite SET STATUS = ? WHERE account_id = ? and product_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, accountId);
            preparedStatement.setInt(3, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public boolean isProductFavorite(int accountId, int productId) {
        String query = "SELECT * FROM favorite WHERE ACCOUNT_ID = ? AND PRODUCT_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, accountId);
            stmt.setInt(2, productId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

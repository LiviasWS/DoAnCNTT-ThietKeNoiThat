package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Product;
import com.util.DBUtil;

public class ProductDAO 
{
	
	private Connection connection;
	
	public ProductDAO()
	{
		connection = DBUtil.getConnection();
	}
	
	public Product getProductByID(int id)
	{
		String sqlString = "SELECT * FROM PRODUCT WHERE ID = " + id + ";";
		Product product = new Product();
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next())
			{
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getString("price"));
				product.setCategory(rs.getString("category"));
				product.setCollection(rs.getString("collection"));
				product.setImage(rs.getString("image"));
				product.setQuantity(rs.getInt("quantity"));
				product.setSold(rs.getInt("sold"));	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return product;
	}
	
	public List<Product> GetProductBySearching(String searchString)
	{
		String sqlString= "CALL SEARCH_PRODUCT('"+ searchString +"');";
		List<Product> products= new ArrayList<Product>();
		try
		{
			Statement stmt= connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next())
			{
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getString("price"));
				product.setCategory(rs.getString("category"));
				product.setCollection(rs.getString("collection"));
				product.setImage(rs.getString("image"));
				product.setQuantity(rs.getInt("quantity"));
				product.setSold(rs.getInt("sold"));
				products.add(product);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return products;
	}
	
	public List<Product> GetTop4BestSeller(int collection)
	{
		String sqlString= "CALL GET_TOP4_BESTSELLER (" + collection + ");";
		List<Product> products= new ArrayList<Product>();
		try
		{
			Statement stmt= connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next())
			{
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getString("price"));
				product.setCategory(rs.getString("category"));
				product.setCollection(rs.getString("collection"));
				product.setImage(rs.getString("image"));
				product.setQuantity(rs.getInt("quantity"));
				product.setSold(rs.getInt("sold"));
				products.add(product);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return products;
	}
	
	public List<Product> GetProductsByCollection(int collection)
	{
		String sqlString = "CALL LIST_PRODUCT_BY_COLLECTION("+ collection +");";
		List<Product> products = new ArrayList<>();
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next())
			{
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getString("price"));
				product.setCategory(rs.getString("category"));
				product.setCollection(rs.getString("collection"));
				product.setImage(rs.getString("image"));
				product.setQuantity(rs.getInt("quantity"));
				product.setSold(rs.getInt("sold"));
				products.add(product);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return products;
	}
	
}

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public List<Product> getProductByColors(List<String> colors)
	{
		return null;
	}
	
	public List<Product> getProductByMaterialList(String[] materials)
	{
		String sqlString = "SELECT * FROM PRODUCT JOIN MATERIAL ON PRODUCT.MATERIAL = MATERIAL.ID WHERE ";
		List<Product> products = new ArrayList();
		for(String material : materials)
		{
			sqlString += "MATERIAL.NAME = '" + material + "' OR ";
		}
		sqlString += "0;";
		System.out.println(sqlString);
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
	
	public List<Product> getProductByCollectionList(String[] collections)
	{
		String sqlString = "SELECT * FROM PRODUCT JOIN COLLECTION ON PRODUCT.COLLECTION = COLLECTION.ID WHERE ";
		List<Product> products = new ArrayList();
		for(String collection : collections)
		{
			sqlString += "COLLECTION.NAME = '" + collection + "' OR ";
		}
		sqlString += "0;";
		System.out.println(sqlString);
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
	
	public List<Product> getProductByCategoryList(String[] categories)
	{
		String sqlString = "SELECT * FROM PRODUCT JOIN CATEGORIES ON PRODUCT.CATEGORY = CATEGORIES.ID WHERE ";
		List<Product> products = new ArrayList();
		for(String category : categories)
		{
			sqlString += "CATEGORIES.NAME = '" + category + "' OR ";
		}
		sqlString += "0;";
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
	
	public List<Product> getProductByColorList(String[] colors)
	{
		String sqlString = "SELECT PRODUCT.* "
				+ "FROM PRODUCT "
				+ "JOIN COLOR_MANAGE "
				+ "ON PRODUCT.ID = COLOR_MANAGE.PRODUCT "
				+ "JOIN COLOR "
				+ "ON COLOR_MANAGE.COLOR = COLOR.ID WHERE ";
		for(String color : colors)
		{
			sqlString += "COLOR.NAME = '" + color +"' OR ";
		}
		sqlString += "0;";
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
	
	public List<Product> getAllProduct()
	{
		String sqlString = "SELECT * FROM PRODUCT;";
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
	
	public List<Product> getProductByFilter(String color)
	{
		String sqlString = "SELECT COLOR_TABLE.* "
				+ "FROM ( CALL GET_PRODUCT_BY_COLOR(" + color + ")) AS COLOR_TABLE;";
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
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return products;
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

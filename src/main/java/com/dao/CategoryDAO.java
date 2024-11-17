package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Category;
import com.util.DBUtil;

public class CategoryDAO 
{
	
	private Connection connection;
	
	public CategoryDAO()
	{
		connection = DBUtil.getConnection();
	}
	
	public List<Category> GetAllCategory()
	{
		String sqlString= "SELECT * FROM CATEGORIES";
		List<Category> categories = new ArrayList<>();
		try 
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next())
			{
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				categories.add(category);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return categories;
	}
	
}

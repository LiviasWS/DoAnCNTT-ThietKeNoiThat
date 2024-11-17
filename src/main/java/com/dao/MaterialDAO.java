package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Material;
import com.util.DBUtil;

public class MaterialDAO 
{
	
	private Connection connection;
	
	public MaterialDAO()
	{
		connection = DBUtil.getConnection();
	}
	
	public List<Material> GetAllMeterial()
	{
		String sqlString= "SELECT * FROM MATERIAL";
		List<Material> materials = new ArrayList<>();
		try 
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next())
			{
				Material material = new Material();
				material.setId(rs.getInt("id"));
				material.setName(rs.getString("name"));
				materials.add(material);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return materials;
	}
	
}

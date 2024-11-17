package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Color;
import com.util.DBUtil;

public class ColorDAO 
{
	
	private Connection connection;
	
	public ColorDAO() 
	{
		connection= DBUtil.getConnection();
	}
	
	public List<Color> GetAllColor()
	{
		String sqlString="CALL SELECT_ALL_COLOR();";
		List<Color> colors= new ArrayList<>();
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next())
			{
				Color color = new Color();
				color.setId(rs.getInt("id"));
				color.setName(rs.getString("name"));
				color.setImage(rs.getString("image"));
				colors.add(color);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return colors;
	}
	
}
package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.SubImage;
import com.util.DBUtil;

public class SubImageDAO 
{
	
	private Connection connection;
	
	public SubImageDAO() 
	{
		connection = DBUtil.getConnection();
	}
	
	public List<SubImage> getSubImage(int id)
	{
		String sqlString = "CALL GET_SUB_IMAGE ('"+ id +"');";
		List<SubImage> subImages = new ArrayList<>();
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next())
			{
				SubImage subImage = new SubImage();
				subImage.setName(rs.getString("name"));
				subImage.setImage(rs.getString("image"));
				subImages.add(subImage);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return subImages;
	}
	
}

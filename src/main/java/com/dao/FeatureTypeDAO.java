package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.model.Feature;
import com.model.FeatureType;
import com.util.DBUtil;

public class FeatureTypeDAO 
{	
	
	private Connection connection;
	
	public FeatureTypeDAO()
	{
		connection = DBUtil.getConnection();
	}
	
	public FeatureType getFeatureTypeByID(int id)
	{
		String sqlString = "SELECT * FROM FEATURE_TYPE WHERE ID = "+ id +";";
		FeatureType featureType = new FeatureType();
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next())
			{
				featureType.setId(rs.getInt("id"));
				featureType.setName(rs.getString("name"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return featureType;
	}
	
}

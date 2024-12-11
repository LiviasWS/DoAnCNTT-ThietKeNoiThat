package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.model.Collection;
import com.model.Feature;
import com.model.FeatureType;
import com.model.Product;
import com.util.DBUtil;

public class FeatureDAO 
{
	
	private Connection connection;
	private FeatureTypeDAO featureTypeDAO = new FeatureTypeDAO();
	
	public FeatureDAO()
	{
		connection = DBUtil.getConnection();
	}
	
	public Map<String, String> getProductImageByFeatureNameMap(int productID)
	{
		Map<String, String> productImageByFeatureMap = new HashMap<>();
		String sqlString = "SELECT * FROM FEATURE_MANAGE WHERE PRODUCT = " + productID + ";";
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next())
			{
				int featureID = rs.getInt("feature");
				String image = rs.getString("image");
				Feature feature = this.getFeatureByID(featureID);
				productImageByFeatureMap.put(feature.getName(), image);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return productImageByFeatureMap;
	}
	
	public Map<FeatureType, List<Feature>> getFeatureMapByProductID(int productID)
	{
		List<Feature> featureList = this.getFeatureListByProductID(productID);
		Map<Integer, List<Feature>> mapByFeatureTypeID = this.FeatureHandle(featureList);
		Map<FeatureType, List<Feature>> mapByFeatureType = new HashMap<>();
		for(Map.Entry<Integer, List<Feature>> mapEntry : mapByFeatureTypeID.entrySet())
		{
			FeatureType featureType = featureTypeDAO.getFeatureTypeByID(mapEntry.getKey());
			mapByFeatureType.put(featureType, mapEntry.getValue());
		}
		return mapByFeatureType;
	}
	
	public Map<Integer, List<Feature>> FeatureHandle(List<Feature> list)
	{
		Map<Integer, List<Feature>> groupedByFeatureType = list.stream()
	            .collect(Collectors.groupingBy(Feature::getFeatureType));
		return groupedByFeatureType;
	}
	
	public Feature getFeatureByName(String name)
	{
		String sqlString = "SELECT * FROM FEATURE WHERE NAME = '"+ name +"';";
		Feature feature = new Feature();
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next())
			{
				feature.setId(rs.getInt("id"));
				feature.setName(rs.getString("name"));
				feature.setImage(rs.getString("image"));
				feature.setFeatureType(rs.getInt("feature_type"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return feature;
	}
	
	public Feature getFeatureByID(int id)
	{
		String sqlString = "SELECT * FROM FEATURE WHERE ID = "+ id +";";
		Feature feature = new Feature();
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next())
			{
				feature.setId(rs.getInt("id"));
				feature.setName(rs.getString("name"));
				feature.setImage(rs.getString("image"));
				feature.setFeatureType(rs.getInt("feature_type"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return feature;
	}
	
	public List<Feature> getFeatureListByProductID(int productID)
	{
		String sqlString = "CALL GET_FEATURE_BY_PRODUCT_ID (" + productID + ");";
		List<Feature> features = new ArrayList();
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next())
			{
				Feature feature = new Feature();
				feature.setId(rs.getInt("id"));
				feature.setName(rs.getString("name"));
				feature.setImage(rs.getString("image"));
				feature.setFeatureType(rs.getInt("feature_type"));
				features.add(feature);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return features;
	}
	
}

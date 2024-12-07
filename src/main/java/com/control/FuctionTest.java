package com.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.dao.AccountDAO;
import com.dao.CollectionDAO;
import com.dao.ColorDAO;
import com.dao.FeatureDAO;
import com.dao.FeatureTypeDAO;
import com.dao.ProductDAO;
import com.dao.SubImageDAO;
import com.model.Account;
import com.model.Collection;
import com.model.Color;
import com.model.Feature;
import com.model.FeatureType;
import com.model.Product;
import com.model.SubImage;

public class FuctionTest 
{
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try {
			FeatureDAO featureDAO = new FeatureDAO();
			FeatureTypeDAO featureTypeDAO = new FeatureTypeDAO();
			Map<FeatureType, List<Feature>> featureMap = featureDAO.getFeatureMapByProductID(2);
			for(Map.Entry<FeatureType, List<Feature>> mapEntry : featureMap.entrySet())
			{
				System.out.println("Feature type name: " + mapEntry.getKey().getName());
				for(Feature feature : mapEntry.getValue())
				{
					System.out.println("Feature ID: " + feature.getId());
					System.out.println("Feature name: " + feature.getName());
					System.out.println("Feature image: " + feature.getImage());
					System.out.println("Feature type: " + feature.getFeatureType());
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static Map<Collection, List<Product>> GetBestSellerByCollection() throws ServletException, IOException
	{
		CollectionDAO collectionDAO= new CollectionDAO();;
		ProductDAO productDAO= new ProductDAO();
		Map <Collection, List<Product>> collectionBestSellerMap= new HashMap<>();
		List<Collection> collections= collectionDAO.getAllCollections();
		for(Collection collection : collections)
		{
			List<Product> bestSellers = productDAO.GetTop4BestSeller(collection.getId());
			collectionBestSellerMap.put(collection, bestSellers);
		}
		return collectionBestSellerMap;
	}

}

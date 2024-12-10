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
		FeatureDAO featureDAO = new FeatureDAO();
		Map<Feature, String> productImageByFeatureMap = featureDAO.getProductImageByFeatureMap(2);
		for(Map.Entry<Feature, String> entry : productImageByFeatureMap.entrySet())
		{
			System.out.println("Feature: " + entry.getKey().getName());
			System.out.println("Image: " + entry.getValue());
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

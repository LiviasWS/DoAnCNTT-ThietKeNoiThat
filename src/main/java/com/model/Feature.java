package com.model;

public class Feature 
{
	int id;
	String name;
	String image;
	int featureType;
	
	public Feature()
	{
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getFeatureType() {
		return featureType;
	}
	public void setFeatureType(int featureType) {
		this.featureType = featureType;
	}
	
}

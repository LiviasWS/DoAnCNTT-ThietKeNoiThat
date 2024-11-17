package com.model;

public class Collection 
{
	int id;
	String name;
	String title;
	String description;
	String image;
	public Collection() {}
	
	public Collection(int id, String name, String title, String description, String image) 
	{
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.description = description;
		this.image = image;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}

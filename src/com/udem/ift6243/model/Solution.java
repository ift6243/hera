package com.udem.ift6243.model;

public class Solution 
{
	private Integer id;
	private Integer categoryId;
	private String name;
	private String description;
	
	public Solution(Integer id, Integer categoryId, 
			String name, String description) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
}

package com.udem.ift6243.model;

public class Solution 
{
	private Integer id;
	private Integer categoryId;
	private String name;
	private String description;
	private Double duration;
	private Double priority;
	
	public Solution(Integer id, Integer categoryId, 
			String name, String description,
			Double duration, Double priority) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.priority = priority;
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
	public String getDuration() {
		return description;
	}
	public String getPriority() {
		return description;
	}
}

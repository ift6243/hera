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
	
	public Double getDuration() {
		return duration;
	}
	
	public Solution increasePriority(Double value) throws Exception {
		if(value <= 0 || value > 1)
		{
			throw new Exception("Incorrect priority value");
		}
		
		this.priority += value;
		
		return this;
	}
	
	public Solution decreasePriority(Double value) throws Exception {
		if(value <= 0 || value > 1)
		{
			throw new Exception("Incorrect priority value");
		}
		
		this.priority -= value;
		
		return this;
	}
	
	public Double getPriority() {
		return priority;
	}
}

package com.udem.ift6243.model;

import java.util.Date;

public class Solution 
{
	private Integer id;
	private Integer categoryId;
	private String name;
	private String description;
	private long duration;
	private Double priority;
	
	public Solution(Integer id, Integer categoryId, 
			String name, String description,
			long duration, Double priority) {
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
	public long getDuration() {
		return duration;
	}
	public Double getPriority() {
		return priority;
	}
}

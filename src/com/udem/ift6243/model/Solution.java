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
	
	public Solution increasePriority(double value) throws NumberFormatException {
		if(value < 0.0d || value > 1.0d)
		{
			throw new NumberFormatException("Incorrect priority value");
		}
		
		if(this.priority.doubleValue() + value >= 1.0d)
		{
			this.priority = Double.valueOf(1.0d);
		}
		else
		{
			this.priority = Double.valueOf(Math.round((this.priority.doubleValue() + value) * 100.0d)) / 100.0d;
		}
		
		return this;
	}
	
	public Solution decreasePriority(double value) throws NumberFormatException {
		if(value < 0.0d || value > 1.0d)
		{
			throw new NumberFormatException("Incorrect priority value");
		}
		
		if(this.priority.doubleValue() - value <= 0.0d)
		{
			this.priority = Double.valueOf(0.0d);
		}
		else
		{
			this.priority = Double.valueOf(Math.round((this.priority.doubleValue() - value) * 100.0d)) / 100.0d;
		}
		
		return this;
	}
	
	public Double getPriority() {
		return priority;
	}

	@Override
	public String toString() {
		return "Solution [id=" + id + ", categoryId=" + categoryId + ", name="
				+ name + ", description=" + description + ", duration="
				+ duration + ", priority=" + priority + "]";
	}
}

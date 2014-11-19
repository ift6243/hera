package com.udem.ift6243.model;

public class WeekPart
{
	private Integer id;
	private String name;
	private Integer startDay;
	private Integer endDay;
	
	public WeekPart(Integer id, String name, Integer startDay, Integer endDay) {
		super();
		this.id = id;
		this.name = name;
		this.startDay = startDay;
		this.endDay = endDay;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getStartDay() {
		return startDay;
	}

	public Integer getEndDay() {
		return endDay;
	}
}

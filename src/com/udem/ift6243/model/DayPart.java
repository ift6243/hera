package com.udem.ift6243.model;

public class DayPart
{
	private Integer id;
	private String name;
	private Integer startHour;
	private Integer endHour;
	
	public DayPart(Integer id, String name, Integer startHour, Integer endHour) {
		super();
		this.id = id;
		this.name = name;
		this.startHour = startHour;
		this.endHour = endHour;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getStartHour() {
		return startHour;
	}

	public Integer getEndHour() {
		return endHour;
	}
}

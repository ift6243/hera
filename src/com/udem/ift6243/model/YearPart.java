package com.udem.ift6243.model;

public class YearPart
{
	private Integer id;
	private String name;
	private String startDate;
	private String endDate;
	
	public YearPart(Integer id, String name, String startDate, String endDate) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}
}

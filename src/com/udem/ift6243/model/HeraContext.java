package com.udem.ift6243.model;

public class HeraContext
{
	private Integer id;
	private Double latitude;
	private Double longitude;
	private Integer dayPart;
	private Integer weekPart;
	private Integer yearPart;
	private String date;
	
	public HeraContext(Integer id, Double latitude, Double longitude,
			Integer dayPart, Integer weekPart, Integer yearPart, String date)
	{
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dayPart = dayPart;
		this.weekPart = weekPart;
		this.yearPart = yearPart;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public Integer getDayPart() {
		return dayPart;
	}

	public Integer getWeekPart() {
		return weekPart;
	}

	public Integer getYearPart() {
		return yearPart;
	}

	public String getDate() {
		return date;
	}
}

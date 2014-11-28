package com.udem.ift6243.model;

public class HeraContext
{
	private Integer id;
	private Double latitude;
	private Double longitude;
	private Integer dayPart;
	private Integer weekPart;
	private Integer yearPart;
	private Integer stressLevel;
	private String date;
	
	public HeraContext(Integer id, Double latitude, Double longitude,
			Integer dayPart, Integer weekPart, Integer yearPart, 
			Integer stressLevel, String date)
	{
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dayPart = dayPart;
		this.weekPart = weekPart;
		this.yearPart = yearPart;
		this.stressLevel = stressLevel;
		this.date = date;
	}

	public HeraContext setId(Integer id) {
		this.id = id;
		
		return this;
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

	public Integer getStressLevel() {
		return stressLevel;
	}

	public void setStressLevel(Integer stressLevel) {
		this.stressLevel = stressLevel;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "HeraContext [id=" + id + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", dayPart=" + dayPart
				+ ", weekPart=" + weekPart + ", yearPart=" + yearPart
				+ ", stressLevel=" + stressLevel + ", date=" + date + "]";
	}
}

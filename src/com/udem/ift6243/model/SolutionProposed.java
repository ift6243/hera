package com.udem.ift6243.model;

public class SolutionProposed
{
	private Integer id;
	private Integer heraContextId;
	private Integer solutionId;
	private Double bonus;
	private String date;
	
	public SolutionProposed(Integer id, Integer heraContextId,
			Integer solutionId, Double bonus, String date) {
		super();
		this.id = id;
		this.heraContextId = heraContextId;
		this.solutionId = solutionId;
		this.bonus = bonus;
		this.date = date;
	}
	
	public Integer getId() {
		return id;
	}
	public Integer getHeraContextId() {
		return heraContextId;
	}
	public Integer getSolutionId() {
		return solutionId;
	}
	public Double getBonus() {
		return bonus;
	}
	public String getDate() {
		return date;
	}
}

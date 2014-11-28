package com.udem.ift6243.model;

public class User
{
	private Integer id;
	private String firstName;
	private String lastName;
	private Integer gender;
	private Integer age;
	private Integer maritalStatus;
	private Integer socioprofessionalCategory;
	private Boolean sport;
	private Boolean meditation;
	private Integer expression;
	
	public User(Integer id, String firstName, String lastName, 
			Integer gender, Integer age, Integer maritalStatus,
			Integer socioprofessionalCategory, Boolean sport,
			Boolean meditation, Integer expression)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.maritalStatus = maritalStatus;
		this.socioprofessionalCategory = socioprofessionalCategory;
		this.sport = sport;
		this.meditation = meditation;
		this.expression = expression;
	}

	public User setId(Integer id) {
		this.id = id;
		
		return this;
	}
	
	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Integer getGender() {
		return gender;
	}

	public Integer getAge() {
		return age;
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public Integer getSocioprofessionalCategory() {
		return socioprofessionalCategory;
	}

	public Boolean getSport() {
		return sport;
	}

	public Boolean getMeditation() {
		return meditation;
	}

	public Integer getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", age=" + age
				+ ", maritalStatus=" + maritalStatus
				+ ", socioprofessionalCategory=" + socioprofessionalCategory
				+ ", sport=" + sport + ", meditation=" + meditation
				+ ", expression=" + expression + "]";
	}
}

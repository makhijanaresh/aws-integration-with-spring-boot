package com.example.aws.dynamodb.model;

public class Address {

	private Integer pinCode;
	private Integer houseNumber;
	private String colony;
	private String state;
	private String country;
	
	
	
	public Address() {
	}
	public Address(Integer pinCode, Integer houseNumber, String colony, String state, String country) {
		super();
		this.pinCode = pinCode;
		this.houseNumber = houseNumber;
		this.colony = colony;
		this.state = state;
		this.country = country;
	}
	public Integer getPinCode() {
		return pinCode;
	}
	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}
	public Integer getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getColony() {
		return colony;
	}
	public void setColony(String colony) {
		this.colony = colony;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}

package com.example.aws.dynamodb.model;

import org.springframework.data.annotation.Id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "user")
public class User {

	@Id
	@DynamoDBHashKey
	private String id;
	private String name;
	private Integer age;
	private String address;
	private String email;
	
	

	public User() {
		super();
	}

	public User(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public User(String id, String name, Integer age, String address,String email) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.email=email;
	}

	@DynamoDBHashKey
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDBAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DynamoDBAttribute
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@DynamoDBAttribute
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@DynamoDBAttribute
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + "]";
	}

	
	
	
	

}

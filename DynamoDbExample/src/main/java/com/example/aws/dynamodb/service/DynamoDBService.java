package com.example.aws.dynamodb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.example.aws.dynamodb.model.Address;
import com.example.aws.dynamodb.model.User;
import com.example.aws.dynamodb.repositories.DynamoDbRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DynamoDBService {

	@Autowired
	private DynamoDbRepo repo;

	@Autowired
	private DynamoDBMapper mapper;

	public String addRecord(User user) {
		repo.save(user);
		return "Record saved";
	}

	public void saveJson(Integer currentId) throws JsonProcessingException {
		List<User> list = new ArrayList<>();
		for (int i = currentId + 1; i <= 10; i++) {
			User user = new User(String.valueOf(i), i + " txt");
			Address address = new Address(455001, 125 + i, "colony :" + i, "state MP", "India");
			ObjectMapper mapper = new ObjectMapper();
			user.setAddress(mapper.writeValueAsString(address));
			user.setAge(10 + i);
			user.setEmail("user" + i + "@hotmail.com");
			list.add(user);
		}
		repo.saveAll(list);

	}

	public List<User> ageBetween(Integer start, Integer end) {
		AttributeValue minRange = new AttributeValue().withN(Integer.toString(start));
		AttributeValue maxRange = new AttributeValue().withN(Integer.toString(end));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

		Condition condition = new Condition().withComparisonOperator(ComparisonOperator.BETWEEN)
				.withAttributeValueList(minRange, maxRange);

		HashMap<String, Condition> scanFilter = new HashMap<String, Condition>();
		scanFilter.put("age", condition);
		scanExpression.setScanFilter(scanFilter);

		return mapper.scan(User.class, scanExpression);
	}

	public List<User> ageBetweenWithEmail(Integer start, Integer end, String email) {

		AttributeValue minRange = new AttributeValue().withN(Integer.toString(start));
		AttributeValue maxRange = new AttributeValue().withN(Integer.toString(end));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

		Condition condition = new Condition().withComparisonOperator(ComparisonOperator.BETWEEN)
				.withAttributeValueList(minRange, maxRange);
		Condition emailCondition = new Condition().withComparisonOperator(ComparisonOperator.NOT_CONTAINS)
				.withAttributeValueList(new AttributeValue().withS(email));

		HashMap<String, Condition> scanFilter = new HashMap<String, Condition>();
		scanFilter.put("age", condition);
		scanFilter.put("email", emailCondition);
		scanExpression.setScanFilter(scanFilter);

		return mapper.scan(User.class, scanExpression);
	}

	public void update(User user) {
		repo.save(user);

	}

	public void delete(String userId) {
		repo.deleteById(userId);
	}

}

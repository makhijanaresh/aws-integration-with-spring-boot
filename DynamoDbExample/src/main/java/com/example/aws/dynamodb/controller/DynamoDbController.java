package com.example.aws.dynamodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.aws.dynamodb.model.User;
import com.example.aws.dynamodb.service.DynamoDBService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class DynamoDbController {

	@Autowired
	private DynamoDBService service;

	@PostMapping("/add")
	public ResponseEntity<String> addRecord(@RequestBody User user) {
		return new ResponseEntity<>(service.addRecord(user), HttpStatus.OK);
	}

	@PostMapping("/savejson/{currentId}")
	public ResponseEntity<String> saveJson(@PathVariable Integer currentId) throws JsonProcessingException {
		service.saveJson(currentId);
		return new ResponseEntity<>("Json Saved", HttpStatus.OK);
	}

	@GetMapping("agebetween/{start}/{end}")
	public ResponseEntity<List<User>> ageBetween(@PathVariable("start") Integer start,
			@PathVariable("end") Integer end) {
		return new ResponseEntity<>(service.ageBetween(start, end), HttpStatus.OK);
	}

	@GetMapping("agebetween/with/email/{start}/{end}/{email}")
	public ResponseEntity<List<User>> ageBetweenWithEmail(@PathVariable("start") Integer start,
			@PathVariable("end") Integer end, @PathVariable("email") String email) {
		return new ResponseEntity<>(service.ageBetweenWithEmail(start, end, email), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody User user) {
		service.update(user);
		return new ResponseEntity<>("Record Updated", HttpStatus.OK);
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> delete(@PathVariable("userId")String userId)
	{
		service.delete(userId);
		return new ResponseEntity<>("Record deleted",HttpStatus.OK);
	}
}

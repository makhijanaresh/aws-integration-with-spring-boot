package com.example.aws.dynamodb.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.example.aws.dynamodb.model.User;

@EnableScan
public interface DynamoDbRepo extends CrudRepository<User, String> {

}

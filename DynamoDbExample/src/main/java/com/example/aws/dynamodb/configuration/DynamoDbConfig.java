package com.example.aws.dynamodb.configuration;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.example.aws.dynamodb.repositories")
public class DynamoDbConfig {
	
	@Value("${accessKey}")
	private String accessKey;

	@Value("${secretKey}")
	private String secretKey;


	public AWSCredentials credentials() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		return credentials;
	}

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBAsyncClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
				.withCredentials(new AWSStaticCredentialsProvider(credentials())).build();
	}

	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(amazonDynamoDB());
	}
}

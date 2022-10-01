package com.sqs.examples.sqs;

import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	//@SqsListener(value="my-first-queue",deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void myConsumer(String message)
	{
		System.out.println("Message received:"+message);
	}
}

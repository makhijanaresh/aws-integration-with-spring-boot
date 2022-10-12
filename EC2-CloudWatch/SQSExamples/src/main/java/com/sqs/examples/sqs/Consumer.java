package com.sqs.examples.sqs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	Logger logger = LogManager.getLogger(this.getClass().getName());

	@SqsListener(value = "my-first-queue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void myConsumer(String message) {
		System.out.println("Message received in sysout: " + message);
		logger.info("Message received in logger:" + message);
	}
}

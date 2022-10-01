package com.s3.examples.service;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;

/**
 * Actual class that communicates with S3 to create bucket and upload file
 * 
 * @author Naresh Makhija
 *
 */
@Service
public class FileStore {

	Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	private AmazonS3 amazonS3;


	/**
	 * Validates that credentials are valid and bucket already exist or not
	 * 
	 * @param bucketName
	 * @return
	 */
	public String createBucket(String bucketName) {
		logger.info("Inside method createBucket");
		try {
			if (bucketAlreadyExists(bucketName)) {
				logger.error("bucket already exist");
			}
			amazonS3.createBucket(bucketName);
		} catch (AmazonS3Exception s3Exception) {
			logger.error("Unable to create bucket :" + s3Exception.getMessage());
		}
		return "Bucket created with name:" + bucketName;
	}

	/**
	 * Method validates that bucket already exists or not
	 * 
	 * @param bucketName
	 * @return
	 */
	private boolean bucketAlreadyExists(String bucketName) {
		logger.info("Inside method bucketAlreadyExists");
		return amazonS3.doesBucketExistV2(bucketName);
	}

	/**
	 * Method that upload file on S3 Bucket
	 * 
	 * @param multiPart
	 * @throws Exception
	 */
	public void uploadFiletoBucket(MultipartFile multiPart,String bucketName) throws Exception {
		logger.info("Inside method upload");
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + multiPart.getOriginalFilename());
		multiPart.transferTo(convFile);
		try {
			amazonS3.putObject(bucketName, convFile.getName(), convFile);
			
		} catch (AmazonS3Exception s3Exception) {
			logger.error("Unable to upload file :" + s3Exception.getMessage());
		}
	}

	public void deleteBucket(String bucketName) {
		amazonS3.deleteBucket(bucketName);
	}

	public void deletFile(String bucketName, String fileName) {
		amazonS3.deleteObject(bucketName, fileName);
	}

}

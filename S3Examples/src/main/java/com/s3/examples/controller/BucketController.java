package com.s3.examples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.s3.examples.service.BucketService;

/**
 * Contains method to create bucket and upload file in bucket on AWS S3
 * 
 * @author Naresh Makhija
 *
 */
@RestController
@RequestMapping("s3bucket")
@CrossOrigin("*")
public class BucketController {

	@Autowired
	BucketService service;

	/**
	 * Calls Service class to create bucket on AWS S3
	 * 
	 * @param bucketName
	 * @return
	 */
	@GetMapping("/add/{bucketName}")
	public ResponseEntity<String> createBucket(@PathVariable("bucketName") String bucketName) {
		return new ResponseEntity<>(service.createBucket(bucketName), HttpStatus.OK);
	}

	/**
	 * Calls Service class to upload file on existing bucket
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping(path = "/upload/file/{bucketName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
			@PathVariable("bucketName") String bucketName) {
		return new ResponseEntity<>(service.uploadFile(file,bucketName), HttpStatus.OK);
	}
	
	
	@DeleteMapping(path="/delete/file/{bucketName}/{fileName}")
	public ResponseEntity<String> deleteFile(@PathVariable("bucketName")String bucketName,@PathVariable("fileName")String fileName)
	{
		return new ResponseEntity<>(service.deleteFile(bucketName,fileName),HttpStatus.OK);
	}

	@DeleteMapping("/delete/bucket/{bucketName}")
	public ResponseEntity<String> deleteBucket(@PathVariable("bucketName") String bucketName) {
		return new ResponseEntity<>(service.deleteBucket(bucketName), HttpStatus.OK);
	}

}

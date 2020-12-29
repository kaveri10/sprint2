package com.cg.spring.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.RestTemplate;

import com.cg.spring.exception.ErrorMessage;
import com.cg.spring.exception.InvalidUserDetails;
import com.cg.spring.json.User;

import springfox.documentation.service.ResponseMessage;

class UserControllerTest {

	public static Logger logger =  LogManager.getLogger(UserControllerTest.class);

	/*
	 * User Created Successfully
	 */
	@Test
	void testCreateNewUser() {
		logger.info("[START] testCreateNewUser()");
		RestTemplate restTemplate = new RestTemplate();
		User user =new User("vijay", "1996-02-12", "vijay@gmail.com", "Vijaaayaa#10", 9404388721L, "pune", "delhi");
		ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://localhost:8080/myapp/user", user, User.class);
		logger.info(responseEntity);
		assertNotNull(responseEntity);
		logger.info("[END] testCreateNewUser()");

	}

	/*
	 * User Creation failed (Invalid email)
	 */
	@Test
	void testCreateNewUserFailedWithInvalidEmailId(){
		logger.info("[START] testCreateNewUserFailedWithInvalidEmailId()");
		RestTemplate restTemplate = new RestTemplate();
		User user =new User("Ajay", "1996-02-12", "ajaygmail.com", "Ajaaay#10", 9404388721L, "pune", "delhi");

		Exception exception=assertThrows(BadRequest.class, 
				() ->{	restTemplate.postForEntity("http://localhost:8080/myapp/user",user ,User.class);});
		logger.info(exception.getClass().getName());
		logger.info("[END] testCreateNewUserFailedWithInvalidEmailId()");
	}
	
	/*
	 * User Creation failed (Invalid name)
	 */
	@Test
	void testCreateNewUserWithInvalidName() {
		logger.info("[START] testCreateNewUserWithInvalidName()");
		RestTemplate restTemplate = new RestTemplate();
		User user =new User("Ajay123", "1996-02-12", "ajay@gmail.com", "Ajaaay#10", 9404388721L, "pune", "delhi");

		assertThrows(Exception.class, 
				() ->{	ResponseEntity<User> user2 = restTemplate.postForEntity("http://localhost:8080/myapp/user",user ,User.class);});
		logger.info("[END] testCreateNewUserWithInvalidName()");
	}
	
	/*
	 * User Creation failed (Invalid password)
	 */
	@Test
	void testCreateNewUserWithInvalidPassword() {
		logger.info("[START] testCreateNewUserWithInvalidPassword()");
		RestTemplate restTemplate = new RestTemplate();
		User user =new User("Ajay", "1996-02-12", "ajay@gmail.com", "ajaaay#10", 9404388721L, "pune", "delhi");

		Exception exception=assertThrows(BadRequest.class, 
				() ->{	restTemplate.postForEntity("http://localhost:8080/myapp/user",user ,User.class);});
		logger.info("[END] testCreateNewUserWithInvalidPassword()");
	}
	
	/*
	 * User Creation failed (Invalid Address)
	 */
	@Test
	void testCreateNewUserWithInvalidAddress() {
		logger.info("[START] testCreateNewUserWithInvalidAddress()");
		RestTemplate restTemplate = new RestTemplate();
		User user =new User("Ajay", "1996-02-12", "ajay@gmail.com", "Ajaaay#10", 9404388721L, null, "delhi");

		Exception exception=assertThrows(BadRequest.class, 
				() ->{	restTemplate.postForEntity("http://localhost:8080/myapp/user",user ,User.class);});
		logger.info("[END] testCreateNewUserWithInvalidAddress()");
	}
	
	/*
	 * User Creation failed (Invalid University Name)
	 */
	@Test
	void testCreateNewUserWithInvalidUnivName() {
		logger.info("[START] testCreateNewUserWithInvalidUnivName()");
		RestTemplate restTemplate = new RestTemplate();
		User user =new User("Ajay", "1996-02-12", "ajay@gmail.com", "Ajaaay#10", 9404388721L, "pune", null);

		Exception exception=assertThrows(BadRequest.class, 
				() ->{	restTemplate.postForEntity("http://localhost:8080/myapp/user",user ,User.class);});
		logger.info("[END] testCreateNewUserWithInvalidUnivName()");
	}
	
	/*
	 * User Creation failed (Blank Password)
	 */
	@Test
	void testCreateNewUserWithBlankPass() {
		logger.info("[START] testCreateNewUserWithBlankPass()");
		RestTemplate restTemplate = new RestTemplate();
		User user =new User("Ajay", "1996-02-12", "jay@gmail.com", "", 9404388721L, "pune", "delhi");

		Exception exception=assertThrows(BadRequest.class, 
				() ->{	restTemplate.postForEntity("http://localhost:8080/myapp/user",user ,User.class);});
		logger.info("[END] testCreateNewUserWithBlankPass()");
	}
	
	/*
	 * User Creation failed (Blank EmailId)
	 */
	@Test
	void testCreateNewUserFailedWithBlankEmailId(){
		logger.info("[START] testCreateNewUserFailedWithBlankEmailId()");
		RestTemplate restTemplate = new RestTemplate();
		User user =new User("Ajay", "1996-02-12","", "Ajaaay#10", 9404388721L, "pune", "delhi");

		Exception exception=assertThrows(BadRequest.class, 
				() ->{	restTemplate.postForEntity("http://localhost:8080/myapp/user",user ,User.class);});
		logger.info(exception.getClass().getName());
		logger.info("[END] testCreateNewUserFailedWithBlankEmailId()");
	}
	
	/*
	 * User Creation failed (Blank Name)
	 */
	@Test
	void testCreateNewUserWithBlankName() {
		logger.info("[START] testCreateNewUserWithBlankName()");
		RestTemplate restTemplate = new RestTemplate();
		User user =new User("", "1996-02-12", "ajay@gmail.com", "Ajaaay#10", 9404388721L, "pune", "delhi");

		assertThrows(Exception.class, 
				() ->{	ResponseEntity<User> user2 = restTemplate.postForEntity("http://localhost:8080/myapp/user",user ,User.class);});
		logger.info("[END] testCreateNewUserWithBlankName()");
	}
	

}

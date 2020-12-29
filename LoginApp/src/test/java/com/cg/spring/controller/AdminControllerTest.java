package com.cg.spring.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;

import com.cg.spring.exception.UserNotFoundException;
import com.cg.spring.json.User;

class AdminControllerTest {

	public static Logger logger =  LogManager.getLogger(AdminControllerTest.class);;
	
	@Autowired
	AdminController adminController;
	/*
	 * User with specified email id found Successfully
	 */
	@Test
	void testFindUserByEmailId() {
		RestTemplate restTemplate = new RestTemplate();
		logger.info("[START] testFindUserByEmailIdSuccess()");
		ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://localhost:8080/myapp/admin/vijay@gmail.com",User.class);
		assertNotNull(responseEntity);
		logger.info("[END] testFindUserByEmailIdSuccess()");
	}

	/*
	 * List of User viewed Successfully
	 */
	@Test
	public void testViewUsersSuccess() {

		logger.info("[START] testViewUsersSuccess()");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/myapp/admin",User[].class);
		assertNotNull(responseEntity);
		logger.info("Database fetched successfully");
		logger.info("[END] testViewUsersSuccess()");
	}

	/*
	 * User account with specified email id unlocked Successfully
	 */
	@Test
	void testUnlockUserAccountSuccess() {
		logger.info("[START] testUnlockUserAccountSuccess()");
		RestTemplate restTemplate = new RestTemplate();
		User user=restTemplate.getForObject("http://localhost:8080/myapp/admin/vijay@gmail.com",User.class);
		restTemplate.put("http://localhost:8080/myapp/admin/unlock/vijay@gmail.com",user,User.class);
		logger.info("Account unlocked successfully!!");
		logger.info("[END] testUnlockUserAccountSuccess()");
	}
	
	/*
	 *  User account with specified email id not unlocked
	 */
	@Test
	void testUnlockUserAccountFailed() throws UserNotFoundException{
		logger.info("[START] testUnlockUserAccountFailed()");
		RestTemplate restTemplate = new RestTemplate();
		assertThrows(BadRequest.class,
				()->{
					User user=restTemplate.getForObject("http://localhost:8080/myapp/admin/riya@gmail.com",User.class);
					restTemplate.put("http://localhost:8080/myapp/admin/unlock/riya@gmail.com",user,User.class);
				}
			);
	
		logger.error("user does not exists!!");
		logger.info("[END] testUnlockUserAccountFailed()");
	}

	/*
	 * User with specified email id not found
	 */	
	@Test
	void testFindUserByEmailIdFailed() throws UserNotFoundException{
		RestTemplate restTemplate = new RestTemplate();
		logger.info("[START] testFindUserByEmailIdFailed()");
		assertThrows(BadRequest.class,
				()->{
					restTemplate.getForEntity("http://localhost:8080/myapp/admin/riya@gmail.com",User.class);
				}
			);
		logger.error("user does not exists!!");		
		logger.info("[END] testFindUserByEmailIdFailed()");
	}
	
	
}

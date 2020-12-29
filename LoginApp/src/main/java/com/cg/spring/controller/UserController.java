package com.cg.spring.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.exception.InvalidDataException;
import com.cg.spring.exception.InvalidUserDetails;
import com.cg.spring.json.User;
import com.cg.spring.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@CrossOrigin("*")
@RequestMapping("/myapp")
@Api(value = "User realted RESt api")
public class UserController {

	@Autowired
	private UserService userService;
	
    private static final Logger logger = LogManager.getLogger(UserController.class);

	
	@ApiOperation(value = "Register a user")
	@ApiResponses(value= {
			@ApiResponse(code = 200,message = "User registered successfully "),
			@ApiResponse(code = 404,message = "User already registered ")}
	)
	@PostMapping(value="/user", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createNewUser(@Valid @RequestBody User user) throws InvalidUserDetails{
		User user2=userService.createUser(user);
		if(user2!= null)
		{
			logger.info( "User registered successfully!!");
			return new ResponseEntity<>(user2, HttpStatus.CREATED);

		}else {
			
			logger.warn("User already exists !!!!");
			return new ResponseEntity<>(user2, HttpStatus.BAD_REQUEST);			
		}
	}
	
	@ApiOperation(value = "user login")
	@ApiResponses(value= {
			@ApiResponse(code = 200,message = "Login successfully"),
			@ApiResponse(code = 402,message = "Login Failed")}
	)
	@GetMapping(value="/user/{emailId}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> userLogin(@PathVariable(value="emailId") String emailId,
				@PathVariable(value="password") String password) throws InvalidDataException {
				User user=userService.userLogin(emailId, password);
				if(user!=null)
				{
					logger.info( "Login successfully!");
					return new ResponseEntity<>(user, HttpStatus.CREATED);
				}
				else
				{
					logger.info("Login Failed!!!");
					
					return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
				}
	}
	
	@ApiOperation(value = "update users password")
	@ApiResponses(value= {
			@ApiResponse(code = 200,message = "Password updated successfully"),
			@ApiResponse(code = 404,message = "User does not exist with this emailId")}
	)
	@PutMapping(value = "/user/update/{emailId}/{password}", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updatePassword(@PathVariable(value="emailId") String emailId, 
			@PathVariable(value="password") String password, 
			@RequestBody String newpassword) throws InvalidDataException {
	
			User user=userService.updatePassword(emailId, password, newpassword);
			if(user!=null)
			{
				logger.info( "Password updated successfully!!");
				return new ResponseEntity<>(user, HttpStatus.CREATED);
			}
			else
			{
				logger.info("User does not exist with this emailId!!!");
				return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
			}
	}
	
}

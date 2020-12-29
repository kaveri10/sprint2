package com.cg.spring.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.exception.InvalidDataException;
import com.cg.spring.exception.UserNotFoundException;
import com.cg.spring.json.User;
import com.cg.spring.service.AdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
@RequestMapping("/myapp")
@Api(value = "User realted RESt api")
public class AdminController {

	@Autowired
	AdminService adminService;
    private static final Logger logger = LogManager.getLogger(AdminController.class);

	@ApiOperation(value = "Returns all users")
	@ApiResponses(value= {
			@ApiResponse(code = 200,message = "List of all users"),
			@ApiResponse(code = 404,message = "No users found")}
	)
	@GetMapping(value="/admin", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> findAllUser() {
		logger.info("List of all registered users");
		logger.error("List of all registered users");
		logger.debug("List of all registered users");
		logger.warn("List of all registered users");

		return adminService.findAllUser();
	}
	
	@ApiOperation(value = "Returns user with specified email")
	@ApiResponses(value= {
			@ApiResponse(code = 200,message = "User Details "),
			@ApiResponse(code = 404,message = "No user found")}
	)
	@GetMapping(value="/admin/{email}", produces=MediaType.APPLICATION_JSON_VALUE)
	public User findUserByEmailId(@PathVariable("email") String emailId) 
			throws UserNotFoundException {
		logger.info("Finding user by email id");
		return adminService.getUserByEmailId(emailId);
	}
	
	@PutMapping(value = "/admin/unlock/{email}",produces =MediaType.APPLICATION_JSON_VALUE )
	public User UnlockUserAccount(@PathVariable("email") String emailId) throws UserNotFoundException, InvalidDataException {
		logger.info("Unlocking user account");

		return adminService.UnlockUserAccount(emailId);
	}
	
}

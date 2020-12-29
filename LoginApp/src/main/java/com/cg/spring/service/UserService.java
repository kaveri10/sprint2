package com.cg.spring.service;

import com.cg.spring.exception.InvalidDataException;
import com.cg.spring.json.User;

public interface UserService {

	User createUser(User user);
	public User userLogin(String emailId,String password) throws InvalidDataException;
	public User updatePassword(String emailId, String oldpassword, String newpassword) throws InvalidDataException;

}

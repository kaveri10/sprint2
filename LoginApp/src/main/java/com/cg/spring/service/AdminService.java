package com.cg.spring.service;

import java.util.List;

import com.cg.spring.exception.InvalidDataException;
import com.cg.spring.exception.UserNotFoundException;
import com.cg.spring.json.User;

public interface AdminService {

	List<User> findAllUser();

	User getUserByEmailId(String emailId) throws UserNotFoundException;

	User UnlockUserAccount(String emailId)  throws UserNotFoundException, InvalidDataException;

}

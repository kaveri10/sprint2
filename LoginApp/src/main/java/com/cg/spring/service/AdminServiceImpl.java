package com.cg.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.entity.UserEntity;
import com.cg.spring.exception.InvalidDataException;
import com.cg.spring.exception.UserNotFoundException;
import com.cg.spring.json.User;
import com.cg.spring.repo.UserRepo;
import com.cg.spring.util.UserUtil;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private UserRepo userRepo;

    private static final Logger logger = LogManager.getLogger(AdminServiceImpl.class);

	
	@Override
	public List<User> findAllUser() {
		List<UserEntity> userEntities = userRepo.findAll();
		List<User> users = new ArrayList<>();
		users=UserUtil.convertUserEntityListToUser(userEntities);
		return users;
	}

	@Override
	public User getUserByEmailId(String emailId) throws UserNotFoundException {
		Optional<UserEntity> opUserEntity = userRepo.findByEmailId(emailId);
		if(opUserEntity.isPresent()) {
			UserEntity userEntity = opUserEntity.get();
			
			User user=UserUtil.convertUserEntityToUser(userEntity);
			return user;
		}
		else {
			logger.error("getUserByEmailId : user does not exist");
			throw new UserNotFoundException("user : " + emailId);
		}
	}

	@Override
	public User UnlockUserAccount(String emailId) throws UserNotFoundException,InvalidDataException {

		Optional<UserEntity> opUserEntity = userRepo.findByEmailId(emailId);
		if(opUserEntity.isPresent()) {
			UserEntity userEntity = opUserEntity.get();
			if(userEntity.getAttempts()>=3) {
				userEntity.setAttempts(0);
				logger.info("User account unlocked!!!");
				userEntity=userRepo.save(userEntity);
			}
			else {
				logger.error("User account already active!!");
				throw new InvalidDataException("User account already active!!");
			}
			User user=UserUtil.convertUserEntityToUser(userEntity);
			return user;
		}
		else {
			logger.error("UnlockUserAccount : user does not exist");
			throw new UserNotFoundException("user : " + emailId+" not exists!!");
		}
	}
	
}

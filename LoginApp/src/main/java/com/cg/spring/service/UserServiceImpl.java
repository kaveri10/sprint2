package com.cg.spring.service;


import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.entity.UserEntity;
import com.cg.spring.exception.InvalidDataException;
import com.cg.spring.json.User;
import com.cg.spring.repo.UserRepo;
import com.cg.spring.util.UserUtil;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public User createUser(User user) {
		Optional<UserEntity> userEntity = userRepo.findByEmailId(user.getEmailId());
		if(!userEntity.isPresent()) {
			logger.debug("Regitering a user");
			UserEntity userEntity1 = userRepo.save(UserUtil.covertUserToUSerEntity(user));
			return UserUtil.convertUserEntityToUser(userEntity1);
		}else {
			logger.warn("User alredy exists !!!!");
			return null;
		}
	}

	@Override
	public User userLogin(String emailId, String password) throws InvalidDataException {
		Optional<UserEntity> opuserEntity = userRepo.findByEmailId(emailId);
		if(opuserEntity.isPresent())
		{
			UserEntity userEntity = opuserEntity.get();
			logger.debug("user login");
			int n=userEntity.getAttempts();
			if( n<3) {
					if(userEntity.getPassword().equals(password)){
						return UserUtil.convertUserEntityToUser(userEntity);
					}else{
						userEntity.setAttempts(n+1);
						userEntity=userRepo.save(userEntity);
						logger.info("Invalid Password");
						throw new InvalidDataException("Invalid Password");
					}
			}else{
				logger.info("Reached maximum limit");
				throw new InvalidDataException("Reached maximum limit");
			}			
		}
		else
		{
			logger.info("Login Failed");
			throw new InvalidDataException("Login Failed");

		}
	}
	
	@Override
	public User updatePassword(String emailId, String oldpassword, String newpassword) throws InvalidDataException {

		Optional<UserEntity> opuserEntity = userRepo.findByEmailId(emailId);
		if(opuserEntity.isPresent())
		{
			UserEntity userEntity = opuserEntity.get();
			if(userEntity.getPassword().equals(oldpassword))
			{
				logger.info("password updated");
				userEntity.setPassword(newpassword);
				userEntity=userRepo.save(userEntity);
				return UserUtil.convertUserEntityToUser(userEntity);
			}
			else
			{
				throw new InvalidDataException("current password is wrong:");
			}
		}
		else
		{
				throw new InvalidDataException("Could not find any user with this emailId : " + emailId);
		}
	}


}

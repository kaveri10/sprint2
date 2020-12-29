package com.cg.spring.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cg.spring.entity.UserEntity;
import com.cg.spring.json.User;

public class UserUtil {


	// convert user entity object to user object
	public static User convertUserEntityToUser(UserEntity userEntity) {
		return new User(userEntity.getUserId(), userEntity.getName(), userEntity.getDob(), userEntity.getEmailId(), userEntity.getPassword(), userEntity.getAttempts(), userEntity.getPhoneNo(), userEntity.getAddress(), userEntity.getUnivName());

	} 
	
	// convert user object to user object entity
	public static UserEntity covertUserToUSerEntity(User user) {
		return new UserEntity(user.getEmailId(), user.getName(), user.getDob(), user.getPassword(), user.getPhoneNo(), user.getAddress(), user.getUnivName(),user.getAttempts());
	}
	
	//converts user entity list into user objects list
	public static List<User> convertUserEntityListToUser(List<UserEntity> entities) {
		Iterator<UserEntity> itr = entities.iterator();
		List<User> users = new ArrayList();
		
		while (itr.hasNext()) {
			UserEntity userEntity1=itr.next();
			
			users.add(new User(userEntity1.getUserId(), userEntity1.getName(), userEntity1.getDob(), userEntity1.getEmailId(), userEntity1.getPassword(), userEntity1.getAttempts(), userEntity1.getPhoneNo(), userEntity1.getAddress(), userEntity1.getUnivName()));
		}
		return users;
	}
	
}

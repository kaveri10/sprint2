package com.cg.spring.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.spring.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByEmailId(String emailId);
	
}

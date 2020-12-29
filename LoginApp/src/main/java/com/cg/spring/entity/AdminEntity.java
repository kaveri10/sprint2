package com.cg.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin" ,schema = "public")
public class AdminEntity {

	@Id
	@GeneratedValue
	@Column(name = "admin_id")
	private long adminId;
	
	@Column(name="email" , nullable = false, unique = true)
	private String email;
	
	@Column(name="password", nullable = false)
	private String password;

	public AdminEntity() {
		super();
	}

	
	public AdminEntity(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}


	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

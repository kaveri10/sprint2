package com.cg.spring.json;

public class Admin {


	private long adminId;

	private String email;

	private String password;
	
	
	
	public Admin() {
		super();
	}



	public Admin(long adminId, String email, String password) {
		super();
		this.adminId = adminId;
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



	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", email=" + email + ", password=" + password + "]";
	}

}

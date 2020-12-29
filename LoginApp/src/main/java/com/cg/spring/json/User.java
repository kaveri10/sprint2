package com.cg.spring.json;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;


public class User {

	@ApiModelProperty(value="User's Id")	
	@JsonIgnore
	private long userId;
	
	@NotBlank
	@NotNull
	@Pattern(regexp = "^[a-zA-Z\\s]+$")
	@ApiModelProperty(value="User's Name")
	private String name;
	
	@ApiModelProperty(value="User Date of Birth")
	@Past( message="Please provide a valid date of birth")
	private LocalDate dob;
	
	@NotBlank
	@NotNull
	@ApiModelProperty(value="User's email id")
	@Email( message="Please provide a valid email address")
	private String emailId;
	
	@ApiModelProperty(value="User's password")
	@NotNull
	@NotBlank
	@Pattern(regexp = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$", message="Please provide a valid password as")
	private String password;
	
	@ApiModelProperty(value="User's login attempt")	
	@JsonIgnore
	@Min(value = 0)
	private int attempts;
	
	@ApiModelProperty(value="User's phone ")
	private Long phoneNo;
	
	@NotNull
	@ApiModelProperty(value="User's address ")	
	private String address;
	
	@NotNull
	@ApiModelProperty(value="User's university name")	
	private String univName;
	
	public User() {
		super();
	}
	
	
	



	public User(@NotBlank @NotNull @Pattern(regexp = "^[a-zA-Z\\s]+$") String name,
			@Past(message = "Please provide a valid date of birth") String dob,
			@NotBlank @NotNull @Email(message = "Please provide a valid email address") String emailId,
			@NotNull @NotBlank @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Please provide a valid password as") String password,
			Long phoneNo, String address, String univName) {
		super();
		this.name = name;
		this.dob = LocalDate.parse(dob);
		this.emailId = emailId;
		this.password = password;
		this.phoneNo = phoneNo;
		this.address = address;
		this.univName = univName;
	}






	public User(long userId, @NotBlank @NotNull @Pattern(regexp = "^[a-zA-Z\\s]+$") String name,
			@Past(message = "Please provide a valid date of birth") LocalDate dob,
			@NotBlank @NotNull @Email(message = "Please provide a valid email address") String emailId,
			@NotNull @NotBlank @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Please provide a valid password as") String password,
			int attempts, Long phoneNo, String address, String univName) {
		super();
		this.userId = userId;
		this.name = name;
		this.dob = dob;
		this.emailId = emailId;
		this.password = password;
		this.attempts = attempts;
		this.phoneNo = phoneNo;
		this.address = address;
		this.univName = univName;
	}




	public User(long userId, @NotBlank @NotNull @Pattern(regexp = "^[a-zA-Z\\s]+$") String name,
			@Past(message = "Please provide a valid date of birth") String dob,
			@NotBlank @NotNull @Email(message = "Please provide a valid email address") String emailId,
			@NotNull @NotBlank @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Please provide a valid password as") String password,
			@Min(0) int attempts, Long phoneNo, String address, String univName) {
		super();
		this.userId = userId;
		this.name = name;
		this.dob =  LocalDate.parse(dob);
		this.emailId = emailId;
		this.password = password;
		this.attempts = attempts;
		this.phoneNo = phoneNo;
		this.address = address;
		this.univName = univName;
	}






	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getUnivName() {
		return univName;
	}


	public void setUnivName(String univName) {
		this.univName = univName;
	}


	public int getAttempts() {
		return attempts;
	}


	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", dob=" + dob + ", emailId=" + emailId + ", password="
				+ password + ", attempts=" + attempts + ", phoneNo=" + phoneNo + ", address=" + address + ", univName="
				+ univName + "]";
	}

}

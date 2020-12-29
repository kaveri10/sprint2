package com.cg.spring.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user" ,schema = "public")
public class UserEntity {

	
		@Id
		@GeneratedValue
		@Column(name = "userid")
		private long userId;
		
		@Column(name="email" , nullable = false, unique = true)
		private String emailId;
				
		@Column(name="name")
		private String name;
		
		@Column(name="dob")
		private LocalDate dob;

		@Column(name="password", nullable = false)
		private String password;
		
		@Column(name="phone")
		private Long phoneNo;
		
		@Column(name = "address")
		private String address;
		
		@Column(name = "univ_name")
		private String univName;
		
		@Column(name="login_attempts")
		private int attempts;
		
		public UserEntity() {
			super();
		}

		public UserEntity(String emailId, String name, LocalDate dob, String password, Long phoneNo, String address,
				String univName, int attempts) {
			super();
			this.emailId = emailId;
			this.name = name;
			this.dob = dob;
			this.password = password;
			this.phoneNo = phoneNo;
			this.address = address;
			this.univName = univName;
			this.attempts = attempts;
		}

		public UserEntity(String emailId, String name, String dob, String password, Long phoneNo, String address,
				String univName, int attempts) {
			this.emailId = emailId;
			this.name = name;
			this.dob = LocalDate.parse(dob);
			this.password = password;
			this.phoneNo = phoneNo;
			this.address = address;
			this.univName = univName;
			this.attempts = attempts;

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

		public int getAttempts() {
			return attempts;
		}

		public void setAttempts(int attempts) {
			this.attempts = attempts;
		}

		@Override
		public String toString() {
			return "UserEntity [userId=" + userId + ", emailId=" + emailId + ", name=" + name + ", dob=" + dob
					+ ", password=" + password + ", phoneNo=" + phoneNo + ", address=" + address + ", univName="
					+ univName + ", attempts=" + attempts + "]";
		}

		

		
}

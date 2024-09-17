package com.pizza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String staffName;
	private String username;
	
	@Column(length = 255)
	private String password;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String orgemail;
	
	private String role;
	
	private boolean pwdchanged = false;
	
	public Staff() {
		super();
	}

	public Staff(Long id, String staffName, String username, String password, String email, String orgemail,
			String role, boolean pwdchanged) {
		super();
		this.id = id;
		this.staffName = staffName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.orgemail = orgemail;
		this.role = role;
		this.pwdchanged = pwdchanged;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", staffName=" + staffName + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", orgemail=" + orgemail + ", role=" + role + ", pwdchanged=" + pwdchanged + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrgemail() {
		return orgemail;
	}

	public void setOrgemail(String orgemail) {
		this.orgemail = orgemail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isPwdchanged() {
		return pwdchanged;
	}

	public void setPwdchanged(boolean pwdchanged) {
		this.pwdchanged = pwdchanged;
	}

}

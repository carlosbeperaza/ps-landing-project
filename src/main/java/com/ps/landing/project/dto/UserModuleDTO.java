package com.ps.landing.project.dto;

import java.util.Date;
import java.util.List;

public class UserModuleDTO {
	private Long id;
	private String name;
	private String lastname;
	private String username;
	private String email;
	private String password;
	private boolean status;
	private Date registrationDate;
	private Date updateDate;
	private List<ModuleDTO> modules;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public List<ModuleDTO> getModules() {
		return modules;
	}
	public void setModules(List<ModuleDTO> modules) {
		this.modules = modules;
	}
	
	
	

}

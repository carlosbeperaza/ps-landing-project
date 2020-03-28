package com.ps.landing.project.dto;

import java.util.Date;
import java.util.List;


public class RoleDTO {
	
	private Long id;
    private String name;
    private String description;
    private Date createDate;
    private Date lastUpdateDate;
    private boolean status;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<ModuleDTO> getModules() {
		return modules;
	}
	public void setModules(List<ModuleDTO> modules) {
		this.modules = modules;
	}
}

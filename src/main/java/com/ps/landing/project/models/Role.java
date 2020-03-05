package com.ps.landing.project.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="roles")
public class Role implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=30)
	private String name;
	
	@Column(length=100)
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="role_modules", joinColumns= @JoinColumn(name="id_role"),
	inverseJoinColumns=@JoinColumn(name="id_module"),
	uniqueConstraints= {@UniqueConstraint(columnNames= {"id_role", "id_module"})})
	private List<Module> module;
	
	


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




	public List<Module> getModules() {
		return module;
	}




	public void setModules(List<Module> modules) {
		this.module = modules;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

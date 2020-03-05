package com.ps.landing.project.models;

import java.io.Serializable;
import java.sql.Timestamp;
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
    private Timestamp createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastUpdateDate;
	
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




	public Timestamp getCreation_date() {
		return creation_date;
	}




	public void setCreation_date(Timestamp creation_date) {
		this.creation_date = creation_date;
	}




	public Timestamp getLast_update_date() {
		return last_update_date;
	}




	public void setLast_update_date(Timestamp last_update_date) {
		this.last_update_date = last_update_date;
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

package com.ps.landing.project.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="sub_modules")
public class SubModule implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=60, nullable = false)
	private String name;
	
	@Column(length=100, nullable = false)
	private String description;
	
	@Column(nullable = false)
	private SubModule parent;
	
	@Column(length=100, nullable = false)
	private String url;
	
	@Column(length=100, nullable = false)
	private String icon;
	@Column()
	private Boolean status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp creation_date;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp last_update_date;
	
	
	

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




	public SubModule getParent() {
		return parent;
	}




	public void setParent(SubModule parent) {
		this.parent = parent;
	}




	public String getUrl() {
		return url;
	}




	public void setUrl(String url) {
		this.url = url;
	}




	public String getIcon() {
		return icon;
	}




	public void setIcon(String icon) {
		this.icon = icon;
	}




	public Boolean getStatus() {
		return status;
	}




	public void setStatus(Boolean status) {
		this.status = status;
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




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

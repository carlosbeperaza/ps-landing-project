package com.ps.landing.project.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	@Column(name = "creation_date")
	 @Temporal(TemporalType.TIMESTAMP)
	 @CreationTimestamp
	 private Date createDate;

	@Column(name = "last_update_date")
	 @Temporal(TemporalType.TIMESTAMP)
	 @UpdateTimestamp
	 private Date lastUpdateDate;
	
	
	

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




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

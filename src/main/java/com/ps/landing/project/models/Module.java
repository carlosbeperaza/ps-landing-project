package com.ps.landing.project.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



@Entity
@Table(name="modules")
public class Module implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=60, nullable = false)
	private String name;
	
	@Column(length=100, nullable = false)
	private String description;
	

	@Column(length=100, nullable = false)
	private String url;
	
	@Column(length=100, nullable = false)
	private String icon;
	
	
	private boolean status;
	
	@Column(name = "creation_date")
	 @Temporal(TemporalType.TIMESTAMP)
	 @CreationTimestamp
	 private Date createDate;

	@Column(name = "last_update_date")
	 @Temporal(TemporalType.TIMESTAMP)
	 @UpdateTimestamp
	 private Date lastUpdateDate;
	
	 @OneToMany(cascade = CascadeType.ALL)
	 @JoinColumn(name = "parent")
	 private List<SubModule> subModules;
	
	

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

	

	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
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

	

	public List<SubModule> getSubModules() {
		return subModules;
	}

	public void setSubModules(List<SubModule> subModules) {
		this.subModules = subModules;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

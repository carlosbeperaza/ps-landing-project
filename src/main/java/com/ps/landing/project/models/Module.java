package com.ps.landing.project.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;



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
	
	
	@Column()
	private Boolean status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp creation_date;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp last_update_date;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "sub_modules", joinColumns = @JoinColumn(name = "parent") )
	private List<SubModule> subModule;
	
	

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
	
	
}
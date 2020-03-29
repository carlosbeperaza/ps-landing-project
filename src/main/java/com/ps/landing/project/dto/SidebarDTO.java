package com.ps.landing.project.dto;

import java.util.List;

public class SidebarDTO {

	private Long id;
	private String name;
	private String url;
	private String icon;
	private List<SidebarDTO> children;

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

	public List<SidebarDTO> getChildren() {
		return children;
	}

	public void setChildren(List<SidebarDTO> children) {
		this.children = children;
	}

}

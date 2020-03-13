package com.example.NewsService.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.validation.constraints.Size;

@Entity
public class Councilor {
	@EmbeddedId
	private CouncilorIdentity councilorIdentity;
	
	public Councilor(){}
	
	public Councilor(CouncilorIdentity councilorIdentity, String name, @Size(max = 800) String url) {
		super();
		this.councilorIdentity = councilorIdentity;
		this.name = name;
		this.url = url;
	}

	public CouncilorIdentity getCouncilorIdentity() {
		return councilorIdentity;
	}

	public void setCouncilorIdentity(CouncilorIdentity councilorId) {
		this.councilorIdentity = councilorId;
	}
	
	@Column(name="name")
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Size(max = 800)
	private String url;	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Councilor [councilorIdentity=" + councilorIdentity + ", name=" + name + ", url=" + url + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((councilorIdentity == null) ? 0 : councilorIdentity.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Councilor other = (Councilor) obj;
		if (councilorIdentity == null) {
			if (other.councilorIdentity != null)
				return false;
		} else if (!councilorIdentity.equals(other.councilorIdentity))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
}

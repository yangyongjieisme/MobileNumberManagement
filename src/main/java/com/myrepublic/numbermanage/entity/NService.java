package com.myrepublic.numbermanage.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.transaction.annotation.Transactional;

/**
 * @date 2018/11/25
 * @desc entity
 */
@Entity(name="Service")
@Table(name = "Service_tbl")
@Transactional
public class NService implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "service",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<UserMobileService> userMobileServices;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserMobileService> getUserMobileServices() {
		return userMobileServices;
	}

	public void setUserMobileServices(List<UserMobileService> userMobileServices) {
		this.userMobileServices = userMobileServices;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Service: ");
		sb.append(this.id);
		sb.append(", name:");
		sb.append(this.name);
		return sb.toString();

	}

}

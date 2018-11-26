package com.myrepublic.numbermanage.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

/**
 * @date 2018/11/25
 * @desc entity
 */
@Entity
@Table(name = "User_Mobile_Service_tbl")
@Transactional
public class UserMobileService implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "created")
	private Date created;
	
	
	@Column(name = "status")
	private String status;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_mobile_id", nullable = false)
	private UserMobile userMobile;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "service_id", nullable = false)
	private NService service;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserMobile getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(UserMobile userMobile) {
		this.userMobile = userMobile;
	}

	public NService getService() {
		return service;
	}

	public void setService(NService service) {
		this.service = service;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("MobileService: ");
		sb.append(this.id);
		sb.append(", status");
		sb.append(this.status);
		sb.append(", created");
		sb.append(this.created);

		sb.append(service.toString());
		return sb.toString();
		

	}

}

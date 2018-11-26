package com.myrepublic.numbermanage.entity;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.transaction.annotation.Transactional;

/**
 * @date 2018/11/25
 * @desc entity
 */
@Entity
@Table(name = "User_Mobile_tbl")
@Transactional
public class UserMobile implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "created")
	private Date created;

	@Column(name = "status")
	private String status;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "mobile_id", nullable = false)
	private Mobile mobile;

	@OneToMany(mappedBy = "userMobile",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("created desc")
	private List<UserMobileService> userMobileServices;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Mobile getMobile() {
		return mobile;
	}

	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
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
		sb.append("MobileService: ");
		sb.append(this.id);
		sb.append(", status");
		sb.append(this.status);
		sb.append(", created");
		sb.append(this.created);
		

		if (userMobileServices != null) {

			for (UserMobileService ums : userMobileServices) {
				sb.append(ums.toString());
			}

		}
		return sb.toString();

	}

}

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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.transaction.annotation.Transactional;

/**
 * @date 2018/11/25
 * @desc entity
 */
@Entity
@Table(name = "Mobile_tbl")
@Transactional
public class Mobile implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "number")
	private String number;
	
	@Column(name = "used")
	private String used;
	
	@Version
	private Date version;
	
	@OneToMany(mappedBy = "mobile",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("created desc")
	private List<UserMobile> userMobiles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public List<UserMobile> getUserMobiles() {
		return userMobiles;
	}

	public void setUserMobiles(List<UserMobile> userMobiles) {
		this.userMobiles = userMobiles;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Mobile: ");
		sb.append(this.id);
		sb.append(", ");
		sb.append(this.number);

		if (userMobiles != null) {

			for (UserMobile um : userMobiles) {
				sb.append(um.toString());
			}

		}
		return sb.toString();

	}

}

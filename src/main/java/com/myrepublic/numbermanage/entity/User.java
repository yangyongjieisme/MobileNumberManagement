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
@Table(name = "User_tbl")
@Transactional
public class User implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "account")
	private String account;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("created desc")
	private List<UserMobile> userMobiles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public List<UserMobile> getUserMobiles() {
		return userMobiles;
	}

	public void setUserMobiles(List<UserMobile> userMobiles) {
		this.userMobiles = userMobiles;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User: ");
		sb.append(this.id);
		sb.append(", ");
		sb.append(this.account);

		if (userMobiles != null) {

			for (UserMobile um : userMobiles) {
				sb.append(um.toString());
			}

		}
		return sb.toString();

	}

}

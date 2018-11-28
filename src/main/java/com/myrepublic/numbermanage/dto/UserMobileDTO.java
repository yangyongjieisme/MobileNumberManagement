package com.myrepublic.numbermanage.dto;

import java.util.Date;
/**
 * @date 2018/11/25
 * @desc DTO
 */
public class UserMobileDTO {

	private String account;
	private String action;
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}

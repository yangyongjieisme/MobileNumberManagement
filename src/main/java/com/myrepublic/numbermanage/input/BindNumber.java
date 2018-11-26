package com.myrepublic.numbermanage.input;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @date 2018/11/25
 * @desc input object
 */
public class BindNumber {

	@NotNull
	@JSONField(name = "number")
	private String number;
	@NotNull
	@JSONField(name = "account")
	private String account;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}

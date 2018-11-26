package com.myrepublic.numbermanage.dto;

import java.util.List;

/**
 * @date 2018/11/25
 * @desc DTO
 */
public class UserDTO {

	private String account;
	List<MobileDTO> bindMobiles;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public List<MobileDTO> getBindMobiles() {
		return bindMobiles;
	}
	public void setBindMobiles(List<MobileDTO> bindMobiles) {
		this.bindMobiles = bindMobiles;
	}
	
	
}

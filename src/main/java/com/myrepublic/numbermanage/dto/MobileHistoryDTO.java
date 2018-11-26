package com.myrepublic.numbermanage.dto;

import java.util.List;

public class MobileHistoryDTO {

	private String number;
	private List<UserMobileDTO> userMobiles;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public List<UserMobileDTO> getUserMobiles() {
		return userMobiles;
	}

	public void setUserMobiles(List<UserMobileDTO> userMobiles) {
		this.userMobiles = userMobiles;
	}

}

package com.myrepublic.numbermanage.dto;

import java.util.List;

/**
 * @date 2018/11/25
 * @desc DTO
 */
public class MobileDTO {

	String number;
	List<ServiceDTO> services;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public List<ServiceDTO> getServices() {
		return services;
	}
	public void setServices(List<ServiceDTO> services) {
		this.services = services;
	}
	
	
}

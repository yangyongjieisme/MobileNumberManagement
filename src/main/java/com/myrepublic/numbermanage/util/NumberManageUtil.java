package com.myrepublic.numbermanage.util;

import java.util.HashMap;
import java.util.Map;

import com.myrepublic.numbermanage.input.BindService;

/**
 * @date 2018/11/25
 * @desc utility class
 */
public class NumberManageUtil {
	public static String INTERNAL_ERROR = "There is some problem,please try again later";
	public static String HTTP_404_ERROR = "Invalid request";
	public static String HTTP_500_ERROR = "bad request";
	public static String HTTP_403_ERROR = "Not Authorized";
	public static String MOBILE_EMPTY = "Mobile Number must be provided";
	public static String USER_EMPTY = "User Account must be provided";
	public static String Service_EMPTY = "Service Name must be provided";
	public static String MOBILE_BAD ="Invalid mobile number";
	
	public static String NUMBER_USED = "Number already used";
	public static String NUMBER_NOT_USED = "Number not used or bind by another user";
	public static String SERVICE_ALREADY_BIND = "Service already bind by this user";
	public static String SERVICE_NOT_BIND = "Service not previously bind by this user";
	
	public static String MOBILE_STATUS_USED = "1";
	public static String MOBILE_STATUS_UNUSED = "0";
	public static String STATUS_ACTIVE = "1";
	public static String STATUS_INACTIVE = "0";
	public static String BIND = "Bind";
	public static String UNBIND = "unBind";
	/**
	 * @date 2018/11/25
	 * @desc check empty string
	 */
	public static boolean validateEmpty(String input) {

		return (input == null) || (input.length() == 0);
	}

	/**
	 * @date 2018/11/25
	 * @desc wrap good response
	 */
	public static Map<String, Object> successResponse(Object data) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 200);
		map.put("data", data);
		return map;

	}
	/**
	 * @date 2018/11/25
	 * @desc wrap bad response
	 */
	public static Map<String, Object> errorResponse(String message) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		map.put("messge", message);
		return map;
	}
	/**
	 * @date 2018/11/25
	 * @desc construct lock key
	 */
	public static String toLockKey(BindService bs) {
		
		return bs.getAccount()+"_"+bs.getNumber()+"_"+bs.getService();
	}
}

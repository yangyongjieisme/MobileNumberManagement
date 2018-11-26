package com.myrepublic.numbermanage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myrepublic.numbermanage.exception.InputException;
import com.myrepublic.numbermanage.input.BindNumber;
import com.myrepublic.numbermanage.input.BindService;
import com.myrepublic.numbermanage.service.NumberManageService;
import com.myrepublic.numbermanage.util.NumberManageUtil;

/**
 * @author yongjie
 * @date 2018/11/25
 * @desc Number Management controller
 */
@RestController
public class NumberManageController {

	@Resource
	private NumberManageService numberManageService;

	/**
	 * @date 2018/11/25
	 * @desc get all users
	 */
	@RequestMapping(value="/getAllUsers",method = RequestMethod.GET)
	public Map<String, Object> getAllUsers() throws Exception {

		return NumberManageUtil.successResponse(numberManageService.getAllUsers());
	}
	
	/**
	 * @date 2018/11/25
	 * @desc get all mobiles
	 */
	@RequestMapping(value="/getAllMobiles",method = RequestMethod.GET)
	public Map<String, Object> getAllMobiles() throws Exception {

		return NumberManageUtil.successResponse(numberManageService.getAllMobiles());
	}

	/**
	 * @date 2018/11/25
	 * @desc get all services
	 */
	@RequestMapping(value="/getAllServices",method = RequestMethod.GET)
	public Map<String, Object> getAllServices() throws Exception {

		return NumberManageUtil.successResponse(numberManageService.getAllServices());
	}

	/**
	 * @date 2018/11/25
	 * @desc get all available mobiles
	 */
	@RequestMapping(value="/getAvailableMobiles",method = RequestMethod.GET)
	public Map<String, Object> getAvailableMobiles() throws Exception {

		return NumberManageUtil.successResponse(numberManageService.getAvailableMobiles());
	}
	

	/**
	 * @date 2018/11/25
	 * @desc get current status of a mobile
	 */
	@RequestMapping(value="/getCurrentStatus",method = RequestMethod.GET)
	public Map<String, Object> getCurrentStatus(@RequestParam Map<String, Object> paramMap) throws Exception {
		if (NumberManageUtil.validateEmpty((String) paramMap.get("number"))) {

			throw new InputException(NumberManageUtil.MOBILE_EMPTY);
		}

		return NumberManageUtil.successResponse(numberManageService.getCurrentStatus((String) paramMap.get("number")));
	}
	
	/**
	 * @date 2018/11/25
	 * @desc get mobile history
	 */
	@RequestMapping(value="/getMobileHistory",method = RequestMethod.GET)
	public Map<String, Object> getMobileHistory(@RequestParam Map<String, Object> paramMap) throws Exception {
		if (NumberManageUtil.validateEmpty((String) paramMap.get("number"))) {

			throw new InputException(NumberManageUtil.MOBILE_EMPTY);
		}

		return NumberManageUtil.successResponse(numberManageService.getMobileHistory((String) paramMap.get("number")));
	}
	/**
	 * @date 2018/11/25
	 * @desc bind number
	 */
	@RequestMapping(value="/bindNumber",method = RequestMethod.POST)
	public Map<String, Object> bindNumber(@RequestParam Map<String, Object> paramMap,
			@Valid @RequestBody BindNumber input, Errors errors) throws Exception {
		
		if (errors.hasErrors()) {
	       throw new InputException(errors.getAllErrors().toString());
	    }
		
		return NumberManageUtil.successResponse(numberManageService.bindNumber(input));
	}
	
	/**
	 * @date 2018/11/25
	 * @desc unbind number
	 */
	@RequestMapping(value="/unBindNumber",method = RequestMethod.POST)
	public Map<String, Object> unBindNumber(@RequestParam Map<String, Object> paramMap,
			@Valid @RequestBody BindNumber input, Errors errors) throws Exception {
		
		if (errors.hasErrors()) {
	       throw new InputException(errors.getAllErrors().toString());
	    }
		
		return NumberManageUtil.successResponse(numberManageService.unBindNumber(input));
	}
	
	/**
	 * @date 2018/11/25
	 * @desc bind service
	 */
	@RequestMapping(value="/bindService",method = RequestMethod.POST)
	public Map<String, Object> bindService(@RequestParam Map<String, Object> paramMap,
			@Valid @RequestBody BindService input, Errors errors) throws Exception {
		
		if (errors.hasErrors()) {
	       throw new InputException(errors.getAllErrors().toString());
	    }
		
		return NumberManageUtil.successResponse(numberManageService.bindService(input));
	}
	
	/**
	 * @date 2018/11/25
	 * @desc bind service
	 */
	@RequestMapping(value="/unBindService",method = RequestMethod.POST)
	public Map<String, Object> unBindService(@RequestParam Map<String, Object> paramMap,
			@Valid @RequestBody BindService input, Errors errors) throws Exception {
		
		if (errors.hasErrors()) {
	       throw new InputException(errors.getAllErrors().toString());
	    }
		
		return NumberManageUtil.successResponse(numberManageService.unBindService(input));
	}
	
	/**
	 * @date 2018/11/25
	 * @desc get a user's full picture
	 */
	@RequestMapping(value="/getUserFullPicture",method = RequestMethod.GET)
	public Map<String, Object> getUserFullPicture(@RequestParam Map<String, Object> paramMap) throws Exception {
		
		if (NumberManageUtil.validateEmpty((String) paramMap.get("account"))) {

			throw new InputException(NumberManageUtil.USER_EMPTY);
		}
		
		return NumberManageUtil.successResponse(numberManageService.getUserFullPicture((String) paramMap.get("account")));
	}
	/**
	 * @date 2018/11/25
	 * @desc info
	 */
	@RequestMapping(value="/info",method = RequestMethod.GET)
	public Map<String, Object> info() throws Exception {

		return NumberManageUtil.successResponse("Number Management Server Running!");
	}
}

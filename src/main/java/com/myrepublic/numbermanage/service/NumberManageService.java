package com.myrepublic.numbermanage.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.alturkovic.lock.jdbc.alias.JdbcLocked;
import com.myrepublic.numbermanage.dao.MobileDao;
import com.myrepublic.numbermanage.dao.ServiceDao;
import com.myrepublic.numbermanage.dao.UserDao;
import com.myrepublic.numbermanage.dto.MobileDTO;
import com.myrepublic.numbermanage.dto.MobileHistoryDTO;
import com.myrepublic.numbermanage.dto.ServiceDTO;
import com.myrepublic.numbermanage.dto.UserDTO;
import com.myrepublic.numbermanage.dto.UserMobileDTO;
import com.myrepublic.numbermanage.entity.Mobile;
import com.myrepublic.numbermanage.entity.NService;
import com.myrepublic.numbermanage.entity.User;
import com.myrepublic.numbermanage.entity.UserMobile;
import com.myrepublic.numbermanage.entity.UserMobileService;
import com.myrepublic.numbermanage.exception.InputException;
import com.myrepublic.numbermanage.input.BindNumber;
import com.myrepublic.numbermanage.input.BindService;
import com.myrepublic.numbermanage.util.NumberManageUtil;

/**
 *
 * @date 2018/11/25
 * @desc business logic for Number Management
 */
@Service
public class NumberManageService {
	private static Logger logger = LogManager.getLogger(NumberManageService.class);
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	@Autowired
	private UserDao userDao;
	@Autowired
	private MobileDao mobileDao;
	@Autowired
	private ServiceDao serviceDao;

	/**
	 * @date 2018/11/25
	 * @desc get all users
	 */

	@Transactional(readOnly = true)
	public List<UserDTO> getAllUsers() throws Exception {

		List<User> users = userDao.loadAll();
		List<UserDTO> result = new ArrayList<UserDTO>();
		if (users != null) {

			for (User user : users) {

				UserDTO userdto = new UserDTO();
				userdto.setAccount(user.getAccount());
				result.add(userdto);
			}
		}

		return result;
	}

	/**
	 * @date 2018/11/25
	 * @desc get all mobiles
	 */

	@Transactional(readOnly = true)
	public List<MobileDTO> getAllMobiles() throws Exception {

		return toMobileDTO(mobileDao.loadAll());
	}

	/**
	 * @date 2018/11/25
	 * @desc get all mobiles
	 */

	@Transactional(readOnly = true)
	public List<ServiceDTO> getAllServices() throws Exception {

		List<NService> services = serviceDao.loadAll();
		List<ServiceDTO> result = new ArrayList<ServiceDTO>();

		if (services != null) {

			for (NService service : services) {

				ServiceDTO servicedto = new ServiceDTO();
				servicedto.setName(service.getName());
				result.add(servicedto);
			}
		}
		return result;
	}

	/**
	 * @date 2018/11/25
	 * @desc get all mobiles
	 */

	@Transactional(readOnly = true)
	public List<MobileDTO> getAvailableMobiles() throws Exception {

		return toMobileDTO(mobileDao.getAvailableMobiles());
	}

	/**
	 * @date 2018/11/25
	 * @desc get all mobiles
	 */

	@Transactional(readOnly = true)
	public String getCurrentStatus(String number) throws Exception {

		Mobile mobile = mobileDao.getByNumber(number);
		if (mobile == null) {
			throw new InputException("Invalid mobile number");
		}
		return mobile.getUsed().equals(NumberManageUtil.MOBILE_STATUS_USED) ? NumberManageUtil.BIND
				: NumberManageUtil.UNBIND;

	}

	/**
	 * @date 2018/11/25
	 * @desc get all history of a mobile
	 */

	@Transactional(readOnly = true)
	public MobileHistoryDTO getMobileHistory(String number) throws Exception {

		MobileHistoryDTO result = new MobileHistoryDTO();
		List<UserMobileDTO> umdtos = new ArrayList<UserMobileDTO>();
		Mobile mobile = mobileDao.getByNumber(number);
		List<UserMobile> userMobiles = mobile.getUserMobiles();
		result.setNumber(number);
		if (userMobiles != null) {
			for (UserMobile userMobile : userMobiles) {

				UserMobileDTO umdto = new UserMobileDTO();
				umdto.setAccount(userMobile.getUser().getAccount());
				umdto.setDate(userMobile.getCreated());
				umdto.setAction(userMobile.getStatus().equals(NumberManageUtil.STATUS_ACTIVE) ? NumberManageUtil.BIND
						: NumberManageUtil.UNBIND);
				umdtos.add(umdto);
			}

		}
		result.setUserMobiles(umdtos);
		return result;
	}

	/**
	 * @date 2018/11/25
	 * @desc bind Number
	 */

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean bindNumber(BindNumber input) throws Exception {

		Mobile mobile = mobileDao.getByNumber(input.getNumber());
		User user = userDao.getByAccount(input.getAccount());
		if (mobile.getUsed().equals(NumberManageUtil.MOBILE_STATUS_USED)) {
			throw new InputException(NumberManageUtil.NUMBER_USED);
		}
		mobile.setUsed(NumberManageUtil.MOBILE_STATUS_USED);
		UserMobile userMobile = new UserMobile();
		userMobile.setMobile(mobile);
		userMobile.setStatus(NumberManageUtil.STATUS_ACTIVE);
		userMobile.setUser(user);
		userMobile.setCreated(new Date());
		mobile.getUserMobiles().add(userMobile);
		mobileDao.saveOrUpdate(mobile);
		return true;
	}

	/**
	 * @date 2018/11/25
	 * @desc bind Number
	 */

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean unBindNumber(BindNumber input) throws Exception {

		Mobile mobile = mobileDao.getByNumber(input.getNumber());
		User user = userDao.getByAccount(input.getAccount());

		List<UserMobile> userMobiles = mobile.getUserMobiles();
		UserMobile userMobile = null;
		boolean isUsed = false;
		if (userMobiles != null && userMobiles.size() != 0) {
			userMobile = userMobiles.get(0);
			isUsed = (userMobile.getUser().getAccount().equals(input.getAccount()))
					&& userMobile.getStatus().equals(NumberManageUtil.STATUS_ACTIVE);
		}
		if (!isUsed) {
			throw new InputException(NumberManageUtil.NUMBER_NOT_USED);
		}

		mobile.setUsed(NumberManageUtil.MOBILE_STATUS_UNUSED);
		userMobile = new UserMobile();
		userMobile.setMobile(mobile);
		userMobile.setStatus(NumberManageUtil.STATUS_INACTIVE);
		userMobile.setUser(user);
		userMobile.setCreated(new Date());

		mobile.getUserMobiles().add(userMobile);
		mobileDao.saveOrUpdate(mobile);
		return true;
	}

	/**
	 * @date 2018/11/25
	 * @desc bind Service
	 */

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@JdbcLocked(expression = "T(com.myrepublic.numbermanage.util.NumberManageUtil).toLockKey(#input)")
	public boolean bindService(BindService input) throws Exception {
		
		Mobile mobile = mobileDao.getByNumber(input.getNumber());
		List<UserMobile> userMobiles = mobile.getUserMobiles();
		UserMobile userMobile = null;
		boolean isUsed = false;
		if (userMobiles != null && userMobiles.size() != 0) {
			userMobile = userMobiles.get(0);
			isUsed = (userMobile.getUser().getAccount().equals(input.getAccount()))
					&& userMobile.getStatus().equals(NumberManageUtil.STATUS_ACTIVE);
		}
		if (!isUsed) {
			throw new InputException(NumberManageUtil.NUMBER_NOT_USED);
		}
		NService service = serviceDao.getByName(input.getService());
		List<UserMobileService> userMobileServices = userMobile.getUserMobileServices();
		if (checkServiceBind(userMobileServices, input.getService())) {

			throw new InputException(NumberManageUtil.SERVICE_ALREADY_BIND);
		}

		UserMobileService userMobileService = new UserMobileService();
		userMobileService.setService(service);
		userMobileService.setStatus(NumberManageUtil.STATUS_ACTIVE);
		userMobileService.setUserMobile(userMobile);
		userMobileService.setCreated(new Date());
		userMobile.getUserMobileServices().add(userMobileService);
		mobileDao.saveOrUpdate(mobile);
		return true;
	}

	/**
	 * @date 2018/11/25
	 * @desc unbind Service
	 */

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@JdbcLocked(expression = "T(com.myrepublic.numbermanage.util.NumberManageUtil).toLockKey(#input)")
	public boolean unBindService(BindService input) throws Exception {

		Mobile mobile = mobileDao.getByNumber(input.getNumber());

		List<UserMobile> userMobiles = mobile.getUserMobiles();
		UserMobile userMobile = null;
		boolean isUsed = false;
		if (userMobiles != null && userMobiles.size() != 0) {
			userMobile = userMobiles.get(0);
			isUsed = (userMobile.getUser().getAccount().equals(input.getAccount()))
					&& userMobile.getStatus().equals(NumberManageUtil.STATUS_ACTIVE);
		}
		if (!isUsed) {
			throw new InputException(NumberManageUtil.NUMBER_NOT_USED);
		}
		NService service = serviceDao.getByName(input.getService());
		List<UserMobileService> userMobileServices = userMobile.getUserMobileServices();
		if (!checkServiceBind(userMobileServices, input.getService())) {

			throw new InputException(NumberManageUtil.SERVICE_NOT_BIND);
		}

		UserMobileService userMobileService = new UserMobileService();
		userMobileService.setService(service);
		userMobileService.setStatus(NumberManageUtil.STATUS_INACTIVE);
		userMobileService.setUserMobile(userMobile);
		userMobileService.setCreated(new Date());
		userMobile.getUserMobileServices().add(userMobileService);
		mobileDao.saveOrUpdate(mobile);
		return true;
	}

	/**
	 * @date 2018/11/25
	 * @desc get a user's full picture
	 */

	@Transactional(readOnly = true)
	public UserDTO getUserFullPicture(String account) throws Exception {

		UserDTO result = new UserDTO();
		result.setAccount(account);
		User user = userDao.getByAccount(account);
		List<UserMobile> activeUserMobiles = findAllActiveMobile(user.getUserMobiles());
		List<MobileDTO> mobiles = new ArrayList<MobileDTO>();
		for (UserMobile userMobile : activeUserMobiles) {
			MobileDTO mobiledto = new MobileDTO();
			mobiledto.setNumber(userMobile.getMobile().getNumber());
			List<UserMobileService> activeServices = findAllActiveService(userMobile.getUserMobileServices());
			List<ServiceDTO> services = new ArrayList<ServiceDTO>();
			for (UserMobileService userMobileService : activeServices) {

				ServiceDTO servicedto = new ServiceDTO();
				servicedto.setName(userMobileService.getService().getName());
				services.add(servicedto);

			}
			mobiledto.setServices(services);
			mobiles.add(mobiledto);
		}

		result.setBindMobiles(mobiles);
		return result;
	}

	/**
	 * @date 2018/11/25
	 * @desc private method for checking whether user bind a service with a number
	 */

	private boolean checkServiceBind(List<UserMobileService> userMobileServices, String name) {
		boolean isBind = false;
		if (userMobileServices != null && userMobileServices.size() != 0) {

			for (UserMobileService userMobileService : userMobileServices) {

				if (userMobileService.getService().getName().equals(name)) {
					if (userMobileService.getStatus().equals(NumberManageUtil.STATUS_ACTIVE)) {
						isBind = true;
					}

					break;
				}

			}

		}
		return isBind;
	}

	/**
	 * @date 2018/11/25
	 * @desc find all active services
	 */

	private List<UserMobileService> findAllActiveService(List<UserMobileService> userMobileServices) {
		Map<String, UserMobileService> serviceMap = new HashMap<String, UserMobileService>();

		if (userMobileServices != null && userMobileServices.size() != 0) {
			for (UserMobileService userMobileService : userMobileServices) {
				if (!serviceMap.keySet().contains(userMobileService.getService().getName())) {
					serviceMap.put(userMobileService.getService().getName(), userMobileService);

				}
			}

		}
		List<UserMobileService> result = new ArrayList<UserMobileService>();
		for (String key : serviceMap.keySet()) {

			if (serviceMap.get(key).getStatus().equals(NumberManageUtil.STATUS_ACTIVE)) {

				result.add(serviceMap.get(key));
			}
		}

		return result;
	}

	/**
	 * @date 2018/11/25
	 * @desc find all active mobile for a user
	 */

	private List<UserMobile> findAllActiveMobile(List<UserMobile> userMobiles) {
		Map<String, UserMobile> userMobileMap = new HashMap<String, UserMobile>();

		if (userMobiles != null && userMobiles.size() != 0) {
			for (UserMobile userMobile : userMobiles) {
				if (!userMobileMap.keySet().contains(userMobile.getMobile().getNumber())) {
					userMobileMap.put(userMobile.getMobile().getNumber(), userMobile);

				}
			}

		}
		List<UserMobile> result = new ArrayList<UserMobile>();
		for (String key : userMobileMap.keySet()) {

			if (userMobileMap.get(key).getStatus().equals(NumberManageUtil.STATUS_ACTIVE)) {

				result.add(userMobileMap.get(key));
			}
		}

		return result;
	}

	/**
	 * @date 2018/11/25
	 * @desc convert mobile to mobileDTO
	 */
	private List<MobileDTO> toMobileDTO(List<Mobile> mobiles) {

		List<MobileDTO> result = new ArrayList<MobileDTO>();
		if (mobiles != null) {

			for (Mobile mobile : mobiles) {

				MobileDTO mobiledto = new MobileDTO();
				mobiledto.setNumber(mobile.getNumber());
				result.add(mobiledto);
			}
		}
		return result;

	}
}

package com.myrepublic.numbermanage.dao;

import com.myrepublic.numbermanage.entity.User;

/**
 * @date 2018/11/25
 * @desc User DAO
 */
public interface UserDao extends GenericDao<User,Long>{
	
	public User getByAccount(String account);

}

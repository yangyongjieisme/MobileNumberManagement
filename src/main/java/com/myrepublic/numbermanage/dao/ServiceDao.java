package com.myrepublic.numbermanage.dao;

import com.myrepublic.numbermanage.entity.NService;

/**
 * @date 2018/11/25
 * @desc Service DAO
 */
public interface ServiceDao extends GenericDao<NService,Long>{
	
	public NService getByName(String name);

}

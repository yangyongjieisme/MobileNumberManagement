package com.myrepublic.numbermanage.dao;

import java.util.List;

import com.myrepublic.numbermanage.entity.Mobile;

/**
 * @date 2018/11/25
 * @desc Mobile DAO
 */

public interface MobileDao extends GenericDao<Mobile,Long>{
	
	public Mobile getByNumber(String number);
	
	public List<Mobile> getAvailableMobiles();

}

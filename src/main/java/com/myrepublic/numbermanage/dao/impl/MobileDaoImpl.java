package com.myrepublic.numbermanage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myrepublic.numbermanage.dao.MobileDao;
import com.myrepublic.numbermanage.entity.Mobile;

@Repository("mobileDao")
public class MobileDaoImpl extends GenericDaoImpl<Mobile,Long> implements MobileDao{

	
	public Mobile getByNumber(String number) {
		
		String hql="from Mobile m where m.number=?";
		List<Mobile> mobiles = find(hql, number);
		if (mobiles != null && mobiles.size() != 0) {
			return mobiles.get(0);
		}
		return null;

	}
	
	public List<Mobile> getAvailableMobiles(){
		
		String hql="from Mobile m where m.used='0'";
		return find(hql, null);

		
	}
}

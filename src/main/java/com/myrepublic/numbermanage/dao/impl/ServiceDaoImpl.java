package com.myrepublic.numbermanage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myrepublic.numbermanage.dao.ServiceDao;
import com.myrepublic.numbermanage.entity.NService;

@Repository("serviceDao")
public class ServiceDaoImpl extends GenericDaoImpl<NService,Long> implements ServiceDao{


public NService getByName(String name) {
		
		String hql="from Service s where s.name=?";
		List<NService> services = find(hql, name);
		if (services != null && services.size() != 0) {
			return services.get(0);
		}
		return null;

	}
}

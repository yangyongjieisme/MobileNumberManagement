package com.myrepublic.numbermanage.dao.impl;

import org.springframework.stereotype.Repository;

import com.myrepublic.numbermanage.dao.UserMobileServiceDao;
import com.myrepublic.numbermanage.entity.UserMobileService;

@Repository("userMobileServiceDao")
public class UserMobileServiceDaoImpl extends GenericDaoImpl<UserMobileService,Long> implements UserMobileServiceDao{

}

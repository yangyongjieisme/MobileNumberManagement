package com.myrepublic.numbermanage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myrepublic.numbermanage.dao.UserDao;
import com.myrepublic.numbermanage.entity.User;

@Repository("userDao")

public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {


	public User getByAccount(String account) {

		String hql = "from User u where u.account=?";
		List<User> users = find(hql, account);
		if (users != null && users.size() != 0) {
			return users.get(0);
		}
		return null;

	}
}

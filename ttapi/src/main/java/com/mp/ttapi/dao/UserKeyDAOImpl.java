package com.mp.ttapi.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mp.ttapi.domain.UserKey;

@Repository("userKeyDAO")
public class UserKeyDAOImpl implements UserKeyDAO {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public UserKey getUserKey(String username) {
		return (UserKey) sessionFactory.getCurrentSession().createCriteria(UserKey.class).add(Restrictions.eq("username", username)).uniqueResult();
	}

	@Override
	public void addUserKey(UserKey userKey) {
		sessionFactory.getCurrentSession().saveOrUpdate(userKey);
	}

}

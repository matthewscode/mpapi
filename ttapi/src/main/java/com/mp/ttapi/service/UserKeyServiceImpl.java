package com.mp.ttapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mp.ttapi.dao.UserKeyDAO;
import com.mp.ttapi.domain.UserKey;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserKeyServiceImpl implements UserKeyService {
	
	@Autowired
	private UserKeyDAO userKeyDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addUserKey(String username, String keyString) {
		UserKey userKey = userKeyDao.getUserKey(username);
		userKey.setUsername(username);
		userKey.setKeyString(keyString);
		userKeyDao.addUserKey(userKey);
	}
	
}

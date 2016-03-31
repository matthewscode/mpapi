package com.mp.ttapi.dao;

import com.mp.ttapi.domain.UserKey;

public interface UserKeyDAO {

	UserKey getUserKey(String username);

	void addUserKey(UserKey userKey);

}

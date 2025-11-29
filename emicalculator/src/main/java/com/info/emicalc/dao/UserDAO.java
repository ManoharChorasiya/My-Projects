package com.info.emicalc.dao;

import com.info.emicalc.entity.User;

public interface UserDAO {
	boolean register(User user);

	User login(String username, String password);

	boolean isUsernameTaken(String username);
}

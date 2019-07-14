package com.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.bean.Users;
import com.bean.Users;
import com.dao.UsersDao;

public class LoginService {
	UsersDao usersDao = null;
	public LoginService() {
		usersDao = new UsersDao();
	}
	
	public Users findUserByNameAndPassword(String name, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Users user = usersDao.findUserByNameAndPassword(name, password);
		return user;
	}
}

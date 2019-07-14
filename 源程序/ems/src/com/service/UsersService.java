package com.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.bean.Users;
import com.bean.Users;
import com.dao.UsersDao;

public class UsersService {
	UsersDao usersDao = null;
	public UsersService() {
		usersDao = new UsersDao();
	}

	public int deleteUsersById(int id) {
		return usersDao.deleteUsersById(id);
	}

	public List<Users> findAllUsers(Users user,int showpage,int pageSize) {
		int firstRecord = (showpage - 1)*pageSize+1;
		int lastRecord = showpage * pageSize;
		return usersDao.findAllUsers(user,firstRecord, lastRecord);
	}

	public Users findUsersById(int id) {
		// TODO Auto-generated method stub
		return usersDao.findUsersById(id);
	}

	public int updateUsers(Users users) {
		return usersDao.updateUsers(users);
	}
	public int findAllCounts(Users users) {
		int allRecord = usersDao.findAllCounts(users);
		return allRecord;
	}
	public int insertUsers(Users users) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return usersDao.insertUsers(users);
	}
}

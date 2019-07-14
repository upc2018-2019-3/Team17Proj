package com.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.bean.Type;
import com.dao.TypeDao;

public class TypeService {
	TypeDao typeDao = null;
	public TypeService() {
		typeDao = new TypeDao();
	}
	public int deleteTypeById(int id) {
		return typeDao.deleteTypeById(id);
	}

	public List<Type> findAllType(Type type,int showpage,int pageSize) {
		int firstRecord = (showpage - 1)*pageSize+1;
		int lastRecord = showpage * pageSize;
		return typeDao.findAllType(type,firstRecord, lastRecord);
	}

	public Type findTypeById(int id) {
		// TODO Auto-generated method stub
		return typeDao.findTypeById(id);
	}

	public int updateType(Type type) {
		return typeDao.updateType(type);
	}
	public int findAllCounts(Type type) {
		int allRecord = typeDao.findAllCounts(type);
		return allRecord;
	}
	public int insertType(Type type) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return typeDao.insertType(type);
	}
}

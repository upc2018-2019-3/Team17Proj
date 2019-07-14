package com.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.bean.Maintain;
import com.dao.MaintainDao;

public class MaintainService {
	MaintainDao maintainDao = null;
	public MaintainService() {
		maintainDao = new MaintainDao();
	}
	public int deleteMaintainById(int id) {
		return maintainDao.deleteMaintainById(id);
	}

	public List<Maintain> findAllMaintain(Maintain mai,int showpage,int pageSize) {
		int firstRecord = (showpage - 1)*pageSize+1;
		int lastRecord = showpage * pageSize;
		return maintainDao.findAllMaintain(mai,firstRecord, lastRecord);
	}

	public Maintain findMaintainById(int id) {
		// TODO Auto-generated method stub
		return maintainDao.findMaintainById(id);
	}

	public int updateMaintain(Maintain maintain) {
		return maintainDao.updateMaintain(maintain);
	}
	public int findAllCounts(Maintain equ) {
		int allRecord = maintainDao.findAllCounts(equ);
		return allRecord;
	}
	public int insertMaintain(Maintain maintain) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return maintainDao.insertMaintain(maintain);
	}
}

package com.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.bean.Reject;
import com.dao.RejectDao;

public class RejectService {
	RejectDao rejectDao = null;
	public RejectService() {
		rejectDao = new RejectDao();
	}
	public int deleteRejectById(int id,int eid) {
		return rejectDao.deleteRejectById(id,eid);
	}

	public List<Reject> findAllReject(Reject mai,int showpage,int pageSize) {
		int firstRecord = (showpage - 1)*pageSize+1;
		int lastRecord = showpage * pageSize;
		return rejectDao.findAllReject(mai,firstRecord, lastRecord);
	}

	public Reject findRejectById(int id) {
		// TODO Auto-generated method stub
		return rejectDao.findRejectById(id);
	}

	public int updateReject(Reject reject) {
		return rejectDao.updateReject(reject);
	}
	public int findAllCounts(Reject reject) {
		int allRecord = rejectDao.findAllCounts(reject);
		return allRecord;
	}
	public int insertReject(Reject reject) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return rejectDao.insertReject(reject);
	}
}

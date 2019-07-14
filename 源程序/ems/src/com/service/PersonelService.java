package com.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.bean.Personel;
import com.dao.PersonelDao;

public class PersonelService {
	PersonelDao personelDao = null;
	public PersonelService() {
		personelDao = new PersonelDao();
	}
	public int deletePersonelById(int id) {
		return personelDao.deletePersonelById(id);
	}

	public List<Personel> findAllPersonel(Personel per,int showpage,int pageSize) {
		int firstRecord = (showpage - 1)*pageSize+1;
		int lastRecord = showpage * pageSize;
		return personelDao.findAllPersonel(per,firstRecord, lastRecord);
	}

	public Personel findPersonelById(int id) {
		// TODO Auto-generated method stub
		return personelDao.findPersonelById(id);
	}

	public int updatePersonel(Personel personel) {
		return personelDao.updatePersonel(personel);
	}
	public int findAllCounts(Personel stu) {
		int allRecord = personelDao.findAllCounts(stu);
		return allRecord;
	}
	public int insertPersonel(Personel personel) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return personelDao.insertPersonel(personel);
	}
}

package com.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.bean.Section;
import com.dao.SectionDao;

public class SectionService {
	SectionDao sectionDao = null;
	public SectionService() {
		sectionDao = new SectionDao();
	}
	public int deleteSectionById(int id) {
		return sectionDao.deleteSectionById(id);
	}

	public List<Section> findAllSection(Section mai,int showpage,int pageSize) {
		int firstRecord = (showpage - 1)*pageSize+1;
		int lastRecord = showpage * pageSize;
		return sectionDao.findAllSection(mai,firstRecord, lastRecord);
	}

	public Section findSectionById(int id) {
		// TODO Auto-generated method stub
		return sectionDao.findSectionById(id);
	}

	public int updateSection(Section section) {
		return sectionDao.updateSection(section);
	}
	public int findAllCounts(Section section) {
		int allRecord = sectionDao.findAllCounts(section);
		return allRecord;
	}
	public int insertSection(Section section) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return sectionDao.insertSection(section);
	}
}

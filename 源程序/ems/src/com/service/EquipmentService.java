package com.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.bean.Equipment;
import com.dao.EquipmentDao;

public class EquipmentService {
	EquipmentDao equipmentDao = null;
	public EquipmentService() {
		equipmentDao = new EquipmentDao();
	}
	public int deleteEquipmentById(int id) {
		return equipmentDao.deleteEquipmentById(id);
	}

	public List<Equipment> findAllEquipment(Equipment per,int showpage,int pageSize) {
		int firstRecord = (showpage - 1)*pageSize+1;
		int lastRecord = showpage * pageSize;
		return equipmentDao.findAllEquipment(per,firstRecord, lastRecord);
	}
	
	public List<Equipment> findAllExpEquipment() {
		
		return equipmentDao.findAllExpEquipment();
	}
	
	public List searchEquipment() {
		
		return equipmentDao.searchEquipment();
	}

	public Equipment findEquipmentById(int id) {
		// TODO Auto-generated method stub
		return equipmentDao.findEquipmentById(id);
	}

	public int updateEquipment(Equipment equipment) {
		return equipmentDao.updateEquipment(equipment);
	}
	public int findAllCounts(Equipment equ) {
		int allRecord = equipmentDao.findAllCounts(equ);
		return allRecord;
	}
	public int insertEquipment(Equipment equipment) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return equipmentDao.insertEquipment(equipment);
	}
}

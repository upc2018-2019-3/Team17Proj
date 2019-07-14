package com.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bean.Equipment;
import com.bean.Equipment;
import com.bean.SearchEquipment;

public class EquipmentDao {
	PreparedStatement ps = null;

	ResultSet rs = null;

	DBSql mydb = null;

	public EquipmentDao() {
		mydb = new DBSql();
	}

	public int deleteEquipmentById(int id) {
		String sql = "delete from Equipment where eid = ?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setInt(1, id);
			i = ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			mydb.close();
		}
		return i;
	}

	public List<Equipment> findAllEquipment(Equipment equ, int firstRecord,
			int lastRecord) {
		List<Equipment> list = new ArrayList<Equipment>();
		Equipment equipment = null;
		// 1 2 3 4 5 6
		String sql = "select tt.eid,tt.ecode,tt.ename,tt.tid,tt.eworth,tt.eproducer,tt.eoutdate, "
				+ "tt.ebuydate,tt.esid,tt.estatus,t.tname,t.tcode,s.sname,s.scode,tt.eremarks,p.pcode,p.pname "
				+ "from(select *,row_number() over(order by ename ) rn  from equipment where 1=1 ";
		if (equ != null) {
			if (equ.getEname() != null)
				sql += " and ename like '%" + equ.getEname() + "%' ";
			if(equ.getTid()!=0)
			{
				sql += " and tid ="+equ.getTid();
			}
		}
		sql += ") tt    " + "left join type t on t.tid = tt.tid  "
				+ "left join section s on s.sid = tt.esid  "
				+ "left join personel p on p.pid = tt.pid  "
				+ " where  rn between " + firstRecord + " and " + lastRecord;

		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				equipment = new Equipment();
				equipment.setEid(rs.getInt(1));
				equipment.setEcode(rs.getString(2));
				equipment.setEname(rs.getString(3));
				equipment.setTid(rs.getInt(4));
				equipment.setEworth(rs.getFloat(5));
				equipment.setEproducer(rs.getString(6));
				equipment.setEoutdate(rs.getString(7));
				equipment.setEbuydate(rs.getString(8));
				equipment.setEsid(rs.getInt(9));
				equipment.setEstatus(rs.getString(10));
				equipment.setTname(rs.getString(11));
				equipment.setTcode(rs.getString(12));
				equipment.setSname(rs.getString(13));
				equipment.setScode(rs.getString(14));
				equipment.setEremarks(rs.getString(15));
				equipment.setPcode(rs.getString(16));
				equipment.setPname(rs.getString(17));
				list.add(equipment);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			mydb.close();
		}
		return list;
	}

	public Equipment findEquipmentById(int id) {
		Equipment equipment = null;
		String sql = "select tt.eid,tt.ecode,tt.ename,tt.tid,tt.eworth,tt.eproducer,tt.eoutdate, "
				+ "tt.ebuydate,tt.esid,tt.estatus,t.tname,t.tcode,s.sname,s.scode ,tt.pid,tt.eremarks"
				+ " from equipment tt "
				+ "left join type t on t.tid = tt.tid  "
				+ "left join section s on s.sid = tt.esid  "
				+ "  where tt.eid = " + id;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				equipment = new Equipment();
				equipment.setEid(rs.getInt(1));
				equipment.setEcode(rs.getString(2));
				equipment.setEname(rs.getString(3));
				equipment.setTid(rs.getInt(4));
				equipment.setEworth(rs.getFloat(5));
				equipment.setEproducer(rs.getString(6));
				equipment.setEoutdate(rs.getString(7));
				equipment.setEbuydate(rs.getString(8));
				equipment.setEsid(rs.getInt(9));
				equipment.setEstatus(rs.getString(10));
				equipment.setTname(rs.getString(11));
				equipment.setTcode(rs.getString(12));
				equipment.setSname(rs.getString(13));
				equipment.setScode(rs.getString(14));
				equipment.setPid(rs.getInt(15));
				equipment.setEremarks(rs.getString(16));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			mydb.close();
		}
		return equipment;
	}

	public int updateEquipment(Equipment equipment) {
		//		
		String sql = "";
		sql = " update equipment set ecode=?,ename=?,tid=?,eworth=?,eproducer=?,eoutdate=?,ebuydate=?,esid=?,estatus=?,pid=?,eremarks=? where eid=?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, equipment.getEcode());
			ps.setString(2, equipment.getEname());
			ps.setInt(3, equipment.getTid());
			ps.setFloat(4, equipment.getEworth());
			ps.setString(5, equipment.getEproducer());
			ps.setString(6, equipment.getEoutdate());
			ps.setString(7, equipment.getEbuydate());
			ps.setInt(8, equipment.getEsid());
			ps.setString(9, equipment.getEstatus());
			ps.setInt(10, equipment.getPid());
			ps.setString(11, equipment.getEremarks());
			ps.setInt(12, equipment.getEid());
			i = ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			mydb.close();
		}
		return i;
	}

	public int findAllCounts(Equipment equ) {
		int count = 0;
		String sql = "select count(*) from equipment where 1=1";
		if (equ != null) {
			if (equ.getEname() != null)
				sql += " and ename like '%" + equ.getEname() + "%' ";
			if(equ.getTid()!=0)
			{
				sql += " and tid ="+equ.getTid();
			}
		}
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			mydb.close();
		}
		return count;
	}

	public int insertEquipment(Equipment equipment)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "insert into equipment (ecode,ename,tid,eworth,eproducer,eoutdate,ebuydate,esid,estatus,pid,eremarks) values(?,?,?,?,?,?,?,?,?,?,?)";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, equipment.getEcode());
			ps.setString(2, equipment.getEname());
			ps.setInt(3, equipment.getTid());
			ps.setFloat(4, equipment.getEworth());
			ps.setString(5, equipment.getEproducer());
			ps.setString(6, equipment.getEoutdate());
			ps.setString(7, equipment.getEbuydate());
			ps.setInt(8, equipment.getEsid());
			ps.setString(9, equipment.getEstatus());
			ps.setInt(10, equipment.getPid());
			ps.setString(11, equipment.getEremarks());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			mydb.close();
		}
		return i;
	}

	public List searchEquipment() {
		List list = new ArrayList();
		String sql = "select  s.scode,s.sname,tt.eworth,tt.ecount,tt.esid "
				+ " from ( select esid,sum(eworth) eworth,count(*) ecount from equipment  where estatus='正常' group by esid )  tt    "
				+ "left join section s on s.sid = tt.esid  "
				+ " ";

		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				SearchEquipment searchEquipment = new SearchEquipment();
				searchEquipment.setScode(rs.getString(1));
				searchEquipment.setSname(rs.getString(2));
				searchEquipment.setEworth(rs.getFloat(3));
				searchEquipment.setEcount(rs.getInt(4));
				searchEquipment.setSid(rs.getInt(5));
				list.add(searchEquipment);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			mydb.close();
		}
		return list;
	}

	public List<Equipment> findAllExpEquipment() {
		List<Equipment> list = new ArrayList<Equipment>();
		Equipment equipment = null;
		// 1 2 3 4 5 6
		String sql = "select tt.eid,tt.ecode,tt.ename,tt.tid,tt.eworth,tt.eproducer,tt.eoutdate, "
				+ "tt.ebuydate,tt.esid,tt.estatus,t.tname,t.tcode,s.sname,s.scode,tt.eremarks,p.pcode,p.pname "
				+ " from equipment  tt    "
				+ "left join type t on t.tid = tt.tid  "
				+ "left join section s on s.sid = tt.esid  "
				+ "left join personel p on p.pid = tt.pid  "
				+ " where estatus='正常' ";

		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				equipment = new Equipment();
				equipment.setEid(rs.getInt(1));
				equipment.setEcode(rs.getString(2));
				equipment.setEname(rs.getString(3));
				equipment.setTid(rs.getInt(4));
				equipment.setEworth(rs.getFloat(5));
				equipment.setEproducer(rs.getString(6));
				equipment.setEoutdate(rs.getString(7));
				equipment.setEbuydate(rs.getString(8));
				equipment.setEsid(rs.getInt(9));
				equipment.setEstatus(rs.getString(10));
				equipment.setTname(rs.getString(11));
				equipment.setTcode(rs.getString(12));
				equipment.setSname(rs.getString(13));
				equipment.setScode(rs.getString(14));
				equipment.setEremarks(rs.getString(15));
				equipment.setPcode(rs.getString(16));
				equipment.setPname(rs.getString(17));
				list.add(equipment);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			mydb.close();
		}
		return list;
	}
}

package com.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Maintain;

public class MaintainDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBSql mydb = null;
	public MaintainDao() {
		mydb = new DBSql();
	}
	public int deleteMaintainById(int id) {
		String sql = "delete from Maintain where mid = ?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setInt(1, id);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			mydb.close();
		}
		return i;
	}

	public List<Maintain> findAllMaintain(Maintain main,int firstRecord, int lastRecord) {
		List<Maintain> list = new ArrayList<Maintain>();
		Maintain maintain = null;
	                        //      1      2         3           4     5         6
			String sql = "select tt.mid,tt.eid,tt.mhour,tt.mcharge,tt.mdate,tt.mremarks,tt.mperson, " +
					"mtel,e.ename,e.ecode,tt.mreson " +
					"from(select *,row_number() over(order by mid desc) rn  from maintain where 1=1 ";
			if(main!=null)
			{
				if(main.getEid()!=0)
					sql+=" and eid="+main.getEid();
			}
			sql+=") tt    " +
					"left join equipment e on e.eid = tt.eid  " +
					" where  rn between "+firstRecord +" and "+lastRecord ;
			
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				maintain = new Maintain();
				maintain.setMid(rs.getInt(1));
				maintain.setEid(rs.getInt(2));
				maintain.setMhour(rs.getFloat(3));
				maintain.setMcharge(rs.getFloat(4));
				maintain.setMdate(rs.getString(5));
				maintain.setMremarks(rs.getString(6));
				maintain.setMperson(rs.getString(7));
				maintain.setMtel(rs.getString(8));
				maintain.setEname(rs.getString(9));
				maintain.setEcode(rs.getString(10));
				maintain.setMreson(rs.getString(11));
				list.add(maintain);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
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
	
	public Maintain findMaintainById(int id) {
		Maintain maintain = null;
						//      1      2         3           4      5          6        7
		String sql = "select tt.mid,tt.eid,tt.mhour,tt.mcharge,tt.mdate,tt.mremarks,tt.mperson, " +
		"mtel,e.ename,e.ecode ,tt.mreson" +
				" from maintain tt " +
				"left join equipment e on e.eid = tt.eid  " +
				"  where tt.mid = "+id;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				maintain = new Maintain();
				maintain.setMid(rs.getInt(1));
				maintain.setEid(rs.getInt(2));
				maintain.setMhour(rs.getFloat(3));
				maintain.setMcharge(rs.getFloat(4));
				maintain.setMdate(rs.getString(5));
				maintain.setMremarks(rs.getString(6));
				maintain.setMperson(rs.getString(7));
				maintain.setMtel(rs.getString(8));
				maintain.setEname(rs.getString(9));
				maintain.setEcode(rs.getString(10));
				maintain.setMreson(rs.getString(11));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
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
		return maintain;
	}
	
	
	public int updateMaintain(Maintain maintain) {
//		
		String sql = "";
		sql = " update maintain set  eid=?,mhour=?,mcharge=?,mdate=?,mremarks=?,mperson=?,mtel=?,mreson=? where mid=?";
		int i =0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setInt(1, maintain.getEid());
			ps.setFloat(2, maintain.getMhour());
			ps.setFloat(3, maintain.getMcharge());
			ps.setString(4, maintain.getMdate());
			ps.setString(5, maintain.getMremarks());
			ps.setString(6, maintain.getMperson());
			ps.setString(7, maintain.getMtel());
			ps.setString(8, maintain.getMreson());
			ps.setInt(9, maintain.getMid());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			mydb.close();
		}
		return i;
	}
	public int findAllCounts(Maintain main) {
		int count = 0;
		String sql = "select count(*) from maintain where 1=1";
		if(main!=null)
		{
			if(main.getEid()!=0)
				sql+=" and eid="+main.getEid();
		}
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			mydb.close();
		}
		return count;
	}
	
	
	public int insertMaintain(Maintain maintain) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "insert into maintain (eid,mhour,mcharge,mdate,mremarks,mperson,mtel,mreson) values(?,?,?,?,?,?,?,?)";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setInt(1, maintain.getEid());
			ps.setFloat(2, maintain.getMhour());
			ps.setFloat(3, maintain.getMcharge());
			ps.setString(4, maintain.getMdate());
			ps.setString(5, maintain.getMremarks());
			ps.setString(6, maintain.getMperson());
			ps.setString(7, maintain.getMtel());
			ps.setString(8, maintain.getMreson());
			i = ps.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			mydb.close();
		}
		return i;
	}
	
	
}

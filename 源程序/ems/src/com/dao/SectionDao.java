package com.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Section;

public class SectionDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBSql mydb = null;
	public SectionDao() {
		mydb = new DBSql();
	}
	public int deleteSectionById(int id) {
		String sql = "delete from section where sid = ?";
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

	public List<Section> findAllSection(Section sec ,int firstRecord, int lastRecord) {
		List<Section> list = new ArrayList<Section>();
		Section section = null;
		String sql = "select tt.sid,tt.sname,tt.scode,tt.sremarks  from " +
				"(select *,row_number() over(order by sname) rn  from section) tt where rn between "+firstRecord +" and "+lastRecord;
		if(sec!=null&&sec.getSname()!=null)
		{
			sql+=" and sname like '%"+sec.getSname()+"%' ";
		}
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				section = new Section();
				section.setSid(rs.getInt(1));
				section.setSname(rs.getString(2));
				section.setScode(rs.getString(3));
				section.setSremarks(rs.getString(4));
				list.add(section);
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

	public Section findSectionById(int id) {
		Section section = null;
		String sql = "select tt.sid,tt.sname,tt.scode,tt.sremarks  from section tt where sid = "+id;
		try {
			ps = mydb.getCon().prepareStatement(sql);
//			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				section = new Section();
				section.setSid(rs.getInt(1));
				section.setSname(rs.getString(2));
				section.setScode(rs.getString(3));
				section.setSremarks(rs.getString(4));
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
		return section;
	}

	
	public int updateSection(Section section) {
		//tt.tid,tt.tname,tt.trealname,tt.tpassword,tsex
		String sql = "update section set  sname=?,scode=?,sremarks=? where sid=?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, section.getSname());
			ps.setString(2, section.getScode());
			ps.setString(3, section.getSremarks());
			ps.setInt(4, section.getSid());
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
	public int findAllCounts(Section sec) {
		int count = 0;
		String sql = "select count(*) from section where 1=1 ";
		if(sec!=null&&sec.getSname()!=null)
		{
			sql+=" and sname like '%"+sec.getSname()+"%' ";
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
	public int insertSection(Section section) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "insert into section (sname,scode,sremarks) values(?,?,?)";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, section.getSname());
			ps.setString(2, section.getScode());
			ps.setString(3, section.getSremarks());
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

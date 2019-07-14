package com.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Type;

public class TypeDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBSql mydb = null;
	public TypeDao() {
		mydb = new DBSql();
	}
	public int deleteTypeById(int id) {
		String sql = "delete from type where tid = ?";
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

	public List<Type> findAllType(Type ty,int firstRecord, int lastRecord) {
		List<Type> list = new ArrayList<Type>();
		Type type = null;
		String sql = "select tt.tid,tt.tname,tt.tcode,tt.tremarks  from " +
				"(select *,row_number() over(order by tname) rn  from type) tt where rn between "+firstRecord +" and "+lastRecord;
		if(ty!=null&&ty.getTname()!=null)
			sql +=" and tname like '%"+ty.getTname()+"%'";
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				type = new Type();
				type.setTid(rs.getInt(1));
				type.setTname(rs.getString(2));
				type.setTcode(rs.getString(3));
				type.setTremarks(rs.getString(4));
				list.add(type);
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

	public Type findTypeById(int id) {
		Type type = null;
		String sql = "select tt.tid,tt.tname,tt.tcode,tt.tremarks  from type tt where tid = "+id;
		try {
			ps = mydb.getCon().prepareStatement(sql);
//			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				type = new Type();
				type.setTid(rs.getInt(1));
				type.setTname(rs.getString(2));
				type.setTcode(rs.getString(3));
				type.setTremarks(rs.getString(4));
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
		return type;
	}

	
	public int updateType(Type type) {
		//tt.tid,tt.tname,tt.trealname,tt.tpassword,tsex
		String sql = "update type set  tname=?,tcode=?,tremarks=? where tid=?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, type.getTname());
			ps.setString(2, type.getTcode());
			ps.setString(3, type.getTremarks());
			ps.setInt(4, type.getTid());
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
	public int findAllCounts(Type ty) {
		int count = 0;
		String sql = "select count(*) from type where 1=1 ";
		if(ty!=null&&ty.getTname()!=null)
			sql +=" and tname like '%"+ty.getTname()+"%'";
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
	public int insertType(Type type) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "insert into type (tname,tcode,tremarks) values(?,?,?)";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, type.getTname());
			ps.setString(2, type.getTcode());
			ps.setString(3, type.getTremarks());
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

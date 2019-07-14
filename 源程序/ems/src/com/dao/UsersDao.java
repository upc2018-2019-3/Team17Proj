package com.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Users;
import com.bean.Users;

public class UsersDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBSql mydb = null;
	public UsersDao() {
		mydb = new DBSql();
	}
	
	public Users findUserByNameAndPassword(String uname, String upassword) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Users login = new Users();
		String sql = "";
		sql = "select uid,uname,upassword,utype ttype from users where uname = ? and  upassword=? ";
		
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, upassword);
			rs = ps.executeQuery();
			while(rs.next()){
				login.setUid(rs.getInt(1));
				login.setUname(rs.getString(2));
				login.setUtype(rs.getString(4));
				login.setUpassword(rs.getString(3));
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
		return login;
	}
	
	public int deleteUsersById(int id) {
		String sql = "delete from users where uid = ?";
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

	public List<Users> findAllUsers(Users user,int firstRecord, int lastRecord) {
		List<Users> list = new ArrayList<Users>();
		Users users = null;
		String sql = "select tt.uid,tt.uname,tt.upassword,tt.utype  from " +
				"(select *,row_number() over(order by uname) rn  from users where 1=1 " ;
		if(user!=null)
		{
			if(user.getUname()!=null)
				sql+=" and uname like '%"+user.getUname()+"%' ";
			if(user.getUtype()!=null)
				sql+=" and utype = '"+user.getUtype()+"' ";
		}
		sql +=" ) tt" +
				" where rn between "+firstRecord +" and "+lastRecord;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				users = new Users();
				users.setUid(rs.getInt(1));
				users.setUname(rs.getString(2));
				users.setUpassword(rs.getString(3));
				users.setUtype(rs.getString(4));
				list.add(users);
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

	public Users findUsersById(int id) {
		Users users = null;
		String sql = "select tt.uid,tt.uname,tt.upassword,tt.utype  " +
				"from users tt  where uid = "+id;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				users = new Users();
				users.setUid(rs.getInt(1));
				users.setUname(rs.getString(2));
				users.setUpassword(rs.getString(3));
				users.setUtype(rs.getString(4));
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
		return users;
	}

	
	public int updateUsers(Users users) {
		//tt.tid,tt.tname,tt.trealname,tt.tpassword,tsex
		String sql = "update users set uname=?,upassword=?,utype=?  where uid=?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, users.getUname());
			ps.setString(2, users.getUpassword());
			ps.setString(3, users.getUtype());
			ps.setInt(4, users.getUid());
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
	public int findAllCounts(Users user) {
		int count = 0;
		String sql = "select count(*) from users where 1=1";
		if(user!=null)
		{
			if(user.getUname()!=null)
				sql+=" and uname like '%"+user.getUname()+"%' ";
			if(user.getUtype()!=null)
				sql+=" and utype = '"+user.getUtype()+"' ";
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
	public int insertUsers(Users users) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "insert into users ( uname,upassword,utype) values(?,?,?)";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, users.getUname());
			ps.setString(2, users.getUpassword());
			ps.setString(3, users.getUtype());
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

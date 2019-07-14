package com.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Maintain;
import com.bean.Reject;

public class RejectDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBSql mydb = null;
	public RejectDao() {
		mydb = new DBSql();
	}
	public int deleteRejectById(int id,int eid) {
		String sql = "delete from reject where rid = ?";
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
		String sql2 = "update  equipment set estatus='Õý³£' where eid=?";
		try {
			ps = mydb.getCon().prepareStatement(sql2);
			ps.setInt(1, eid);		
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

	public List<Reject> findAllReject(Reject rej,int firstRecord, int lastRecord) {
		List<Reject> list = new ArrayList<Reject>();
		Reject reject = null;
		 String where = "";
		if(rej!=null)
		{
			if(rej.getEid()!=0)
			{
				where+=" and eid = "+rej.getEid()+" ";
			}
		}
		String sql = "select rr.rid,rr.eid,rr.rdate,rr.rdepreciation,rr.rremarks,rr.ename,rr.ecode from " +
				"(select r.*,e.ecode,e.ename,row_number() over(order by ename) rn  from reject r left join equipment e on e.eid = r.eid where 1=1 "+where +" ) rr where rn between "+firstRecord +" and "+lastRecord;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				reject = new Reject();
				reject.setRid(rs.getInt(1));
				reject.setEid(rs.getInt(2));
				reject.setRdate(rs.getString(3));
				reject.setRdepreciation(rs.getFloat(4));
				reject.setRremarks(rs.getString(5));
				reject.setEname(rs.getString(6));
				reject.setEcode(rs.getString(7));
				list.add(reject);
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

	public Reject findRejectById(int id) {
		Reject reject = null;
		String sql = "select rr.rid,rr.eid,rr.rdate,rr.rdepreciation,rr.rremarks,e.ename,e.ecode from reject rr" +
				" left join equipment e on e.eid = rr.eid  where rid = "+id;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				reject = new Reject();
				reject.setRid(rs.getInt(1));
				reject.setEid(rs.getInt(2));
				reject.setRdate(rs.getString(3));
				reject.setRdepreciation(rs.getFloat(4));
				reject.setRremarks(rs.getString(5));
				reject.setEname(rs.getString(6));
				reject.setEcode(rs.getString(7));
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
		return reject;
	}

	
	public int updateReject(Reject reject) {
		//ss.sid,ss.sname,ss.spassword,ss.sclass,ss.code,ss.srealname
		String sql = "update reject set eid=?,rdate=?,rdepreciation=?,rremarks=? where rid=?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setInt(1, reject.getEid());
			ps.setString(2, reject.getRdate());
			ps.setFloat(3, reject.getRdepreciation());
			ps.setString(4, reject.getRremarks());
			ps.setInt(5, reject.getRid());
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
	public int findAllCounts(Reject rej) {
		int count = 0;
		String where = "";
		if(rej!=null)
		{
			if(rej.getEid()!=0)
			{
				where+=" and eid = "+rej.getEid()+" ";
			}
		}
		String sql = "select count(*) from reject where 1=1 "+where;
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
	public int insertReject(Reject reject) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "insert into reject (eid,rdate,rdepreciation,rremarks) values(?,?,?,?)";
		
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setInt(1, reject.getEid());
			ps.setString(2, reject.getRdate());
			ps.setFloat(3, reject.getRdepreciation());
			ps.setString(4, reject.getRremarks());
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
		String sql2 = "update  equipment set estatus='±¨·Ï' where eid=?";
		try {
			ps = mydb.getCon().prepareStatement(sql2);
			ps.setInt(1, reject.getEid());		
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

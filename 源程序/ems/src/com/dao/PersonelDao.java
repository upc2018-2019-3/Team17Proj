package com.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Personel;

public class PersonelDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBSql mydb = null;
	public PersonelDao() {
		mydb = new DBSql();
	}
	public int deletePersonelById(int id) {
		String sql = "delete from personel where pid = ?";
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

	public List<Personel> findAllPersonel(Personel per,int firstRecord, int lastRecord) {
		List<Personel> list = new ArrayList<Personel>();
		Personel personel = null;
		String sql = "select tt.pid,tt.pcode,tt.pname,tt.psex,tt.page,tt.padd,tt.ptel,tt.pmail,tt.sid,tt.sname,tt.scode  from " +
				"(select p.*,s.sname,s.scode,row_number() over(order by pname) rn  from personel p left join section s on p.sid = s.sid) tt" +
				" where rn between "+firstRecord +" and "+lastRecord;
		if(per!=null&&per.getPname()!=null)
		{
			sql+=" and pname like '%"+per.getPname()+"%'";
		}
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				personel = new Personel();
				personel.setPid(rs.getInt(1));
				personel.setPcode(rs.getString(2));
				personel.setPname(rs.getString(3));
				personel.setPsex(rs.getString(4));
				personel.setPage(rs.getInt(5));
				personel.setPadd(rs.getString(6));
				personel.setPtel(rs.getString(7));
				personel.setPmail(rs.getString(8));
				personel.setSid(rs.getInt(9));
				personel.setSname(rs.getString(10));
				personel.setScode(rs.getString(11));
				list.add(personel);
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

	public Personel findPersonelById(int id) {
		Personel personel = null;
		String sql = "select tt.pid,tt.pcode,tt.pname,tt.psex,tt.page,tt.padd,tt.ptel,tt.pmail,tt.sid,s.sname,s.scode  " +
				"from personel tt left join section s on tt.sid = s.sid where pid = "+id;
		try {
			ps = mydb.getCon().prepareStatement(sql);
//			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				personel = new Personel();
				personel.setPid(rs.getInt(1));
				personel.setPcode(rs.getString(2));
				personel.setPname(rs.getString(3));
				personel.setPsex(rs.getString(4));
				personel.setPage(rs.getInt(5));
				personel.setPadd(rs.getString(6));
				personel.setPtel(rs.getString(7));
				personel.setPmail(rs.getString(8));
				personel.setSid(rs.getInt(9));
				personel.setSname(rs.getString(10));
				personel.setScode(rs.getString(11));
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
		return personel;
	}

	
	public int updatePersonel(Personel personel) {
		//tt.tid,tt.tname,tt.trealname,tt.tpassword,tsex
		String sql = "update personel set pcode=?,pname=?,psex=?,page=?,padd=?,ptel=?,pmail=?,sid=? where pid=?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, personel.getPcode());
			ps.setString(2, personel.getPname());
			ps.setString(3, personel.getPsex());
			ps.setInt(4, personel.getPage());
			ps.setString(5, personel.getPadd());
			ps.setString(6, personel.getPtel());
			ps.setString(7, personel.getPmail());
			ps.setInt(8, personel.getSid());
			ps.setInt(9, personel.getPid());
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
	public int findAllCounts(Personel per) {
		int count = 0;
		String sql = "select count(*) from personel where 1=1 ";
		if(per!=null&&per.getPname()!=null)
		{
			sql+=" and pname like '%"+per.getPname()+"%'";
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
	public int insertPersonel(Personel personel) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "insert into personel (pcode,pname,psex,page,padd,ptel,pmail,sid) values(?,?,?,?,?,?,?,?)";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, personel.getPcode());
			ps.setString(2, personel.getPname());
			ps.setString(3, personel.getPsex());
			ps.setInt(4, personel.getPage());
			ps.setString(5, personel.getPadd());
			ps.setString(6, personel.getPtel());
			ps.setString(7, personel.getPmail());
			ps.setInt(8, personel.getSid());			
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

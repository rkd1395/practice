package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import util.DBConnectionPool;
import vo.Member;

public class MemberDao {
//	DBConnectionPool dbConnectionPool;
	DataSource ds;
//	public void setDBConnectionPool(DBConnectionPool dbConnectionPool){
//		this.dbConnectionPool = dbConnectionPool;
//	}



	public List<Member> selectList() throws Exception{
		Connection conn = ds.getConnection();
//		Connection conn=dbConnectionPool.getConnection();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		stmt = conn.prepareStatement("select * from members");
		rs = stmt.executeQuery();
		ArrayList<Member> members = new ArrayList();
		while(rs.next())
		{
			members.add(new Member()
					.setNo(rs.getInt("MNO"))
					.setName(rs.getString("MNAME"))
					.setEmail(rs.getString("EMAIL"))
					.setCreatedDate(rs.getDate("CRE_DATE"))
					.setModifiedDate(rs.getDate("MOD_DATE")));
		};
		try {
			if(stmt!=null)stmt.close();
			if(rs!=null)rs.close();
			if(conn!=null)conn.close();
		}catch (Exception e) {
			throw e;
		}
		return members;	
	}

	public void insert(Member member)throws Exception{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
//		Connection conn = dbConnectionPool.getConnection();
		Connection conn = ds.getConnection();
		pstmt = conn.prepareStatement("insert into members values(MEMBERS_SEQ.nextval,?,?,?,sysdate,sysdate)");
		pstmt.setString(1, member.getEmail());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getName());
		rs = pstmt.executeQuery();
		if(rs!=null)rs.close();
		if(pstmt!=null)pstmt.close();
		if(conn!=null)conn.close();
	}

	public void delete(int no)throws Exception{
		Connection conn = ds.getConnection();
//		Connection conn=dbConnectionPool.getConnection();
		PreparedStatement pstmt = null;
		pstmt=conn.prepareStatement("delete from members where mno=?");
		pstmt.setInt(1,no);
		pstmt.executeUpdate();
		if(pstmt!=null)pstmt.close();
		if(conn!=null)conn.close();
	}

	public Member selectOne(int no)throws Exception{
		Connection conn = ds.getConnection();
//		Connection conn=dbConnectionPool.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		pstmt = conn.prepareStatement("select MNO,Email,Mname,Cre_date from members"
				+ " where mno=?");
		pstmt.setInt(1, no);
		rs = pstmt.executeQuery();
		if(rs.next()){
			Member member=new Member().setNo(rs.getInt("mno"))
					.setEmail(rs.getString("email"))
					.setName(rs.getString("mname"))
					.setCreatedDate(rs.getDate("cre_date"));
			try {
				if(pstmt!=null)pstmt.close();
				if(rs!=null)rs.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {
				throw e;
			}	
			return member;
		}
		try {
			if(pstmt!=null)pstmt.close();
			if(rs!=null)rs.close();
			if(conn!=null)conn.close();
		}catch (Exception e) {
			throw e;
		}
		return null;
	}

	public int update(Member member)throws Exception{
		Connection conn = ds.getConnection();
//		Connection conn=dbConnectionPool.getConnection();
		PreparedStatement pstmt=null;
		pstmt = conn.prepareStatement("update members set MNAME=?,EMAIL=? where MNO=?");
		pstmt.setString(1, member.getName());
		pstmt.setString(2, member.getEmail());
		pstmt.setInt(3,member.getNo());
		pstmt.executeUpdate();

		try {
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}catch (Exception e) {
			throw e;
		}
		return 1;
	}

	public String exist(String email,String password) throws Exception{
		Connection conn = ds.getConnection();
//		Connection conn=dbConnectionPool.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		pstmt = conn.prepareStatement("SELECT MNAME,EMAIL FROM MEMBERS WHERE EMAIL=? AND PWD=?");
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		rs = pstmt.executeQuery();
		if(rs.next()){
			return rs.getString("MNAME");
		}else{
			return null;
		}

	}



	public void setDataSource(DataSource datasource) {
		this.ds = datasource;		
	}

}

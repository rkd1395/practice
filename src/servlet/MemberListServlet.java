package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberListServlet extends HttpServlet {
	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>회원목록</title></head>");
		out.println("<body><a href='add'>신규회원</a></br>");
		out.println("</body></html>");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "kms", "1234");
			stmt = conn.prepareStatement("select * from members");
			rs = stmt.executeQuery();
			while(rs.next())
			{
				out.println(rs.getString("MNO"));
				out.println("<a href=update?mno="+rs.getString("MNO")+">"+rs.getString("MNAME")+"</a>");
				out.println(rs.getString("EMAIL"));
				out.println(rs.getString("CRE_DATE"));
				out.println("<a href=delete?mno="+rs.getString("MNO")+">"+"삭제"+"</a>");
				out.println("</br>");
			};
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null)conn.close();
				if(stmt!=null)stmt.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}

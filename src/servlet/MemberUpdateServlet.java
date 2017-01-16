package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberUpdateServlet extends HttpServlet {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ServletContext sc;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			sc = this.getServletContext();

			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"),
					sc.getInitParameter("id"),
					sc.getInitParameter("password"));
			pstmt = conn.prepareStatement("select MNO,Email,Mname,Cre_date from members"
					+ " where mno=?");
			pstmt.setString(1, req.getParameter("mno"));
			rs = pstmt.executeQuery();
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print("<html><head><title>회원상세정보</title></head><body>");
			out.print("<h1>회원정보</h1>");
			out.print("<form action='update' method='post'>");
			if(rs.next()){
				out.println("번호 : <input type='text' name='mno' value='"+ rs.getString("mno")+"'readonly><br>");
				out.println("이름 : <input type='text' name='mname' value='"+ rs.getString("mname")+"'><br>");
				out.println("이메일 : <input type='text' name='email' value='"+ rs.getString("email")+"'><br>");
				out.println("가입일 : <input type='text' value='"+ rs.getString("cre_date")+"'readonly><br>");
				out.print("<input type='submit' value='확인'>");
				out.print("<input type='button' value='삭제'"+
						"onclick='location.href=\"delete?mno="+rs.getString("mno")+"\"'>");
				out.print("<input type='button' value='취소'"+
						"onclick='location.href=\"list\"'>");
			}
			out.print("</body></html>");
			out.print("</form>");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		try {
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"),
					sc.getInitParameter("id"),
					sc.getInitParameter("password"));
			pstmt = conn.prepareStatement("update members set MNAME=?,EMAIL=? where MNO=?");
			pstmt.setString(1, req.getParameter("mname"));
			pstmt.setString(2, req.getParameter("email"));
			pstmt.setInt(3, Integer.parseInt(req.getParameter("mno")));
			pstmt.executeUpdate();
			resp.sendRedirect("list");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

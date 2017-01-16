package servlet;

import java.io.IOException;
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

public class MemberDeleteServlet extends HttpServlet {
	ServletContext sc;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sc = this.getServletContext();
		try {
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("id"),sc.getInitParameter("password"));
			pstmt=conn.prepareStatement("delete from members where mno=?");
			pstmt.setInt(1, Integer.parseInt(req.getParameter("mno")));
			pstmt.executeUpdate();
			resp.sendRedirect("list");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}

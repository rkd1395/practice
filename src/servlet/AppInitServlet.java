package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AppInitServlet extends HttpServlet {
	Connection conn;
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("-----------AppInitServlet 준비----------");
		super.init(config);
		ServletContext sc;
		sc = this.getServletContext();
		try {
			Class.forName((String) sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"),
					sc.getInitParameter("id"), 
					sc.getInitParameter("password"));
			sc.setAttribute("conn", conn);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		System.out.println("----------ApInitServlet 마무리----------");
		conn = (Connection)this.getServletContext().getAttribute("conn");
		try {
			if(conn!=null&&conn.isClosed()==false){conn.close();} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

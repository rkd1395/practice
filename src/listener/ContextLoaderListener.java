package listener;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import dao.MemberDao;
import util.DBConnectionPool;

public class ContextLoaderListener implements ServletContextListener {
	DBConnectionPool dbConnectionPool;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("contextInitiallized");
		ServletContext sc;
		sc = event.getServletContext();
		
		try {
			InitialContext initialContext = new InitialContext();
			DataSource datasource = (DataSource)initialContext.lookup("java:comp/env/jdbc/studydb");
//			dbConnectionPool = 
//					new DBConnectionPool((String) sc.getInitParameter("driver"),
//							(String)sc.getInitParameter("url"),
//							(String)sc.getInitParameter("id"),
//							(String)sc.getInitParameter("password"));
			//			Class.forName((String) sc.getInitParameter("driver"));
			//			Connection conn = DriverManager.getConnection(sc.getInitParameter("url"),
			//					sc.getInitParameter("id"), 
			//					sc.getInitParameter("password"));
			//			sc.setAttribute("conn", conn);
			MemberDao memberDao = new MemberDao();
			memberDao.setDataSource(datasource);
			sc.setAttribute("memberDao", memberDao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}

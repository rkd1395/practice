package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.Member;

public class MemberUpdateServlet extends HttpServlet {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ServletContext sc;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");

		try {
			MemberDao memberDao = (MemberDao)this.getServletContext().getAttribute("memberDao");
			Member member = memberDao.selectOne(Integer.parseInt(req.getParameter("mno")));
			req.setAttribute("member", member);
			req.setAttribute("viewUrl", "/member/MemberUpdate.jsp");
			//			RequestDispatcher rd = req.getRequestDispatcher("/member/MemberUpdate.jsp");
			//			rd.include(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		try {
			MemberDao memberDao = (MemberDao)req.getServletContext().getAttribute("memberDao");
			Member member = (Member)req.getAttribute("member");
			memberDao.update(member);
			req.setAttribute("viewUrl", "redirect:list.do");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		}

	}

}

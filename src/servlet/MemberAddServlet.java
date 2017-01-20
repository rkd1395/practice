package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.Member;

public class MemberAddServlet extends HttpServlet {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("viewUrl", "/member/MemberAdd.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		Member member = (Member)req.getAttribute("member");
		try {
			MemberDao memberDao = (MemberDao)req.getServletContext().getAttribute("memberDao");
			memberDao.insert(member);
			req.setAttribute("viewUrl", "redirect:list.do");
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
		}
	}

}

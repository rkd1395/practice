package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("viewUrl", "/auth/LoginForm.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
		try {
			Member member = (Member)req.getAttribute("member");
			member.setName((String)memberDao.exist(member.getEmail(), member.getPassword()));
			HttpSession session = req.getSession();
			session.setAttribute("member", member);
			if(member.getName()==null){
				req.setAttribute("viewUrl", "/auth/LoginFail.jsp");
			}else {
				req.setAttribute("viewUrl", "redirect:../member/list.do");}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

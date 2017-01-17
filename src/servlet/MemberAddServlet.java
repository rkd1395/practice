package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberAddServlet extends HttpServlet {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>회원 등록</title></head>");
		out.println("<body><h1>회원등록</h1>");
		out.println("<form action='add' method='post'>");
		out.println("이름 <input type='text' name='name'><br>");
		out.println("이메일 <input type='text' name='email'><br>");
		out.println("암호 <input type='password' name='password'><br>");
		out.println("<input type='submit' value='추가'><br>");
		out.println("<input type='reset' value='취소'><br>");
		out.println("</form>");
		out.println("</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		String password =  req.getParameter("password");
		String name = req.getParameter("name");
		try {
			conn = (Connection)this.getServletContext().getAttribute("conn");
			pstmt = conn.prepareStatement("insert into members values(MEMBERS_SEQ.nextval,?,?,?,sysdate,sysdate)");
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			rs = pstmt.executeQuery();

			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<html><head><title>가입성공</title></head></html>");
			out.println("<body><p>등록 성공입니다</p></body>");
			out.println("</html>");
			resp.addHeader("Refresh", "1;url=list");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

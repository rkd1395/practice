package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.Member;

public class DispatcherServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		String servletPath = req.getServletPath();

		String pageControllerPath = null;
		try{
			if("/member/list.do".equals(servletPath)){
				pageControllerPath = "/member/list";
			}

			else if("/member/add.do".equals(servletPath)){
				pageControllerPath = "/member/add";
				if(req.getParameter("email")!=null){
					req.setAttribute("member",new Member().setName(req.getParameter("name"))
							.setEmail(req.getParameter("email"))
							.setPassword(req.getParameter("password")));
				}
			}

			else if("/member/update.do".equals(servletPath)){
				pageControllerPath = "/member/update";
				if(req.getParameter("email")!=null){
					req.setAttribute("member",new Member().setName(req.getParameter("name"))
							.setEmail(req.getParameter("email"))
							.setPassword(req.getParameter("password")));
				}
			}

			else if("/member/delete.do".equals(servletPath)){
				req.setAttribute("mno",req.getParameter("mno"));
				pageControllerPath = "/member/delete";
			}

			else if("/auth/login.do".equals(servletPath)){
				pageControllerPath = "/auth/login";
				req.setAttribute("member", 
						new Member()
						.setEmail(req.getParameter("email"))
						.setPassword(req.getParameter("password")));
			}

			else if("/auth/logout.do".equals(servletPath)){
				pageControllerPath = "/auth/logout";
			}

			RequestDispatcher rd = req.getRequestDispatcher(pageControllerPath);
			rd.include(req, resp);

			String viewUrl = (String) req.getAttribute("viewUrl");
			if(viewUrl.startsWith("redirect:")){
				resp.sendRedirect(viewUrl.substring(9));
				return;
			}else{
				rd = req.getRequestDispatcher(viewUrl);
				rd.include(req, resp);
			}
		}catch(Exception e){
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
	}
}


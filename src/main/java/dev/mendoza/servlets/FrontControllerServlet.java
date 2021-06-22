package dev.mendoza.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class FrontControllerServlet extends HttpServlet {
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(0);
		switch(uri) {
			case "/ReimbursementManagement/invalidate": {
				session.invalidate();
				break;
			}
			case "/ReimbursementManagement/id": {
				response.getWriter().append("\n" + session.getId());
				break;
			}
			case "/ReimbursementManagement": {
				response.getWriter().append("\nHome page?");
				break;
			}
			case "/ReimbursementManagement/login": {
				response.sendRedirect("/ReimbursementManagement/LoginServlet");
				break;
			}
			default: {
				System.out.println("Default Case.");
				response.sendError(418, "BRB Making tea.");
				break;
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}

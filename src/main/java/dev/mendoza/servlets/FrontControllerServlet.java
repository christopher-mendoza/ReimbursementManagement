package dev.mendoza.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dev.mendoza.models.User;
import dev.mendoza.services.UserServiceImpl;

public class FrontControllerServlet extends HttpServlet {
	class LoginAttempt {
		public String un;
		public String pw;
	}
	
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "application/json");	
		HttpSession session = request.getSession();
		switch(uri) {
			case "/ReimbursementManagement/invalidate": {
				session.invalidate();
				break;
			}
			case "/ReimbursementManagement/id": {
				response.getWriter().append("\n" + session.getId());
				break;
			}
//			case "/ReimbursementManagement": {
//				
//				break;
//			}
			case "/ReimbursementManagement/adminlogin": {
				System.out.println("admin login");
				LoginAttempt login = this.gson.fromJson(request.getReader(), LoginAttempt.class);
				UserServiceImpl us = new UserServiceImpl();
				User u = us.getUserByUsername(login.un);
				if(u != null) {
					System.out.println("Admin " + u.getName() + " has logged in.");
					session.setAttribute("currentUser", u);
					response.getWriter().append("index.html");
				}
				else {
					System.out.println("Failed admin login attempt.");
				}
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
		String uri = request.getRequestURI();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "application/json");
		HttpSession session = request.getSession();
		switch(uri) {
			case "/ReimbursementManagement/login": {
				System.out.println("Got admin login");
				LoginAttempt login = this.gson.fromJson(request.getReader(), LoginAttempt.class);
				UserServiceImpl us = new UserServiceImpl();
				User u = us.getUserByUsername(login.un);
				if(u == null) {
					System.out.println("Failed admin login attempt.");
					break;
				}
				if(u.getUsername().equals(login.un) && u.getPassword().equals(login.pw)) {
					System.out.println("Admin " + u.getName() + " has logged in.");
					System.out.println(login.un + " " + login.pw);
					System.out.println(u.getUsername() + " " + u.getPassword());
					if(u.getBcAdmin() || u.getDhAdmin() || u.getDsAdmin()) {
						session.setAttribute("currentUser", u);
					
						response.getWriter().append("index.html");
					}
					else {
						System.out.println("not admin");
						response.getWriter().append("index.html");
					}
				}
				else {
					System.out.println("Failed admin login attempt.");
				}
				break;
			}
			default: {
				System.out.println("Default POST Case.");
				response.sendError(418, "BRB Making tea.");
				break;
			}
		}
	}
}

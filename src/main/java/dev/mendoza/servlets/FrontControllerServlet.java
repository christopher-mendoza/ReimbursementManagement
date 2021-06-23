package dev.mendoza.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dev.mendoza.models.Reimbursement;
import dev.mendoza.models.User;
import dev.mendoza.services.ReimbursementServiceImpl;
import dev.mendoza.services.UserServiceImpl;

public class FrontControllerServlet extends HttpServlet {
	class LoginAttempt {
		public String un;
		public String pw;
	}
	
	class ReimbursementData {
		public User user;
		public List<Reimbursement> list;

	}
	

	private Gson gson = new Gson();
	public static HttpSession session;
	ReimbursementData rd = new ReimbursementData();
	static User u;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "application/json");	
		session = request.getSession();
		switch(uri) {
			// Admin Main
			case "/ReimbursementManagement/adminmain": {
				System.out.println("Inside Admin Main.");
				ReimbursementServiceImpl rs = new ReimbursementServiceImpl();
				List<Reimbursement> l = rs.getAllReimbursements();
				// Benefits Coordinator List
				if(u.getBcAdmin() == true) {
					for(Reimbursement r : l) {
						if((r.getBcApproval().getApprove() == true)) {
							l.remove(r);
						}
					}
				}
				
				// Department Head List
				else if(u.getDhAdmin() == true) {
					for(Reimbursement r : l) {
						if((!r.getDhApproval().getName().equals(u.getUsername())) 
								|| (r.getDhApproval().getApprove() == true)) {
							l.remove(r);
						}
					}
				}
				
				// Direct Supervisor List
				else if(u.getDsAdmin() == true) {
					for(Reimbursement r : l) {
						if((!r.getBcApproval().getName().equals(u.getUsername())) 
								|| (r.getDsApproval().getApprove() == true)) {
							l.remove(r);
						}
					}
				}
				rd.list = l;
				rd.user = u;
				response.getWriter().append(gson.toJson(rd));
				break;
			}
//			case "/ReimbursementManagement/home": {
//				response.getWriter().append("login.html");
//				break;
//			}
//			case "/ReimbursementManagement/invalidate": {
//				session.invalidate();
//				break;
//			}
//			case "/ReimbursementManagement/id": {
//				response.getWriter().append("\n" + session.getId());
//				break;
//			}
//			case "/ReimbursementManagement": {
//				
//				break;
//			}
//			case "/ReimbursementManagement/adminlogin": {
//				System.out.println("admin login");
//				LoginAttempt login = this.gson.fromJson(request.getReader(), LoginAttempt.class);
//				UserServiceImpl us = new UserServiceImpl();
//				User u = us.getUserByUsername(login.un);
//				if(u != null) {
//					System.out.println("Admin " + u.getName() + " has logged in.");
//					session.setAttribute("currentUser", u);
//					response.getWriter().append("index.html");
//				}
//				else {
//					System.out.println("Failed admin login attempt.");
//				}
//				break;
//			}
			
			// User Main
			case "/ReimbursementManagement/usermain": {
				System.out.println("Inside User Main.");
				ReimbursementServiceImpl rs = new ReimbursementServiceImpl();
				List<Reimbursement> l = rs.getAllReimbursements();
				for(Reimbursement r : l) {
					if(!r.getUsername().equals(u.getUsername())) {
						l.remove(r);
					}
				}
				rd.list = l;
				rd.user = u;
				response.getWriter().append(gson.toJson(rd));
				break;
			}
			
			// Add Reimbursements
			case "/ReimbursementManagement/addreimbursements": {
				System.out.println("Inside User Add Reimbursements.");
				response.getWriter().append("addreimbursements.html");
				break;
			}
			
			
			// Cancel Reimbursement
			case "/ReimbursementManagement/cancelapp": {
				response.getWriter().append("user.html");
				break;
			}
			
			// Login
			case "/ReimbursementManagement/login": {
				System.out.println("Got login");

				
				LoginAttempt login = gson.fromJson(request.getReader(), LoginAttempt.class);
				UserServiceImpl us = new UserServiceImpl();
				u = us.getUserByUsername(login.un);
				if(u == null) {
					System.out.println("Failed admin login attempt.");
					break;
				}
				if(u.getUsername().equals(login.un) && u.getPassword().equals(login.pw)) {
					System.out.println("User, " + u.getName() + " has logged in.");
					if(u.getBcAdmin() || u.getDhAdmin() || u.getDsAdmin()) {
						
						session.setAttribute("current_user", u);
						System.out.println("Going to Admin page.");
						response.getWriter().append("admin.html");
					}
					else {
						session.setAttribute("current_user", u);
						System.out.println("Going to User page.");
						response.getWriter().append("user.html");
					}
				}
				else {
					System.out.println("Failed admin login attempt.");
					session.invalidate();
				}
				break;
			}
			
			// Logging Out
			case "/ReimbursementManagement/logout": {
				System.out.println("Logging out.");
				session.invalidate();
				u = null;
				response.getWriter().append("login.html");
				
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
//		String uri = request.getRequestURI();
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Content-Type", "application/json");
//		session = request.getSession();
//		System.out.println("dopost: " + session.getId());
//		switch(uri) {
//			// Logging In
//			case "/ReimbursementManagement/login": {
//				System.out.println("Got login");
//
//				
//				LoginAttempt login = gson.fromJson(request.getReader(), LoginAttempt.class);
//				UserServiceImpl us = new UserServiceImpl();
//				User u = us.getUserByUsername(login.un);
//				if(u == null) {
//					System.out.println("Failed admin login attempt.");
//					break;
//				}
//				if(u.getUsername().equals(login.un) && u.getPassword().equals(login.pw)) {
//					System.out.println("User, " + u.getName() + " has logged in.");
//					if(u.getBcAdmin() || u.getDhAdmin() || u.getDsAdmin()) {
//						
//						session.setAttribute("current_user", u);
//						System.out.println("Going to Admin page.");
//						System.out.println(session.getId());
//						response.getWriter().append("admin.html");
//					}
//					else {
//						session.setAttribute("current_user", u);
//						System.out.println("Going to User page.");
//						response.getWriter().append("user.html");
//					}
//				}
//				else {
//					System.out.println("Failed admin login attempt.");
//					session.invalidate();
//				}
//				break;
//			}
//			
//			// Logging Out
//			case "/ReimbursementManagement/logout": {
//				System.out.println("Logging out.");
//				session.invalidate();
//				response.getWriter().append("login.html");
//				
//				break;
//			}
//			
//			// Default Case
//			default: {
//				System.out.println("Default POST Case.");
//				response.sendError(418, "BRB Making tea.");
//				break;
//			}
//		}
	}
}

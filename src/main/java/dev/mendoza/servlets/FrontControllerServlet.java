package dev.mendoza.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.mendoza.models.BCApproval;
import dev.mendoza.models.DHApproval;
import dev.mendoza.models.DSApproval;
import dev.mendoza.models.Event;
import dev.mendoza.models.EventType;
import dev.mendoza.models.GradeUpload;
import dev.mendoza.models.GradingFormat;
import dev.mendoza.models.PresentationUpload;
import dev.mendoza.models.Reimbursement;
import dev.mendoza.models.User;
import dev.mendoza.services.BCApprovalServiceImpl;
import dev.mendoza.services.DHApprovalServiceImpl;
import dev.mendoza.services.DSApprovalServiceImpl;
import dev.mendoza.services.EventServiceImpl;
import dev.mendoza.services.GradeUploadServiceImpl;
import dev.mendoza.services.GradingFormatServiceImpl;
import dev.mendoza.services.PresentationUploadServiceImpl;
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
	
	class ReimbursementApplication {
		public Date date;
		public String location;
		public String description;
		public float cost;
		public float missedWork;
		public String gradeFormat;
		public String eventType;
		public String workJust;
	}

	Gson gson=  new GsonBuilder().setDateFormat("MM-dd-yyyy").create();
	
	public static HttpSession session;
	ReimbursementData rd = new ReimbursementData();
	static User u;
	static List<Reimbursement> l;
	
	ReimbursementServiceImpl rs = new ReimbursementServiceImpl();
	EventServiceImpl es = new EventServiceImpl();
	UserServiceImpl us = new UserServiceImpl();
	GradingFormatServiceImpl gfs = new GradingFormatServiceImpl();
	DSApprovalServiceImpl dss = new DSApprovalServiceImpl();
	DHApprovalServiceImpl dhs = new DHApprovalServiceImpl();
	BCApprovalServiceImpl bcs = new BCApprovalServiceImpl();
	GradeUploadServiceImpl gus = new GradeUploadServiceImpl();
	PresentationUploadServiceImpl pus = new PresentationUploadServiceImpl();
	
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
				l = rs.getAllReimbursements();
				// Benefits Coordinator List
				if(u.getBcAdmin() == true) {
					for(int i = 0; i < l.size(); i++) {
						if((l.get(i).getBcApproval().getApprove() == true)) {
							l.remove(i);
							i = 0;
						}
					}
				}
				
				// Department Head List
				else if(u.getDhAdmin() == true) {
					for(int i = 0; i < l.size(); i++) {
						if((!l.get(i).getDhApproval().getName().equals(u.getUsername())) 
								|| (l.get(i).getDhApproval().getApprove() == true)) {
							l.remove(i);
							i = 0;
						}
					}
				}
				
				// Direct Supervisor List
				else if(u.getDsAdmin() == true) {
					for(int i = 0; i < l.size(); i++) {
						if((!l.get(i).getDsApproval().getName().equals(u.getUsername())) 
								|| (l.get(i).getDsApproval().getApprove() == true)) {
							l.remove(i);
							i = 0;
						}
					}
				}
				rd.list = l;
				rd.user = u;
				response.getWriter().append(gson.toJson(rd));
				break;
			}
			
			// Direct Supervisor Approve Page
			case "/ReimbursementManagement/dsapprove": {
				System.out.println("Inside Direct Supervisor Approve Page.");
				response.getWriter().append("dsapprove.html");
				break;
			}
			
			// Direct Supervisor List
			case "/ReimbursementManagement/dslist": {
				System.out.println("Getting Direct Supervisor List.");
				response.getWriter().append(gson.toJson(rd));
				break;
			}
			
			// User Main
			case "/ReimbursementManagement/usermain": {
				System.out.println("Inside User Main.");
				l = rs.getAllReimbursements();
				for(int i = 0; i < l.size(); i++) {
					if(!l.get(i).getUsername().equals(u.getUsername())) {
						l.remove(i);
						i = 0;
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
			
			// Submit Reimbursements
			case "/ReimbursementManagement/submitreimbursement": {
				System.out.println("Submitting Reimbursement");

				ReimbursementApplication ra = gson.fromJson(request.getReader(), ReimbursementApplication.class);
				String uSuper = u.getDepartment().getSupervisor();
				String uHead = us.getUserByUsername(uSuper).getDepartment().getSupervisor();
				String uBenco = us.getUserByUsername(uHead).getDepartment().getSupervisor();
				// Create Reimbursement
				Reimbursement r = new Reimbursement();
				r.setName(u.getName());
				r.setUsername(u.getUsername());
				r.setWorkJust(ra.workJust);
				r.setMissedWork(ra.missedWork);
				// Create Event
				Event e = new Event();
				e.setEventDate(ra.date);
				e.setEventLocation(ra.location);
				e.setEventDesc(ra.description);
				e.setEventCost(ra.cost);
				// Create EventType
				EventType et = es.getEventTypeByEventType(ra.eventType);
				e.setEventType(et);
				es.addEvent(e);
				List<Event> eList = es.getAllEvents();
				e = es.getLatestEvent(eList);
				r.setEvent(e);
				// Create GradingFormat
				GradingFormat gf = gfs.getGradingFortmatByFormat(ra.gradeFormat);
				r.setGradingFormat(gf);
				// Add Direct Supervisor Approval
				DSApproval ds = new DSApproval();
				ds.setName(uSuper);
				ds.setApprove(false);
				dss.addDSApproval(ds);
				List<DSApproval> dsList = dss.getAllDSApprovals();
				ds = dss.getLatestDSApproval(dsList);
				r.setDsApproval(ds);
				// Add Department Head Approval
				DHApproval dh = new DHApproval();
				dh.setName(uHead);
				dh.setApprove(false);
				dhs.addDHApproval(dh);
				List<DHApproval> dhList = dhs.getAllDHApprovals();
				dh = dhs.getLatestDHApproval(dhList);
				r.setDhApproval(dh);
				// Add Benefits Coordinator Approval
				BCApproval bc = new BCApproval();
				bc.setName(uBenco);
				bcs.addBCApproval(bc);
				List<BCApproval> bcList = bcs.getAllBCApprovals();
				bc = bcs.getLatestBCApproval(bcList);
				r.setBcApproval(bc);
				// Add Grade Upload
				GradeUpload gu = new GradeUpload();
				gu.setGradeFormat(ra.gradeFormat);
				gu.setGradeUp("");
				gus.addGradeUpload(gu);
				List<GradeUpload> guList = gus.getAllGradeUploads();
				gu = gus.getLatestGradeUpload(guList);
				r.setgUp(gu);
				// Add Presentation Upload
				PresentationUpload pu = new PresentationUpload();
				pu.setPresUp("".getBytes());
				pus.addPresUpload(pu);
				List<PresentationUpload> puList = pus.getAllPresUploads();
				pu = pus.getLatestPresUpload(puList);
				r.setpUp(pu);
				rs.addReimbursement(r);
				System.out.println("Added Reimbursement!");
				response.getWriter().append("user.html");
				break;
			}
			
			// Cancel Reimbursement
			case "/ReimbursementManagement/cancel": {
				System.out.println("Cancelled Task.");
				if(u.getBcAdmin() || u.getDhAdmin() || u.getDsAdmin()) {
					response.getWriter().append("admin.html");
				}
				else {
					response.getWriter().append("user.html");
				}
				break;
			}
			
			// Upload Data
			case "/ReimbursementManagement/userupload": {
				System.out.println("Inside User Upload.");
				response.getWriter().append("uploaddata.html");
				break;
			}
			
			// Login
			case "/ReimbursementManagement/login": {
				System.out.println("Got login");
				LoginAttempt login = gson.fromJson(request.getReader(), LoginAttempt.class);
				u = us.getUserByUsername(login.un);
				if(u == null) {
					System.out.println("Failed admin login attempt.");
					break;
				}
				if(u.getUsername().equals(login.un) && u.getPassword().equals(login.pw)) {
					System.out.println("User " + u.getName() + " has logged in.");
					if(u.getBcAdmin() || u.getDhAdmin() || u.getDsAdmin()) {
						
						session.setAttribute("current_user", u);
						response.getWriter().append("admin.html");
					}
					else {
						session.setAttribute("current_user", u);
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
	}
}

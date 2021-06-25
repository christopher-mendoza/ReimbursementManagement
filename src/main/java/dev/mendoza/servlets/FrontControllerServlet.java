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
	
	class DSSubmit {
		public String judgement;
		public String reason;
		public int id;
	}
	
	class DHSubmit {
		public String judgement;
		public String reason;
		public int id;
	}
	
	class UserUpload {
		public String grade;
		public String passfail;
		public int id;
	}
	
	class BCSubmit {
		public String judgement;
		public String reason;
		public float change;
		public int id;
	}
	
	class UserVerdict {
		public String judgement;
		public int id;
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
				u = us.getUserByUsername(u.getUsername());
				l = rs.getAllReimbursements();
				// Benefits Coordinator List
				if(u.getBcAdmin() == true) {
					for(int i = 0; i < l.size(); i++) {
						if((l.get(i).getBcApproval().getApprove() == true)
								|| (l.get(i).getDhApproval().getApprove() == false)
								|| (l.get(i).getDsApproval().getApprove() == false)) {
							l.remove(i);
							i = -1;
						}
					}
				}
				
				// Department Head List
				else if(u.getDhAdmin() == true) {
					for(int i = 0; i < l.size(); i++) {
						if((!l.get(i).getDhApproval().getName().equals(u.getUsername())) 
								|| (l.get(i).getDhApproval().getApprove() == true)
								|| (l.get(i).getDsApproval().getApprove() == false && u.getDsAdmin() == false)) {
							l.remove(i);
							i = -1;
						}
					}
				}
				
				// Direct Supervisor List
				else if(u.getDsAdmin() == true) {
					for(int i = 0; i < l.size(); i++) {
						if((!l.get(i).getDsApproval().getName().equals(u.getUsername())) 
								|| (l.get(i).getDsApproval().getApprove() == true)) {
							l.remove(i);
							i = -1;
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
			
			// Direct Supervisor Submit Judgement
			case "/ReimbursementManagement/dssubmit": {
				System.out.println("Submitting Direct Supervisor Judgement.");
				DSSubmit dsSubmit = gson.fromJson(request.getReader(), DSSubmit.class);
				Reimbursement r = rs.getReimbursementById(dsSubmit.id);
				DSApproval directSuper = dss.getDSApprovalById(r.getDsApproval().getId());
				// Submitted Reject
				if(dsSubmit.judgement.equals("1")) {
					System.out.println("Received Reject for id: " + dsSubmit.id);
					dss.changeDSReason(directSuper, dsSubmit.reason);
				}
				// Submitted Accept
				else {
					System.out.println("Received Accept for id: " + dsSubmit.id);
					dss.changeDSApprove(directSuper);
					dss.changeDSReason(directSuper, "");
				}
				response.getWriter().append("admin.html");
				break;
			}
			
			// Department Head Approve Page
			case "/ReimbursementManagement/dhapprove": {
				System.out.println("Inside Department Head Approve Page.");
				response.getWriter().append("dhapprove.html");
				break;
			}
			
			// Department Head List
			case "/ReimbursementManagement/dhlist": {
				System.out.println("Getting Department Head List.");
				response.getWriter().append(gson.toJson(rd));
				break;
			}
			
			// Department Head Submit Judgement
			case "/ReimbursementManagement/dhsubmit": {
				System.out.println("Submitting Department Head Judgement.");
				DHSubmit dhSubmit = gson.fromJson(request.getReader(), DHSubmit.class);
				Reimbursement r = rs.getReimbursementById(dhSubmit.id);
				DHApproval departHead = dhs.getDHApprovalById(r.getDhApproval().getId());
				// Submitted Reject
				if(dhSubmit.judgement.equals("1")) {
					System.out.println("Received Reject for id: " + dhSubmit.id);
					dhs.changeDHReason(departHead, dhSubmit.reason);
				}
				// Submitted Accept
				else {
					System.out.println("Received Accept for id: " + dhSubmit.id);
					if(u.getDsAdmin() == true)
					{
						DSApproval directSuper = dss.getDSApprovalById(r.getDsApproval().getId());
						dss.changeDSApprove(directSuper);
					}
					dhs.changeDHApprove(departHead);
					dhs.changeDHReason(departHead, "");
				}
				response.getWriter().append("admin.html");
				break;
			}
			
			// Benefits Coordinate Approve Page
			case "/ReimbursementManagement/bcapprove": {
				System.out.println("Inside Benefits Coordinator Approve Page.");
				response.getWriter().append("bcapprove.html");
				break;
			}
			
			// Benefits Coordinator List
			case "/ReimbursementManagement/bclist": {
				System.out.println("Getting Benefits Coordinator List.");
				response.getWriter().append(gson.toJson(rd));
				break;
			}
			
			// Benefits Coordinator Submit Judgement
			case "/ReimbursementManagement/bcsubmit": {
				System.out.println("Submitting Benefits Coordinator Judgement.");
				BCSubmit bcSubmit = gson.fromJson(request.getReader(), BCSubmit.class);
				Reimbursement r = rs.getReimbursementById(bcSubmit.id);
				BCApproval benCo = bcs.getBCApprovalById(r.getBcApproval().getId());
				// Submitted Reject
				if(bcSubmit.judgement.equals("1")) {
					System.out.println("Received Reject for id: " + bcSubmit.id);
					bcs.changeBCReason(benCo, bcSubmit.reason);
				}
				// Submitted Accept
				else {
					System.out.println("Received Accept for id: " + bcSubmit.id);
					User employee = us.getUserByUsername(r.getUsername());
					if((employee.getReAmount() + bcSubmit.change) > 1000.00f) {
						bcs.changeBCExceed(benCo);
					}
					bcs.changeBCReason(benCo, bcSubmit.reason);
					es.changeProposedAmt(r.getEvent(), bcSubmit.change);
					bcs.changeBCApproval(benCo);
				}
				response.getWriter().append("admin.html");
				break;
			}
			
			// User Main
			case "/ReimbursementManagement/usermain": {
				System.out.println("Inside User Main.");
				u = us.getUserByUsername(u.getUsername());
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
				r.setFullApprove(false);
				// Create Event
				Event e = new Event();
				e.setEventDate(ra.date);
				e.setEventLocation(ra.location);
				e.setEventDesc(ra.description);
				e.setEventCost(ra.cost);
				// Create EventType
				EventType et = es.getEventTypeByEventType(ra.eventType);
				e.setEventProposed(ra.cost * et.getCoverage());
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
			
			// User List
			case "/ReimbursementManagement/userlist": {
				System.out.println("Getting User List.");
				response.getWriter().append(gson.toJson(rd));
				break;
			}
			
			// User Grade/Pres Submit
			case "/ReimbursementManagement/usersubmit": {
				System.out.println("Submitting User Upload.");
				UserUpload uUpload = gson.fromJson(request.getReader(), UserUpload.class);
				Reimbursement r = rs.getReimbursementById(uUpload.id);
				GradeUpload gu = gus.getGradeUploadById(r.getgUp().getId());
				if(r.getGradingFormat().getgFormatName().equals("Letter Grading")) {
					switch(uUpload.grade) {
						// A
						case "0": {
							gus.changeGradeUpload(gu, "A");
							break;
						}
						// B
						case "1": {
							gus.changeGradeUpload(gu, "B");
							break;
						}
						// C
						case "2": {
							gus.changeGradeUpload(gu, "C");
							break;
						}
						// D
						case "3": {
							gus.changeGradeUpload(gu, "D");
							break;
						}
						// F
						case "4": {
							gus.changeGradeUpload(gu, "F");
							break;
						}
					}
				}
				else if(r.getGradingFormat().getgFormatName().equals("Pass/Fail")) {
					switch(uUpload.passfail) {
						// Pass
						case "0": {
							gus.changeGradeUpload(gu, "Pass");
							break;
						}
						// Fail
						case "1": {
							gus.changeGradeUpload(gu, "Fail");
							break;
						}
					}
				}
				response.getWriter().append("user.html");
				break;
			}
			
			// User Cancel/Accept Reimburses
			case "/ReimbursementManagement/userjudgement": {
				System.out.println("Inside User Judgement.");
				response.getWriter().append("userjudgement.html");
				break;
			}
			
			// User Reimbursement Verdict
			case "/ReimbursementManagement/userverdict": {
				System.out.println("Submitting User Verdict.");
				UserVerdict userV = gson.fromJson(request.getReader(), UserVerdict.class);
				Reimbursement r = rs.getReimbursementById(userV.id);
				// Cancel
				if(userV.judgement.equals("1")) {
					System.out.print("Removing reimbursement: " + userV.id);
					rs.removeReimbursement(r);
				}
				// Confirm!
				else {
					us.changeReimbursementAmt(u, u.getReAmount() + es.getEventById(r.getEvent().getId()).getEventProposed());
					rs.changeFullApprove(r);
				}
				response.getWriter().append("user.html");
				break;
			}
			
			// Login
			case "/ReimbursementManagement/login": {
				System.out.println("Got login");
				LoginAttempt login = gson.fromJson(request.getReader(), LoginAttempt.class);
				u = us.getUserByUsername(login.un);
				if(u == null) {
					System.out.println("Failed login attempt.");
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
					System.out.println("Failed login attempt.");
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

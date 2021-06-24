package dev.mendoza.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.mendoza.models.BCApproval;
import dev.mendoza.models.DHApproval;
import dev.mendoza.models.DSApproval;
import dev.mendoza.models.Department;
import dev.mendoza.models.Event;
import dev.mendoza.models.EventType;
import dev.mendoza.models.GradeUpload;
import dev.mendoza.models.GradingFormat;
import dev.mendoza.models.PresentationUpload;
import dev.mendoza.models.Reimbursement;
import dev.mendoza.models.User;
import dev.mendoza.utils.JDBCConnection;

/*
public class Reimbursement {
	private Integer id;
	private String name;
	private String username;
	private Event event;
	private String workJust;
	private GradingFormat gradingFormat;
	private DSApproval dsApproval;
	private DHApproval dhApproval;
	private BCApproval bcApproval;
	private Boolean fullApprove;
	private Float missedWork;
	private GradeUpload gUp;
	private PresentationUpload pUp;
	
 */

public class ReimbursementDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public boolean addReimbursement(Reimbursement r) {
		String sql = "INSERT INTO reimbursements VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, false, ?, ?, ?) RETURNING *;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, r.getName());
			cs.setString(2, r.getUsername());
			cs.setInt(3, r.getEvent().getId());
			cs.setString(4, r.getWorkJust());
			cs.setInt(5, r.getGradingFormat().getId());
			cs.setInt(6, r.getDsApproval().getId());
			cs.setInt(7, r.getDhApproval().getId());
			cs.setInt(8, r.getBcApproval().getId());
			cs.setFloat(9, r.getMissedWork());
			cs.setInt(10, r.getgUp().getId());
			cs.setInt(11, r.getpUp().getId());
			cs.execute();
			cs.close();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Reimbursement getReimbursementById(Integer id) {
		String sql = "SELECT * FROM reimbursements " +
					 "JOIN events ON r_event = event_id " +
					 "JOIN event_types ON e_type = event_type_id " +
					 "JOIN gradeformats ON gradeformat_id = r_grade_format " +
					 "JOIN ds_approvals ON ds_approval_id = ds_approval " +
					 "JOIN dh_approvals ON dh_approval_id = dh_approval " +
					 "JOIN bc_approvals ON bc_approval_id = bc_approval " +
					 "JOIN grade_uploads ON grade_upload_id = g_upload " +
					 "JOIN presentation_uploads ON presentation_upload_id = p_upload " +
					 "WHERE reimbursement_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			// Checks if ResultSet returns nothing (bad input)
			if(!rs.isBeforeFirst()) {
				return null;
			}
			if(rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setId(rs.getInt("reimbursement_id"));
				r.setName(rs.getString("r_name"));
				r.setUsername(rs.getString("r_username"));
				r.setWorkJust(rs.getString("work_justification"));
				r.setFullApprove(rs.getBoolean("full_approve"));
				r.setMissedWork(rs.getFloat("missed_work"));
				// Add Event
				Event e = new Event();
				e.setId(rs.getInt("event_id"));
				e.setEventDate(rs.getDate("event_date"));
				e.setEventLocation(rs.getString("event_location"));
				e.setEventDesc(rs.getString("event_description"));
				e.setEventCost(rs.getFloat("event_cost"));
				// Add EventType into Event
				EventType eType = new EventType();
				eType.setId(rs.getInt("event_type_id"));
				eType.setType(rs.getString("event_type"));
				eType.setCoverage(rs.getFloat("event_coverage"));
				e.setEventType(eType);
				r.setEvent(e);
				// Add Grading Format
				GradingFormat gf = new GradingFormat();
				gf.setId(rs.getInt("gradeformat_id"));
				gf.setgFormatName(rs.getString("g_grade_format"));
				gf.setGrade(rs.getString("grade"));
				gf.setPass(rs.getBoolean("pass"));
				r.setGradingFormat(gf);
				// Add DS Approval
				DSApproval ds = new DSApproval();
				ds.setId(rs.getInt("ds_approval_id"));
				ds.setName(rs.getString("ds_name"));
				ds.setReason(rs.getString("ds_reason"));
				ds.setApprove(rs.getBoolean("ds_approved"));
				r.setDsApproval(ds);
				// Add DH Approval
				DHApproval dh = new DHApproval();
				dh.setId(rs.getInt("dh_approval_id"));
				dh.setName(rs.getString("dh_name"));
				dh.setReason(rs.getString("dh_reason"));
				dh.setApprove(rs.getBoolean("dh_approved"));
				r.setDhApproval(dh);
				// Add BC Approval
				BCApproval bc = new BCApproval();
				bc.setId(rs.getInt("bc_approval_id"));
				bc.setName(rs.getString("bc_name"));
				bc.setReason(rs.getString("bc_reason"));
				bc.setApprove(rs.getBoolean("bc_approved"));
				bc.setExceed(rs.getBoolean("bc_exceed"));
				r.setBcApproval(bc);
				// Add Grade Upload
				GradeUpload g = new GradeUpload();
				g.setId(rs.getInt("grade_upload_id"));
				g.setGradeFormat(rs.getString("grade_upload_format"));
				g.setGradeUp(rs.getString("grade_up"));
				r.setgUp(g);
				// Add Presentation Upload
				PresentationUpload p = new PresentationUpload();
				p.setId(rs.getInt("presentation_upload_id"));
				// Fix this to whatever presUp ends up being
				p.setPresUp(rs.getBytes("presentation_up"));
				r.setpUp(p);
				return r;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Reimbursement> getAllReimbursements() {
		String sql = "SELECT * FROM reimbursements " +
				 "JOIN events ON r_event = event_id " +
				 "JOIN event_types ON e_type = event_type_id " +
				 "JOIN gradeformats ON gradeformat_id = r_grade_format " +
				 "JOIN ds_approvals ON ds_approval_id = ds_approval " +
				 "JOIN dh_approvals ON dh_approval_id = dh_approval " +
				 "JOIN bc_approvals ON bc_approval_id = bc_approval " +
				 "JOIN grade_uploads ON grade_upload_id = g_upload " +
				 "JOIN presentation_uploads ON presentation_upload_id = p_upload ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
			while(rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setId(rs.getInt("reimbursement_id"));
				r.setName(rs.getString("r_name"));
				r.setUsername(rs.getString("r_username"));
				r.setWorkJust(rs.getString("work_justification"));
				r.setFullApprove(rs.getBoolean("full_approve"));
				r.setMissedWork(rs.getFloat("missed_work"));
				// Add Event
				Event e = new Event();
				e.setId(rs.getInt("event_id"));
				e.setEventDate(rs.getDate("event_date"));
				e.setEventLocation(rs.getString("event_location"));
				e.setEventDesc(rs.getString("event_description"));
				e.setEventCost(rs.getFloat("event_cost"));
				// Add EventType into Event
				EventType eType = new EventType();
				eType.setId(rs.getInt("event_type_id"));
				eType.setType(rs.getString("event_type"));
				eType.setCoverage(rs.getFloat("event_coverage"));
				e.setEventType(eType);
				r.setEvent(e);
				// Add Grading Format
				GradingFormat gf = new GradingFormat();
				gf.setId(rs.getInt("gradeformat_id"));
				gf.setgFormatName(rs.getString("g_grade_format"));
				gf.setGrade(rs.getString("grade"));
				gf.setPass(rs.getBoolean("pass"));
				r.setGradingFormat(gf);
				// Add DS Approval
				DSApproval ds = new DSApproval();
				ds.setId(rs.getInt("ds_approval_id"));
				ds.setName(rs.getString("ds_name"));
				ds.setReason(rs.getString("ds_reason"));
				ds.setApprove(rs.getBoolean("ds_approved"));
				r.setDsApproval(ds);
				// Add DH Approval
				DHApproval dh = new DHApproval();
				dh.setId(rs.getInt("dh_approval_id"));
				dh.setName(rs.getString("dh_name"));
				dh.setReason(rs.getString("dh_reason"));
				dh.setApprove(rs.getBoolean("dh_approved"));
				r.setDhApproval(dh);
				// Add BC Approval
				BCApproval bc = new BCApproval();
				bc.setId(rs.getInt("bc_approval_id"));
				bc.setName(rs.getString("bc_name"));
				bc.setReason(rs.getString("bc_reason"));
				bc.setApprove(rs.getBoolean("bc_approved"));
				bc.setExceed(rs.getBoolean("bc_exceed"));
				r.setBcApproval(bc);
				// Add Grade Upload
				GradeUpload g = new GradeUpload();
				g.setId(rs.getInt("grade_upload_id"));
				g.setGradeFormat(rs.getString("grade_upload_format"));
				g.setGradeUp(rs.getString("grade_up"));
				r.setgUp(g);
				// Add Presentation Upload
				PresentationUpload p = new PresentationUpload();
				p.setId(rs.getInt("presentation_upload_id"));
				// Fix this to whatever presUp ends up being
				p.setPresUp(rs.getBytes("presentation_up"));
				r.setpUp(p);
				reimbursements.add(r);
			}
			return reimbursements;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package dev.mendoza.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.mendoza.models.DHApproval;
import dev.mendoza.utils.JDBCConnection;

/*
public class DHApproval {
	private Integer id;
	private String name;
	private String reason;
	private Boolean approve;
 */

public class DHApprovalDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public boolean addDHApproval(DHApproval approve) {
		String sql = "INSERT INTO dh_approvals VALUES (default, ?, '', ?) RETURNING *;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, approve.getName());
			cs.setBoolean(2, approve.getApprove());
			cs.execute();
			cs.close();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public DHApproval getDHApprovalById(Integer id) {
		String sql = "SELECT * FROM dh_approvals " +
					 "WHERE dh_approval_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			// Checks if ResultSet returns nothing (bad input)
			if(!rs.isBeforeFirst()) {
				return null;
			}
			if(rs.next()) {
				DHApproval a = new DHApproval();
				a.setId(rs.getInt("dh_approval_id"));
				a.setName(rs.getString("dh_name"));
				a.setReason(rs.getString("dh_reason"));
				a.setApprove(rs.getBoolean("dh_approved"));
				return a;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DHApproval> getAllDHApprovals() {
		String sql = "SELECT * FROM dh_approvals;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<DHApproval> dhApprovals = new ArrayList<DHApproval>();
			while(rs.next()) {
				DHApproval a = new DHApproval();
				a.setId(rs.getInt("dh_approval_id"));
				a.setName(rs.getString("dh_name"));
				a.setReason(rs.getString("dh_reason"));
				a.setApprove(rs.getBoolean("dh_approved"));
				dhApprovals.add(a);
			}
			return dhApprovals;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean changeDHApprove(DHApproval a) {
		String sql = "UPDATE dh_approvals SET dh_approved = true " +
					 "WHERE dh_approval_id = ?;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, a.getId());
			cs.execute();
			cs.close();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean changeDHReason(DHApproval a, String reason) {
		String sql = "UPDATE dh_approvals SET dh_reason = ? " +
					 "WHERE dh_approval_id = ?;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, reason);
			cs.setInt(2, a.getId());
			cs.execute();
			cs.close();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

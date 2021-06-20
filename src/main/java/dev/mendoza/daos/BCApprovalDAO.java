package dev.mendoza.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.mendoza.models.BCApproval;
import dev.mendoza.utils.JDBCConnection;

/*
public class BCApproval {
	private Integer id;
	private String name;
	private String reason;
	private Boolean approve;
	private Boolean exceed;
 */

public class BCApprovalDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public boolean addBCApproval(BCApproval approve) {
		String sql = "INSERT INTO bc_approvals VALUES (default, ?, '') RETURNING *;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, approve.getName());
			cs.execute();
			cs.close();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public BCApproval getBCApprovalById(Integer id) {
		String sql = "SELECT * FROM bc_approvals " +
					 "WHERE bc_approval_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			// Checks if ResultSet returns nothing (bad input)
			if(!rs.isBeforeFirst()) {
				return null;
			}
			if(rs.next()) {
				BCApproval a = new BCApproval();
				a.setId(rs.getInt("bc_approval_id"));
				a.setName(rs.getString("bc_name"));
				a.setReason(rs.getString("bc_reason"));
				a.setApprove(rs.getBoolean("bc_approved"));
				a.setExceed(rs.getBoolean("bc_exceed"));
				return a;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean changeBCApprove(BCApproval a) {
		String sql = "UPDATE bc_approvals SET bc_approved = true " +
					 "WHERE bc_approval_id = ?;";
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
	
	public boolean changeBCReason(BCApproval a, String reason) {
		String sql = "UPDATE bc_approvals SET bc_reason = ? " +
					 "WHERE bc_approval_id = ?;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, reason);
			cs.setInt(2, a.getId());
			cs.execute();
			cs.close();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean changeBCExceed(BCApproval a) {
		String sql = "UPDATE bc_approvals SET bc_exceed = true " + 
					 "WHERE bc_approval_id = ?;";
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
}

package dev.mendoza.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.mendoza.models.DSApproval;
import dev.mendoza.utils.JDBCConnection;

/*
public class DSApproval {
	private Integer id;
	private String name;
	private String reason;
	private Boolean approve;
 */

public class DSApprovalDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public boolean addDSApproval(DSApproval approve) {
		String sql = "INSERT INTO ds_approvals VALUES (default, ?, '', ?) RETURNING *;";
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

	public DSApproval getDSApprovalById(Integer id) {
		String sql = "SELECT * FROM ds_approvals " +
					 "WHERE ds_approval_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			// Checks if ResultSet returns nothing (bad input)
			if(!rs.isBeforeFirst()) {
				return null;
			}
			if(rs.next()) {
				DSApproval a = new DSApproval();
				a.setId(rs.getInt("ds_approval_id"));
				a.setName(rs.getString("ds_name"));
				a.setReason(rs.getString("ds_reason"));
				a.setApprove(rs.getBoolean("ds_approved"));
				return a;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DSApproval> getAllDSApprovals() {
		String sql = "SELECT * FROM ds_approvals;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<DSApproval> dsApprovals = new ArrayList<DSApproval>();
			while(rs.next()) {
				DSApproval ds = new DSApproval();
				ds.setId(rs.getInt("ds_approval_id"));
				ds.setName(rs.getString("ds_name"));
				ds.setReason(rs.getString("ds_reason"));
				ds.setApprove(rs.getBoolean("ds_approved"));
				dsApprovals.add(ds);
			}
			return dsApprovals;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean changeDSApprove(DSApproval a) {
		String sql = "UPDATE ds_approvals SET ds_approved = true " +
					 "WHERE ds_approval_id = ?;";
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
	
	public boolean changeDSReason(DSApproval a, String reason) {
		String sql = "UPDATE ds_approvals SET ds_reason = ? " +
					 "WHERE ds_approval_id = ?;";
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
}

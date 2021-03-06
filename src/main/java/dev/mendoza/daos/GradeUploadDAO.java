package dev.mendoza.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.mendoza.models.GradeUpload;
import dev.mendoza.utils.JDBCConnection;

/*
public class GradeUpload {
	private Integer id;
	private String gradeFormat;
	private String gradeUp;
 */

public class GradeUploadDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public boolean addGradeUpload(GradeUpload g) {
		String sql = "INSERT INTO grade_uploads VALUES (default, ?, ?) RETURNING *;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, g.getGradeFormat());
			cs.setString(2, g.getGradeUp());
			cs.execute();
			cs.close();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public GradeUpload getGradeUploadById(Integer id) {
		String sql = "SELECT * FROM grade_uploads WHERE grade_upload_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			// Checks if ResultSet returns nothing (bad input) 
			if(!rs.isBeforeFirst()) {
				return null;
			}
			if(rs.next()) {
				GradeUpload g = new GradeUpload();
				g.setId(rs.getInt("grade_upload_id"));
				g.setGradeFormat(rs.getString("grade_upload_format"));
				g.setGradeUp(rs.getString("grade_up"));
				return g;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<GradeUpload> getAllGradeUploads() {
		String sql = "SELECT * FROM grade_uploads;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<GradeUpload> gradeUploads = new ArrayList<GradeUpload>();
			while(rs.next()) {
				GradeUpload g = new GradeUpload();
				g.setId(rs.getInt("grade_upload_id"));
				g.setGradeFormat(rs.getString("grade_upload_format"));
				g.setGradeUp(rs.getString("grade_up"));
				gradeUploads.add(g);
			}
			return gradeUploads;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean changeGradeFormat(GradeUpload g, String format) {
		String sql = "UPDATE grade_uploads SET grade_upload_format = ? " +
					 "WHERE grade_upload_id = ?;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, format);
			cs.setInt(2, g.getId());
			cs.execute();
			cs.close();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean changeGradeUpload(GradeUpload g, String grade) {
		String sql = "UPDATE grade_uploads SET grade_up = ? " + 
					 "WHERE grade_upload_id = ?;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, grade);
			cs.setInt(2, g.getId());
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

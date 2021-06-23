package dev.mendoza.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.mendoza.models.GradingFormat;
import dev.mendoza.utils.JDBCConnection;

/*
public class GradingFormat {
	private Integer id;
	private String gFormatName;
	private String grade;
	private Boolean pass;
 */

public class GradingFormatDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public GradingFormat getGradingFormatById(Integer id) {
		String sql = "SELECT * FROM gradeformats WHERE gradeformat_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			// Checks if ResultSet returns nothing (bad input)
			if(!rs.isBeforeFirst()) {
				return null;
			}
			if(rs.next()) {
				GradingFormat gf = new GradingFormat();
				gf.setId(rs.getInt("gradeformat_id"));
				gf.setgFormatName(rs.getString("g_grade_format"));
				gf.setGrade(rs.getString("grade"));
				gf.setPass(rs.getBoolean("pass"));
				return gf;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public GradingFormat getGradingFormatByFormat(String format) {
		String sql = "SELECT * FROM gradeformats WHERE g_grade_format = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, format);
			ResultSet rs = ps.executeQuery();
			// Checks if ResultSet returns nothing (bad input)
			if(!rs.isBeforeFirst()) {
				return null;
			}
			if(rs.next()) {
				GradingFormat gf = new GradingFormat();
				gf.setId(rs.getInt("gradeformat_id"));
				gf.setgFormatName(rs.getString("g_grade_format"));
				gf.setGrade(rs.getString("grade"));
				gf.setPass(rs.getBoolean("pass"));
				return gf;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<GradingFormat> getAllGradingFormats() {
		String sql = "SELECT * FROM gradeformats";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<GradingFormat> formats = new ArrayList<GradingFormat>();
			while(rs.next()) {
				GradingFormat gf = new GradingFormat();
				gf.setId(rs.getInt("gradeformat_id"));
				gf.setgFormatName(rs.getString("g_grade_format"));
				gf.setGrade(rs.getString("grade"));
				gf.setPass(rs.getBoolean("pass"));
				formats.add(gf);
			}
			return formats;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

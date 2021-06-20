package dev.mendoza.daos;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.mendoza.models.PresentationUpload;
import dev.mendoza.utils.JDBCConnection;

/*
public class PresentationUpload {
	private Integer id;
	private Byte[] presUp;
 */

public class PresentationUploadDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	// Fix this to accept byte arrays/blobs/files
	public boolean addPresUpload(PresentationUpload p) {
		String sql = "INSERT INTO presentation_uploads VALUES (default, ?) RETURNING *;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setBytes(1, p.getPresUp());
			cs.execute();
			cs.close();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public PresentationUpload getPresUploadById(Integer id) {
		String sql = "SELECT * FROM presentation_uploads WHERE presentation_upload_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			// Checks if ResultSet returns nothing (bad input)
			if(!rs.isBeforeFirst()) {
				return null;
			}
			if(rs.next()) {
				PresentationUpload p = new PresentationUpload();
				p.setId(rs.getInt("presentation_upload_id"));
				// Fix this to whatever presUp ends up being
				p.setPresUp(rs.getBytes("presentation_up"));
				return p;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package dev.mendoza.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.mendoza.models.Department;
import dev.mendoza.utils.JDBCConnection;

/*
public class Department {
	private Integer id;
	private String depName;
	private String supervisor;
*/

public class DepartmentDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public List<Department> getDepartmentByName(String depName) {
		String sql = "SELECT department_id, d_department, supervisor " +
				 	 "FROM departments WHERE d_department = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, depName);
			ResultSet rs = ps.executeQuery();
			List<Department> department = new ArrayList<Department>();
			// Checks if ResultSet would return nothing (bad input)
			if(!rs.isBeforeFirst()) {
				System.out.println("I could not find the department \"" + depName + "\".");
				return null;
			}
			while(rs.next()) {
				Department d = new Department();
				d.setId(rs.getInt("department_id"));
				d.setDepName(rs.getString("d_department"));
				d.setSupervisor(rs.getString("supervisor"));
				department.add(d);
			}
			return department;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Department> getDepartmentBySupervisor(String supervisor) {
		String sql = "SELECT department_id, d_department, supervisor " +
					 "FROM departments WHERE supervisor = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, supervisor);
			ResultSet rs = ps.executeQuery();
			List<Department> department = new ArrayList<Department>();
			// Checks if ResultSet would return nothing (bad input)
			if(!rs.isBeforeFirst()) {
				System.out.println("I could not find the supervisor \"" + supervisor + "\".");
				return null;
			}
			while(rs.next()) {
				Department d = new Department();
				d.setId(rs.getInt("department_id"));
				d.setDepName(rs.getString("d_department"));
				d.setSupervisor(rs.getString("supervisor"));
				department.add(d);
			}
			return department;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

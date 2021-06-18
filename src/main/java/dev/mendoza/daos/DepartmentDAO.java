package dev.mendoza.daos;

import java.sql.Connection;

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
	
	
	
	public Department getDepartmentById(Integer id) {
		
		
		return null;
	}
	
	public Department getDepartmentByName(String depName) {
		String sql = "SELECT department_id, d_department, supervisor " +
				 	 " FROM departments WHERE d_department = ?";
		return null;
	}
	
	public Department getDepartmentBySupervisor(String supervisor) {
		return null;
	}
	
	
}

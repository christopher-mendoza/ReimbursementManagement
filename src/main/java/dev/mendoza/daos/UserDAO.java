package dev.mendoza.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dev.mendoza.models.Department;
import dev.mendoza.models.User;
import dev.mendoza.utils.JDBCConnection;

/*
public class User {
	private Integer id;
	private Department department;
	private String name;
	private String username;
	private String password;
	private Float reAmount;
	private Timestamp reTimer;
	private Boolean dsAdmin;
	private Boolean dhAdmin;
	private Boolean bcAdmin;
*/

public class UserDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public boolean addUser(User u) {
		String sql = "INSERT INTO users VALUES (default, ?, ?, ?, ?, 0, current_timestamp) RETURNING *;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, u.getDepartment().getId());
			cs.setString(2,  u.getName());
			cs.setString(3,  u.getUsername());
			cs.setString(4,  u.getPassword());
			cs.execute();
			cs.close();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public User getUserByUsername(String username) {
		String sql = "SELECT * FROM users JOIN departments " +
					 "ON u_department = department_id " +
					 "WHERE u_username = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			// Checks if ResultSet returns nothing (bad input)
			if(!rs.isBeforeFirst()) {
				return null;
			}
			if(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("user_id"));
				// Add Department Object
				Department d = new Department();
				d.setId(rs.getInt("department_id"));
				d.setDepName(rs.getString("d_department"));
				d.setSupervisor(rs.getString("supervisor"));
				u.setDepartment(d);
				
				u.setName(rs.getString("u_name"));
				u.setUsername(rs.getString("u_username"));
				u.setPassword(rs.getString("password"));
				u.setReAmount(rs.getFloat("reim_amount"));
				u.setReTimer(rs.getTimestamp("reim_timer"));
				return u;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> getUserByDepartment(String department) {
		String sql = "SELECT * FROM users JOIN departments " + 
					 "ON u_department = department_id " +
					 "WHERE d_department = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, department);
			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("user_id"));
				// Add Department Object
				Department d = new Department();
				d.setId(rs.getInt("department_id"));
				d.setDepName(rs.getString("d_department"));
				d.setSupervisor(rs.getString("supervisor"));
				u.setDepartment(d);
				
				u.setName(rs.getString("u_name"));
				u.setUsername(rs.getString("u_username"));
				u.setPassword(rs.getString("password"));
				u.setReAmount(rs.getFloat("reim_amount"));
				u.setReTimer(rs.getTimestamp("reim_timer"));
				users.add(u);
			}
			return users;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM users JOIN departments " +
					 "ON u_department = department_id;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("user_id"));
				// Add Department Object
				Department d = new Department();
				d.setId(rs.getInt("department_id"));
				d.setDepName(rs.getString("d_department"));
				d.setSupervisor(rs.getString("supervisor"));
				u.setDepartment(d);
				
				u.setName(rs.getString("u_name"));
				u.setUsername(rs.getString("u_username"));
				u.setPassword(rs.getString("password"));
				u.setReAmount(rs.getFloat("reim_amount"));
				u.setReTimer(rs.getTimestamp("reim_timer"));
				users.add(u);
			}
			return users;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean changeReimbursementAmt(User u, float newAmt) {
		String sql = "UPDATE users SET reim_amount = ? " +
					 "WHERE u_username = ?;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setFloat(1, newAmt);
			cs.setString(2,  u.getUsername());
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

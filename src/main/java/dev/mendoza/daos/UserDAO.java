package dev.mendoza.daos;

import java.sql.Connection;
import java.sql.Timestamp;
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
		String sql = "INSERT INTO users VALUES (default, ";
		return false;
	}
	
	public User getUserByUsername(String username) {
		return null;
	}
	
	public User getUserByDepartment(Department department) {
		return null;
	}
	
	public List<User> getAllUsers() {
		return null;
	}
	
	public boolean changeReimbursementAmt(User u, float newAmt) {
		return false;
	}
}

package dev.mendoza.daos;

import java.sql.Connection;
import java.sql.Timestamp;

import dev.mendoza.models.Department;

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
}

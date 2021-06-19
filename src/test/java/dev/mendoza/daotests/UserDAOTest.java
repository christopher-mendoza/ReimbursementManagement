package dev.mendoza.daotests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.daos.UserDAO;
import dev.mendoza.models.Department;
import dev.mendoza.models.User;

public class UserDAOTest {

	private UserDAO udao = new UserDAO();
	
	
	@Test @Ignore
	public void addUserTest() {
		Department d = new Department(1, "testDepartment", "DJ Bop");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		User u = new User(d, "test user", "test", "pass", 123.56f, time);
		assertEquals(true, udao.addUser(u));
	}

	@Test @Ignore
	public void getUserByUsernameTest() {
		System.out.println(udao.getUserByUsername("gold"));
	}
	
	@Test @Ignore
	public void getUserByDepartmentTest() {
		List<User> users = udao.getUserByDepartment("testDepartment");
		if(users != null) {
			for(User u : users) {
				System.out.println(u);
			}
		}
		
	}
	
	@Test @Ignore
	public void getAllUsersTest() {
		List<User> users = udao.getAllUsers();
		if(users != null) {
			for(User u : users) {
				System.out.println(u);
			}
		}
		
	}
	
	@Test
	public void changeReimbursmentAmtTest() {
		User u = new User();
		u.setUsername("gold");
		assertEquals(true, udao.changeReimbursementAmt(u, 420.69f));
		
	}
}

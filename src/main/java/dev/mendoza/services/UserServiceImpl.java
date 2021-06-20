package dev.mendoza.services;

import java.util.List;

import dev.mendoza.daos.UserDAO;
import dev.mendoza.models.User;

public class UserServiceImpl implements UserService {

	private static UserDAO udao = new UserDAO();
	
	@Override
	public boolean addUser(User u) {
		return udao.addUser(u);
	}

	@Override
	public User getUserByUsername(String username) {
		return udao.getUserByUsername(username);
	}

	@Override
	public List<User> getUserByDepartment(String department) {
		return udao.getUserByDepartment(department);
	}

	@Override
	public List<User> getAllUsers() {
		return udao.getAllUsers();
	}

	@Override
	public boolean changeReimbursementAmt(User u, float newAmt) {
		return udao.changeReimbursementAmt(u, newAmt);
	}

}

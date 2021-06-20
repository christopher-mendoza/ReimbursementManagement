package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.User;

public interface UserService {
	boolean addUser(User u);
	User getUserByUsername(String username);
	List<User> getUserByDepartment(String department);
	List<User> getAllUsers();
	boolean changeReimbursementAmt(User u, float newAmt);
}

package dev.mendoza.services;

import java.util.List;

import dev.mendoza.daos.DepartmentDAO;
import dev.mendoza.models.Department;

public class DepartmentServiceImpl implements DepartmentService {

	private static DepartmentDAO ddao = new DepartmentDAO();
	
	@Override
	public List<Department> getDepartmentByName(String depName) {
		return ddao.getDepartmentByName(depName);
	}

	@Override
	public List<Department> getDepartmentBySupervisor(String supervisor) {
		return ddao.getDepartmentBySupervisor(supervisor);
	}

}

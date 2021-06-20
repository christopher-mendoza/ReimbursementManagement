package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.Department;

public interface DepartmentService {
	List<Department> getDepartmentByName(String depName);
	List<Department> getDepartmentBySupervisor(String supervisor);
}

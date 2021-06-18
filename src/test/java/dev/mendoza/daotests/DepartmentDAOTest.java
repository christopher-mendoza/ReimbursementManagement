package dev.mendoza.daotests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.daos.DepartmentDAO;
import dev.mendoza.models.Department;

public class DepartmentDAOTest {

	private DepartmentDAO ddao = new DepartmentDAO();
	
	@Test @Ignore
	public void getDepartmentByNameTest() {
		List<Department> d = ddao.getDepartmentByName("testDepartment");
		for(Department dep : d) {
			System.out.println(dep);
		}
		
	}

	@Test @Ignore
	public void getDepartmentBySupervisorTest() {
		List<Department> d = ddao.getDepartmentBySupervisor("asd");
		for(Department dep : d) {
			System.out.println(dep);
		}
	}
}

package dev.mendoza.daotests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.daos.GradeUploadDAO;
import dev.mendoza.models.GradeUpload;

public class GradeUploadDAOTest {
	
	private GradeUploadDAO gdao = new GradeUploadDAO();
	
	@Test @Ignore
	public void addGradeUploadTest() {
		GradeUpload g = new GradeUpload("Letter Grading", "A");
		assertEquals(true, gdao.addGradeUpload(g));
	}
	
	@Test @Ignore
	public void getGradeUploadByIdTest() {
		System.out.println(gdao.getGradeUploadById(1));
	}
}

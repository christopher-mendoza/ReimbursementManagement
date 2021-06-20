package dev.mendoza.daotests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.daos.PresentationUploadDAO;
import dev.mendoza.models.PresentationUpload;

public class PresentationUploadDAOTest {

	private PresentationUploadDAO pdao = new PresentationUploadDAO();
	
	@Test @Ignore
	public void addPresUploadTest() {
		PresentationUpload p = new PresentationUpload("hey gamers".getBytes());
		assertEquals(true, pdao.addPresUpload(p));
	}

	@Test @Ignore
	public void getPresUploadByIdTest() {
		System.out.println(pdao.getPresUploadById(2));
	}
	
	@Test
	public void changePresUploadTest() {
		PresentationUpload p = new PresentationUpload(1, null);
		assertEquals(true, pdao.changePresUpload(p, "only gamers can see this".getBytes()));
	}
}

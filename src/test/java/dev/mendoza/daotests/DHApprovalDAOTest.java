package dev.mendoza.daotests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.daos.DHApprovalDAO;
import dev.mendoza.models.DHApproval;

public class DHApprovalDAOTest {

	private DHApprovalDAO adao = new DHApprovalDAO();
	
	@Test @Ignore
	public void addDHApprovalTest() {
		DHApproval a = new DHApproval("ghosthead", true);
		assertEquals(true, adao.addDHApproval(a));
	}

	@Test @Ignore
	public void getDHApprovalByIdTest() {
		System.out.println(adao.getDHApprovalById(1));
	}
	
	@Test @Ignore
	public void changeDHApproveTest() {
		DHApproval a = new DHApproval(1, "yeehaw", false);
		assertEquals(true, adao.changeDHApprove(a));
	}
}

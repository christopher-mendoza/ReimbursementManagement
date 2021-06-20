package dev.mendoza.daotests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.daos.DSApprovalDAO;
import dev.mendoza.models.DSApproval;

public class DSApprovalDAOTest {

	private DSApprovalDAO adao = new DSApprovalDAO();
	
	@Test @Ignore
	public void addDSApprovalTest() {
		DSApproval a = new DSApproval("ghostsuper", "wut", false);
		assertEquals(true, adao.addDSApproval(a));
	}

	@Test @Ignore
	public void getDSApprovalByIdTest() {
		System.out.println(adao.getDSApprovalById(1));
	}
	
	@Test @Ignore
	public void changeDSApproveTest() {
		DSApproval a = new DSApproval(2, "ghostsuper", "needs x", false);
		assertEquals(true, adao.changeDSApprove(a));
	}
	
	@Test @Ignore
	public void changeDSReasonTest() {
		DSApproval a = new DSApproval(2, "ghostsuper", "haha", false);
		assertEquals(true, adao.changeDSReason(a, "gamer mode: on"));
		
	}
}

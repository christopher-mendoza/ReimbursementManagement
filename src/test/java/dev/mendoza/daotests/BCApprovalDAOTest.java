package dev.mendoza.daotests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.daos.BCApprovalDAO;
import dev.mendoza.models.BCApproval;

public class BCApprovalDAOTest {


	private BCApprovalDAO adao = new BCApprovalDAO();
	
	@Test @Ignore
	public void addBCApprovalTest() {
		BCApproval a = new BCApproval("bcadmin", "wut", false);
		assertEquals(true, adao.addBCApproval(a));
	}

	@Test @Ignore
	public void getBCApprovalByIdTest() {
		System.out.println(adao.getBCApprovalById(1));
	}
	
	@Test @Ignore
	public void changeBCApproveTest() {
		BCApproval a = new BCApproval(1, "ghostsuper", "needs x", false);
		assertEquals(true, adao.changeBCApprove(a));
	}
	
	@Test @Ignore
	public void changeBCExceedTest() {
		BCApproval a = new BCApproval(1, "bcadmin", "haha", false);
		assertEquals(true, adao.changeBCExceed(a));
	}
	
	@Test @Ignore
	public void changeBCReasonTest() {
		BCApproval a = new BCApproval(1, "ghostsuper", "haha", false);
		assertEquals(true, adao.changeBCReason(a, "congrats u get more money"));
		
	}
}

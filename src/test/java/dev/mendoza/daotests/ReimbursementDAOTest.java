package dev.mendoza.daotests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.daos.ReimbursementDAO;
import dev.mendoza.models.BCApproval;
import dev.mendoza.models.DHApproval;
import dev.mendoza.models.DSApproval;
import dev.mendoza.models.Event;
import dev.mendoza.models.GradeUpload;
import dev.mendoza.models.GradingFormat;
import dev.mendoza.models.PresentationUpload;
import dev.mendoza.models.Reimbursement;
import dev.mendoza.models.User;

public class ReimbursementDAOTest {

	private ReimbursementDAO rdao = new ReimbursementDAO();
	
	@Test @Ignore
	public void addReimbursementTest() {
		User u = new User();
		u.setId(7);
		GradingFormat gf = new GradingFormat();
		gf.setId(1);
		DSApproval ds = new DSApproval();
		ds.setId(1);
		DHApproval dh = new DHApproval();
		dh.setId(1);
		BCApproval bc = new BCApproval();
		bc.setId(1);
		Event e = new Event();
		e.setId(1);
		GradeUpload gUp = new GradeUpload();
		gUp.setId(1);
		PresentationUpload pUp = new PresentationUpload();
		pUp.setId(1);
		Reimbursement r = new Reimbursement();
		r.setName("Ark");
		r.setUsername("angle");
		r.setEvent(e);
		r.setWorkJust("money lmao");
		r.setGradingFormat(gf);
		r.setDsApproval(ds);
		r.setDhApproval(dh);
		r.setBcApproval(bc);
		r.setMissedWork(24.5f);
		r.setgUp(gUp);
		r.setpUp(pUp);
		assertEquals(true, rdao.addReimbursement(r));
	}

	@Test @Ignore
	public void getReimbursementByIdTest() {
		System.out.println(rdao.getReimbursementById(1));
	}
	
	@Test
	public void getAllReimbursementsTest() {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		reimbursements = rdao.getAllReimbursements();
		for(Reimbursement r : reimbursements) {
			System.out.println(r);
		}
	}
}

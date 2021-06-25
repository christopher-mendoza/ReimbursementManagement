package dev.mendoza.services;

import java.util.List;

import dev.mendoza.daos.ReimbursementDAO;
import dev.mendoza.models.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService {

	private static ReimbursementDAO rdao = new ReimbursementDAO();
	
	@Override
	public boolean addReimbursement(Reimbursement r) {
		return rdao.addReimbursement(r);
	}

	@Override
	public Reimbursement getReimbursementById(Integer id) {
		return rdao.getReimbursementById(id);
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		return rdao.getAllReimbursements();
	}

	@Override
	public boolean removeReimbursement(Reimbursement r) {
		return rdao.removeReimbursement(r);
	}

	@Override
	public boolean changeFullApprove(Reimbursement r) {
		return rdao.changeFullApprove(r);
	}

}

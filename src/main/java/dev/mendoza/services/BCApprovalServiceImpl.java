package dev.mendoza.services;

import dev.mendoza.daos.BCApprovalDAO;
import dev.mendoza.models.BCApproval;

public class BCApprovalServiceImpl implements BCApprovalService {

	private static BCApprovalDAO adao = new BCApprovalDAO();
	
	@Override
	public boolean addBCApproval(BCApproval approve) {
		return adao.addBCApproval(approve);
	}

	@Override
	public BCApproval getBCApprovalById(Integer id) {
		return adao.getBCApprovalById(id);
	}

	@Override
	public boolean changeBCApproval(BCApproval a) {
		return adao.changeBCApprove(a);
	}

	@Override
	public boolean changeBCReason(BCApproval a, String reason) {
		return adao.changeBCReason(a, reason);
	}

	@Override
	public boolean changeBCExceed(BCApproval a) {
		return adao.changeBCExceed(a);
	}

}

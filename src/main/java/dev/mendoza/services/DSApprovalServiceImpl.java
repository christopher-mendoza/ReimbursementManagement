package dev.mendoza.services;

import java.util.List;

import dev.mendoza.daos.DSApprovalDAO;
import dev.mendoza.models.DSApproval;

public class DSApprovalServiceImpl implements DSApprovalService {

	private static DSApprovalDAO adao = new DSApprovalDAO();
	
	@Override
	public boolean addDSApproval(DSApproval approve) {
		return adao.addDSApproval(approve);
	}

	@Override
	public DSApproval getDSApprovalById(Integer id) {
		return adao.getDSApprovalById(id);
	}

	@Override
	public boolean changeDSApprove(DSApproval a) {
		return adao.changeDSApprove(a);
	}

	@Override
	public boolean changeDSReason(DSApproval a, String reason) {
		return adao.changeDSReason(a, reason);
	}

	@Override
	public List<DSApproval> getAllDSApprovals() {
		return adao.getAllDSApprovals();
	}

	@Override
	public DSApproval getLatestDSApproval(List<DSApproval> dsList) {
		return dsList.get(dsList.size() - 1);
	}

}

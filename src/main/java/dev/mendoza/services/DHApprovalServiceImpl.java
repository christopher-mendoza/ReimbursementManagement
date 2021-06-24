package dev.mendoza.services;

import java.util.List;

import dev.mendoza.daos.DHApprovalDAO;
import dev.mendoza.models.DHApproval;

public class DHApprovalServiceImpl implements DHApprovalService {

	private static DHApprovalDAO adao = new DHApprovalDAO();
	
	@Override
	public boolean addDHApproval(DHApproval approve) {
		return adao.addDHApproval(approve);
	}

	@Override
	public DHApproval getDHApprovalById(Integer id) {
		return adao.getDHApprovalById(id);
	}

	@Override
	public boolean changeDHApprove(DHApproval a) {
		return adao.changeDHApprove(a);
	}

	@Override
	public List<DHApproval> getAllDHApprovals() {
		return adao.getAllDHApprovals();
	}

	@Override
	public DHApproval getLatestDHApproval(List<DHApproval> dhList) {
		return dhList.get(dhList.size() - 1);
	}

	@Override
	public boolean changeDHReason(DHApproval a, String reason) {
		return adao.changeDHReason(a, reason);
	}

}

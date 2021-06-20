package dev.mendoza.services;

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

}

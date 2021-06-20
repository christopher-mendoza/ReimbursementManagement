package dev.mendoza.services;

import dev.mendoza.models.BCApproval;

public interface BCApprovalService {
	boolean addBCApproval(BCApproval approve);
	BCApproval getBCApprovalById(Integer id);
	boolean changeBCApproval(BCApproval a);
	boolean changeBCReason(BCApproval a, String reason);
	boolean changeBCExceed(BCApproval a);
}

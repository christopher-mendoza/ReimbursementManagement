package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.BCApproval;

public interface BCApprovalService {
	boolean addBCApproval(BCApproval approve);
	BCApproval getBCApprovalById(Integer id);
	List<BCApproval> getAllBCApprovals();
	BCApproval getLatestBCApproval(List<BCApproval> bcList);
	boolean changeBCApproval(BCApproval a);
	boolean changeBCReason(BCApproval a, String reason);
	boolean changeBCExceed(BCApproval a);
}

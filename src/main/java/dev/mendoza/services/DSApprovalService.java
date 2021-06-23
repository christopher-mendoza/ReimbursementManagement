package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.DSApproval;

public interface DSApprovalService {
	boolean addDSApproval(DSApproval approve);
	DSApproval getDSApprovalById(Integer id);
	List<DSApproval> getAllDSApprovals();
	DSApproval getLatestDSApproval(List<DSApproval> dsList);
	boolean changeDSApprove(DSApproval a);
	boolean changeDSReason(DSApproval a, String reason);
}

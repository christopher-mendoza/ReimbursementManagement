package dev.mendoza.services;

import dev.mendoza.models.DSApproval;

public interface DSApprovalService {
	boolean addDSApproval(DSApproval approve);
	DSApproval getDSApprovalById(Integer id);
	boolean changeDSApprove(DSApproval a);
	boolean changeDSReason(DSApproval a, String reason);
}

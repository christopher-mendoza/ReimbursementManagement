package dev.mendoza.services;

import dev.mendoza.models.DHApproval;

public interface DHApprovalService {
	boolean addDHApproval(DHApproval approve);
	DHApproval getDHApprovalById(Integer id);
	boolean changeDHApprove(DHApproval a);
}

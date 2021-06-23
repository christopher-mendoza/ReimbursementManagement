package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.DHApproval;

public interface DHApprovalService {
	boolean addDHApproval(DHApproval approve);
	DHApproval getDHApprovalById(Integer id);
	List<DHApproval> getAllDHApprovals();
	DHApproval getLatestDHApproval(List<DHApproval> dhList);
	boolean changeDHApprove(DHApproval a);
}

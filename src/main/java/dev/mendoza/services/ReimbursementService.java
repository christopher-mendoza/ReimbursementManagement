package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.Reimbursement;

public interface ReimbursementService {
	boolean addReimbursement(Reimbursement r);
	Reimbursement getReimbursementById(Integer id);
	List<Reimbursement> getAllReimbursements();
}

package dev.mendoza.services;

import java.util.List;

import dev.mendoza.daos.PresentationUploadDAO;
import dev.mendoza.models.PresentationUpload;

public class PresentationUploadServiceImpl implements PresentationUploadService {

	private static PresentationUploadDAO pdao = new PresentationUploadDAO();
	
	@Override
	public boolean addPresUpload(PresentationUpload p) {
		return pdao.addPresUpload(p);
	}

	@Override
	public PresentationUpload getPresUploadById(Integer id) {
		return pdao.getPresUploadById(id);
	}

	@Override
	public boolean changePresUpload(PresentationUpload p, byte[] upload) {
		return pdao.changePresUpload(p, upload);
	}

	@Override
	public List<PresentationUpload> getAllPresUploads() {
		return pdao.getAllPresUploads();
	}

	@Override
	public PresentationUpload getLatestPresUpload(List<PresentationUpload> puList) {
		return puList.get(puList.size() - 1);
	}

}

package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.PresentationUpload;

public interface PresentationUploadService {
	boolean addPresUpload(PresentationUpload p);
	PresentationUpload getPresUploadById(Integer id);
	List<PresentationUpload> getAllPresUploads();
	PresentationUpload getLatestPresUpload(List<PresentationUpload> puList);
	boolean changePresUpload(PresentationUpload p, byte[] upload);
}

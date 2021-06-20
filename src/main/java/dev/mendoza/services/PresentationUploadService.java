package dev.mendoza.services;

import dev.mendoza.models.PresentationUpload;

public interface PresentationUploadService {
	boolean addPresUpload(PresentationUpload p);
	PresentationUpload getPresUploadById(Integer id);
	boolean changePresUpload(PresentationUpload p, byte[] upload);
}

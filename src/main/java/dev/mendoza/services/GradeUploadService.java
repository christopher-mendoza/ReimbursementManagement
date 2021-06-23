package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.GradeUpload;

public interface GradeUploadService {
	boolean addGradeUpload(GradeUpload g);
	GradeUpload getGradeUploadById(Integer id);
	List<GradeUpload> getAllGradeUploads();
	GradeUpload getLatestGradeUpload(List<GradeUpload> guList);
	boolean changeGradeFormat(GradeUpload g, String format);
	boolean changeGradeUpload(GradeUpload g, String grade);
}

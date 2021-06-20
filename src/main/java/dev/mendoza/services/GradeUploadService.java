package dev.mendoza.services;

import dev.mendoza.models.GradeUpload;

public interface GradeUploadService {
	boolean addGradeUpload(GradeUpload g);
	GradeUpload getGradeUploadById(Integer id);
	boolean changeGradeFormat(GradeUpload g, String format);
	boolean changeGradeUpload(GradeUpload g, String grade);
}

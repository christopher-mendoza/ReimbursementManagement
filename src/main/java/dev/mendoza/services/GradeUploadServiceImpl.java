package dev.mendoza.services;

import java.util.List;

import dev.mendoza.daos.GradeUploadDAO;
import dev.mendoza.models.GradeUpload;

public class GradeUploadServiceImpl implements GradeUploadService {

	private static GradeUploadDAO gdao = new GradeUploadDAO();
	
	@Override
	public boolean addGradeUpload(GradeUpload g) {
		return gdao.addGradeUpload(g);
	}

	@Override
	public GradeUpload getGradeUploadById(Integer id) {
		return gdao.getGradeUploadById(id);
	}

	@Override
	public boolean changeGradeFormat(GradeUpload g, String format) {
		return gdao.changeGradeFormat(g, format);
	}

	@Override
	public boolean changeGradeUpload(GradeUpload g, String grade) {
		return gdao.changeGradeUpload(g, grade);
	}

	@Override
	public List<GradeUpload> getAllGradeUploads() {
		return gdao.getAllGradeUploads();
	}

	@Override
	public GradeUpload getLatestGradeUpload(List<GradeUpload> guList) {
		return guList.get(guList.size() - 1);
	}

}

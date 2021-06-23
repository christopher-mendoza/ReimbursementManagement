package dev.mendoza.services;

import java.util.List;

import dev.mendoza.daos.GradingFormatDAO;
import dev.mendoza.models.GradingFormat;

public class GradingFormatServiceImpl implements GradingFormatService {
	
	private static GradingFormatDAO gdao = new GradingFormatDAO();
	@Override
	public GradingFormat getGradingFormatById(Integer id) {
		return gdao.getGradingFormatById(id);
	}

	@Override
	public List<GradingFormat> getAllGradingFormats() {
		return gdao.getAllGradingFormats();
	}

	@Override
	public GradingFormat getGradingFortmatByFormat(String format) {
		return gdao.getGradingFormatByFormat(format);
	}

}

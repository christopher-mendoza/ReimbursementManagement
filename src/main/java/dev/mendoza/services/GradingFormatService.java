package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.GradingFormat;

public interface GradingFormatService {
	GradingFormat getGradingFormatById(Integer id);
	List<GradingFormat> getAllGradingFormats();
}

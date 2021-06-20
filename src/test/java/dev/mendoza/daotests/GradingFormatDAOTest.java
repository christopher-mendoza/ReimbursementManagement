package dev.mendoza.daotests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.daos.GradingFormatDAO;
import dev.mendoza.models.GradingFormat;

public class GradingFormatDAOTest {

	GradingFormatDAO gdao = new GradingFormatDAO();
	
	@Test @Ignore
	public void getGradingFormatByIdTest() {
		System.out.println(gdao.getGradingFormatById(1));
	}
	
	@Test
	public void getAllGradingFormatsTest() {
		List<GradingFormat> formats = new ArrayList<GradingFormat>();
		formats = gdao.getAllGradingFormats();
		for(GradingFormat g : formats) {
			System.out.println(g);
		}
	}

}

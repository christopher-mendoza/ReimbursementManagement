package dev.mendoza.daotests;

import static org.junit.Assert.*;

import java.util.Date;
import java.time.LocalDate;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.daos.EventDAO;
import dev.mendoza.models.Event;
import dev.mendoza.models.EventType;

public class EventDAOTest {

	private EventDAO edao = new EventDAO();
	
	@Test @Ignore
	public void addEventTest() {
		EventType eType = new EventType(3, "Certification Preparation Classes", .75f);
		//Date time = new Date();
		//Event e = new Event(time, "Tokyo, JPN", "Gamer School", 400f, eType);
		//assertEquals(true, edao.addEvent(e));
	}
	
	@Test
	public void getEventByIdTest() {
		System.out.println(edao.getEventById(1));
	}

}

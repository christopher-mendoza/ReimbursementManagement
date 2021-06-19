package dev.mendoza.daotests;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

import dev.mendoza.daos.EventDAO;
import dev.mendoza.models.Event;
import dev.mendoza.models.EventType;

public class EventDAOTest {

	private EventDAO edao = new EventDAO();
	
	@Test
	public void addEventTest() {
		EventType eType = new EventType(3, "Certification Preparation Classes", .75f);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		Event e = new Event(time, "Tokyo, JPN", "Gamer School", 400f, eType);
		assertEquals(true, edao.addEvent(e));
	}

}

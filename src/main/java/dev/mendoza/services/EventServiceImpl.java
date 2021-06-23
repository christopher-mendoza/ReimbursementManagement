package dev.mendoza.services;

import dev.mendoza.daos.EventDAO;
import dev.mendoza.models.Event;
import dev.mendoza.models.EventType;

public class EventServiceImpl implements EventService {

	private static EventDAO edao = new EventDAO();
	
	@Override
	public boolean addEvent(Event e) {
		return edao.addEvent(e);
	}

	@Override
	public Event getEventById(Integer id) {
		return edao.getEventById(id);
	}

	@Override
	public EventType getEventTypeByEventType(String eventType) {
		return edao.getEventTypeByEventType(eventType);
	}

}

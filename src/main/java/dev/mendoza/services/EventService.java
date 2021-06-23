package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.Event;
import dev.mendoza.models.EventType;

public interface EventService {
	boolean addEvent(Event e);
	Event getEventById(Integer id);
	List<Event> getAllEvents();
	Event getLatestEvent(List<Event> eList);
	EventType getEventTypeByEventType(String eventType);
}

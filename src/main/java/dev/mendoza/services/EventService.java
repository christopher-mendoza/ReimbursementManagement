package dev.mendoza.services;

import dev.mendoza.models.Event;
import dev.mendoza.models.EventType;

public interface EventService {
	boolean addEvent(Event e);
	Event getEventById(Integer id);
	EventType getEventTypeByEventType(String eventType);
}

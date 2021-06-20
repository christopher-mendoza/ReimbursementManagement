package dev.mendoza.services;

import dev.mendoza.models.Event;

public interface EventService {
	boolean addEvent(Event e);
	Event getEventById(Integer id);
}

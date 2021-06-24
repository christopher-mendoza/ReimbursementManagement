package dev.mendoza.models;

import java.sql.Date;
import java.sql.Timestamp;

public class Event {
	private Integer id;
	private Date eventDate;
	private String eventLocation;
	private String eventDesc;
	private Float eventCost;
	private Float eventProposed;
	private EventType eventType;
	
	public Event() {
		super();
	}

	public Event(Date eventDate, String eventLocation, String eventDesc, Float eventCost, EventType eventType) {
		super();
		this.eventDate = eventDate;
		this.eventLocation = eventLocation;
		this.eventDesc = eventDesc;
		this.eventCost = eventCost;
		this.eventType = eventType;
	}

	public Event(Integer id, Date eventDate, String eventLocation, String eventDesc, Float eventCost,
			EventType eventType) {
		super();
		this.id = id;
		this.eventDate = eventDate;
		this.eventLocation = eventLocation;
		this.eventDesc = eventDesc;
		this.eventCost = eventCost;
		this.eventType = eventType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	public Float getEventCost() {
		return eventCost;
	}

	public void setEventCost(Float eventCost) {
		this.eventCost = eventCost;
	}
	
	public Float getEventProposed() {
		return eventProposed;
	}

	public void setEventProposed(Float eventProposed) {
		this.eventProposed = eventProposed;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventCost == null) ? 0 : eventCost.hashCode());
		result = prime * result + ((eventDate == null) ? 0 : eventDate.hashCode());
		result = prime * result + ((eventDesc == null) ? 0 : eventDesc.hashCode());
		result = prime * result + ((eventLocation == null) ? 0 : eventLocation.hashCode());
		result = prime * result + ((eventProposed == null) ? 0 : eventProposed.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (eventCost == null) {
			if (other.eventCost != null)
				return false;
		} else if (!eventCost.equals(other.eventCost))
			return false;
		if (eventDate == null) {
			if (other.eventDate != null)
				return false;
		} else if (!eventDate.equals(other.eventDate))
			return false;
		if (eventDesc == null) {
			if (other.eventDesc != null)
				return false;
		} else if (!eventDesc.equals(other.eventDesc))
			return false;
		if (eventLocation == null) {
			if (other.eventLocation != null)
				return false;
		} else if (!eventLocation.equals(other.eventLocation))
			return false;
		if (eventProposed == null) {
			if (other.eventProposed != null)
				return false;
		} else if (!eventProposed.equals(other.eventProposed))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventDate=" + eventDate + ", eventLocation=" + eventLocation + ", eventDesc="
				+ eventDesc + ", eventCost=" + eventCost + ", eventProposed=" + eventProposed + ", eventType="
				+ eventType + "]";
	}
	
	
	
	
}

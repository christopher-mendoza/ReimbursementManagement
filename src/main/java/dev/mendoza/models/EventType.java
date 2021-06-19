package dev.mendoza.models;

public class EventType {
	private Integer id;
	private String type;
	private Float coverage;
	
	public EventType() {
		super();
	}

	public EventType(String type, Float coverage) {
		super();
		this.type = type;
		this.coverage = coverage;
	}

	public EventType(Integer id, String type, Float coverage) {
		super();
		this.id = id;
		this.type = type;
		this.coverage = coverage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getCoverage() {
		return coverage;
	}

	public void setCoverage(Float coverage) {
		this.coverage = coverage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coverage == null) ? 0 : coverage.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		EventType other = (EventType) obj;
		if (coverage == null) {
			if (other.coverage != null)
				return false;
		} else if (!coverage.equals(other.coverage))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventType [id=" + id + ", type=" + type + ", coverage=" + coverage + "]";
	}
	
	
}

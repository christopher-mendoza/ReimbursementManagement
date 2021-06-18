package dev.mendoza.models;

public class DSApproval {
	private Integer id;
	private String name;
	private String request;
	private String reason;
	private Boolean approve;
	
	public DSApproval() {
		super();
	}

	public DSApproval(String name, Boolean approve) {
		super();
		this.name = name;
		this.approve = approve;
	}
	
	public DSApproval(String name, String request, String reason, Boolean approve) {
		super();
		this.name = name;
		this.request = request;
		this.reason = reason;
		this.approve = approve;
	}

	public DSApproval(Integer id, String name, String request, String reason, Boolean approve) {
		super();
		this.id = id;
		this.name = name;
		this.request = request;
		this.reason = reason;
		this.approve = approve;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getApprove() {
		return approve;
	}

	public void setApprove(Boolean approve) {
		this.approve = approve;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approve == null) ? 0 : approve.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((request == null) ? 0 : request.hashCode());
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
		DSApproval other = (DSApproval) obj;
		if (approve == null) {
			if (other.approve != null)
				return false;
		} else if (!approve.equals(other.approve))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (request == null) {
			if (other.request != null)
				return false;
		} else if (!request.equals(other.request))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DSApproval [id=" + id + ", name=" + name + ", request=" + request + ", reason=" + reason + ", approve="
				+ approve + "]";
	}
	
	
}

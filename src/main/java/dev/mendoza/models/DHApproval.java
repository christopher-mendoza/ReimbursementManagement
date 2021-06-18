package dev.mendoza.models;

public class DHApproval {
	private Integer id;
	private String name;
	private String request;
	private Boolean approve;
	
	public DHApproval() {
		super();
	}

	public DHApproval(String name, Boolean approve) {
		super();
		this.name = name;
		this.approve = approve;
	}

	public DHApproval(String name, String request, Boolean approve) {
		super();
		this.name = name;
		this.request = request;
		this.approve = approve;
	}

	public DHApproval(Integer id, String name, String request, Boolean approve) {
		super();
		this.id = id;
		this.name = name;
		this.request = request;
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
		DHApproval other = (DHApproval) obj;
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
		if (request == null) {
			if (other.request != null)
				return false;
		} else if (!request.equals(other.request))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DHApproval [id=" + id + ", name=" + name + ", request=" + request + ", approve=" + approve + "]";
	}
	
	
}
